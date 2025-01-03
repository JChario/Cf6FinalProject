package com.example.demo.service;

import com.example.demo.dto.BorrowingDTO;
import java.util.List;

public interface BorrowingService {
    List<BorrowingDTO> getAllActiveBorrowings();
    List<BorrowingDTO> getUserBorrowings(Long userId);
    BorrowingDTO borrowBook(Long userId, Long bookId);
    BorrowingDTO returnBook(Long borrowingId);
}
