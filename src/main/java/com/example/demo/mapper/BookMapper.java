package com.example.demo.mapper;

import com.example.demo.dto.BookDTO;
import com.example.demo.entity.Book;

public class BookMapper {
    public static BookDTO toDTO(Book book) {
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getAvailable());
    }
}
