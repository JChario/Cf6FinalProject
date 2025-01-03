package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BorrowingDTO {
    private Long id;
    private Long userId;
    private Long bookId;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private BigDecimal overdueCharges;

    public BorrowingDTO(Long id, Long userId, Long bookId, LocalDateTime borrowDate, LocalDateTime returnDate, BigDecimal overdueCharges) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.overdueCharges = overdueCharges;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }

    public LocalDateTime getBorrowDate() { return borrowDate; }
    public void setBorrowDate(LocalDateTime borrowDate) { this.borrowDate = borrowDate; }

    public LocalDateTime getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDateTime returnDate) { this.returnDate = returnDate; }

    public BigDecimal getOverdueCharges() { return overdueCharges; }
    public void setOverdueCharges(BigDecimal overdueCharges) { this.overdueCharges = overdueCharges; }
}
