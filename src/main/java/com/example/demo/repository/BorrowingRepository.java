package com.example.demo.repository;

import com.example.demo.entity.Borrowing;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    // ✅ Ensure the correct return type (List<Borrowing>) instead of KeyValues
    List<Borrowing> findByUser(User user);

    // ✅ Ensure this method correctly finds active borrowings
    List<Borrowing> findByReturnDateIsNull();
}
