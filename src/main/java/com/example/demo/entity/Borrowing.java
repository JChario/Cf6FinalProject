package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "borrowings")
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private BigDecimal overdueCharges;

    // ✅ Ensure getUser() and getBook() exist
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public BigDecimal getOverdueCharges() {
        return overdueCharges;
    }

    public void setUser(User user) {
    }

    public void setBook(Book borrowedBook) {
    }

    public void setBorrowDate(LocalDateTime now) {
    }

    public void setOverdueCharges(BigDecimal zero) {
    }

    public void setReturnDate(LocalDateTime now) {
    }
}
