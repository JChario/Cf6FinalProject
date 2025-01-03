package com.example.demo.repository;

import com.example.demo.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
    List<Borrowing> findByUserId(Long userId);  // Fetch borrowings by user

    List<Borrowing> findByReturnDateIsNull();  // Find active borrowings (not returned)

    List<Borrowing> findByReturnDateIsNullAndUserId(Long userId); // Find active borrowings for a specific user
}
