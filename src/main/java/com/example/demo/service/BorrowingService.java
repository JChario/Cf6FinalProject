package com.example.demo.service;

import com.example.demo.dto.BorrowingDTO;
import java.util.List;

public interface BorrowingService {
    List<BorrowingDTO> getAllActiveBorrowings();
    List<BorrowingDTO> getUserBorrowings(String username); // ✅ Matches expected implementation
    BorrowingDTO borrowBook(String username, Long bookId); // ✅ Matches expected implementation
    BorrowingDTO returnBook(String username, Long borrowingId); // ✅ Matches expected implementation
}
