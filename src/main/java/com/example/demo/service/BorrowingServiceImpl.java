package com.example.demo.service;

import com.example.demo.dto.BorrowingDTO;
import com.example.demo.entity.Borrowing;
import com.example.demo.entity.Book;
import com.example.demo.entity.User;
import com.example.demo.mapper.BorrowingMapper;
import com.example.demo.repository.BorrowingRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BorrowingService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowingServiceImpl implements BorrowingService {
    private final BorrowingRepository borrowingRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BorrowingServiceImpl(BorrowingRepository borrowingRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.borrowingRepository = borrowingRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<BorrowingDTO> getAllActiveBorrowings() {
        return borrowingRepository.findByReturnDateIsNull()
                .stream()
                .map(BorrowingMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BorrowingDTO> getUserBorrowings(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return borrowingRepository.findByUser(user)
                .stream()
                .map(BorrowingMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public BorrowingDTO borrowBook(String username, Long bookId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (!book.getAvailable()) {
            throw new RuntimeException("Book is not available for borrowing");
        }

        Borrowing borrowing = new Borrowing();
        borrowing.setUser(user);
        borrowing.setBook(book);
        borrowing.setBorrowDate(LocalDate.from(LocalDate.now().atStartOfDay()));
        borrowing.setReturnDate(null); // Not returned yet
        borrowing.setOverdueCharges(BigDecimal.valueOf(0.0));

        book.setAvailable(false); // Mark book as unavailable

        borrowingRepository.save(borrowing);
        bookRepository.save(book);

        return BorrowingMapper.toDTO(borrowing);
    }

    @Override
    public BorrowingDTO returnBook(String username, Long borrowingId) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId)
                .orElseThrow(() -> new RuntimeException("Borrowing record not found"));

        if (!borrowing.getUser().getUsername().equals(username)) {
            throw new RuntimeException("You can only return books you borrowed");
        }

        borrowing.setReturnDate(LocalDate.from(LocalDate.now().atStartOfDay()));
        Book book = borrowing.getBook();
        book.setAvailable(true); // Mark book as available again

        borrowingRepository.save(borrowing);
        bookRepository.save(book);

        return BorrowingMapper.toDTO(borrowing);
    }
}
