package com.example.demo.controller;

import com.example.demo.dto.BorrowingDTO;
import com.example.demo.service.BorrowingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowings")
@Tag(name = "Borrowing Management", description = "APIs for borrowing and returning books")
public class BorrowingController {
    private final BorrowingService borrowingService;

    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @Operation(summary = "Get active borrowings", description = "Fetches a list of books that are currently borrowed and not yet returned")
    @GetMapping("/active")
    public ResponseEntity<List<BorrowingDTO>> getActiveBorrowings() {
        return ResponseEntity.ok(borrowingService.getAllActiveBorrowings());
    }

    @Operation(summary = "Get user borrowings", description = "Fetches all borrowings associated with a specific user ID")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BorrowingDTO>> getUserBorrowings(@PathVariable Long userId) {
        return ResponseEntity.ok(borrowingService.getUserBorrowings(userId));
    }

    @Operation(summary = "Borrow a book", description = "Allows a user to borrow a book")
    @PostMapping("/{userId}/{bookId}")
    public ResponseEntity<BorrowingDTO> borrowBook(@PathVariable Long userId, @PathVariable Long bookId) {
        return ResponseEntity.ok(borrowingService.borrowBook(userId, bookId));
    }

    @Operation(summary = "Return a book", description = "Allows a user to return a borrowed book")
    @PutMapping("/{borrowingId}/return")
    public ResponseEntity<BorrowingDTO> returnBook(@PathVariable Long borrowingId) {
        return ResponseEntity.ok(borrowingService.returnBook(borrowingId));
    }
}
