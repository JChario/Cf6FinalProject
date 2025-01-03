package com.example.demo.service;


import com.example.demo.dto.BorrowingDTO;
import com.example.demo.entity.Borrowing;
import com.example.demo.entity.Book;
import com.example.demo.entity.User;
import com.example.demo.mapper.BorrowingMapper;
import com.example.demo.repository.BorrowingRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BorrowingServiceImpl implements BorrowingService {
    private final BorrowingRepository borrowingRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BorrowingServiceImpl(BorrowingRepository borrowingRepository,
                                UserRepository userRepository,
                                BookRepository bookRepository) {
        this.borrowingRepository = borrowingRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BorrowingDTO> getAllActiveBorrowings() {
        return borrowingRepository.findByReturnDateIsNull()
                .stream()
                .map(BorrowingMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BorrowingDTO> getUserBorrowings(Long userId) {
        return borrowingRepository.findByUserId(userId)
                .stream()
                .map(BorrowingMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BorrowingDTO borrowBook(Long userId, Long bookId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Book> book = bookRepository.findById(bookId);

        if (user.isEmpty() || book.isEmpty() || !book.get().getAvailable()) {
            throw new IllegalArgumentException("Invalid borrow request: User or book unavailable");
        }

        Book borrowedBook = book.get();
        borrowedBook.setAvailable(false); // Mark book as unavailable
        bookRepository.save(borrowedBook);

        Borrowing borrowing = new Borrowing();
        borrowing.setUser(user.get());
        borrowing.setBook(borrowedBook);
        borrowing.setBorrowDate(LocalDateTime.now());
        borrowing.setOverdueCharges(BigDecimal.ZERO);

        return BorrowingMapper.toDTO(borrowingRepository.save(borrowing));
    }

    @Override
    public BorrowingDTO returnBook(Long borrowingId) {
        Optional<Borrowing> borrowingOpt = borrowingRepository.findById(borrowingId);

        if (borrowingOpt.isEmpty()) {
            throw new IllegalArgumentException("Borrowing record not found");
        }

        Borrowing borrowing = borrowingOpt.get();
        borrowing.setReturnDate(LocalDateTime.now());

        // Calculate overdue charges
        if (borrowing.getBorrowDate().plusDays(14).isBefore(borrowing.getReturnDate())) {
            borrowing.setOverdueCharges(BigDecimal.valueOf(5)); // Example: 5 EUR fine
        }

        // Mark book as available again
        Book returnedBook = borrowing.getBook();
        returnedBook.setAvailable(true);
        bookRepository.save(returnedBook);

        return BorrowingMapper.toDTO(borrowingRepository.save(borrowing));
    }
}
