package com.example.demo.mapper;

import com.example.demo.dto.BorrowingDTO;
import com.example.demo.entity.Borrowing;

public class BorrowingMapper {
    public static BorrowingDTO toDTO(Borrowing borrowing) {
        if (borrowing == null || borrowing.getUser() == null || borrowing.getBook() == null) {
            throw new IllegalArgumentException("Borrowing, User, or Book cannot be null");
        }

        return new BorrowingDTO(
                borrowing.getId(),
                borrowing.getUser().getId(), // Ensuring User exists
                borrowing.getBook().getId(), // Ensuring Book exists
                borrowing.getBorrowDate(),
                borrowing.getReturnDate(),
                borrowing.getOverdueCharges()
        );
    }
}