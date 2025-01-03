package com.example.demo.service;

import com.example.demo.dto.BookDTO;
import com.example.demo.entity.Book;
import java.util.List;

public interface BookService {
    List<BookDTO> getAvailableBooks();
    BookDTO getBookById(Long id);
    Book saveBook(Book book);
    void deleteBook(Long id);
}
