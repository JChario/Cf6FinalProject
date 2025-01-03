package com.example.demo.mapper;

import com.example.demo.dto.BorrowingDTO;
import com.example.demo.entity.Borrowing;

public class BorrowingMapper {
    public static BorrowingDTO toDTO(Borrowing borrowing) {
        return new BorrowingDTO(
                borrowing.getId(),
                borrowing.getUser() != null ? borrowing.getUser().getUsername() : "Unknown",
                borrowing.getBook() != null ? borrowing.getBook().getId() : null,
                borrowing.getBorrowDate(),
                borrowing.getReturnDate(),
                borrowing.getOverdueCharges()
        );
    }
}
