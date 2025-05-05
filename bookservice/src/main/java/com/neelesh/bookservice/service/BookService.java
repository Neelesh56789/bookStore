package com.neelesh.bookservice.service;

import com.neelesh.bookservice.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    void deleteBook(Long id);
    void addBook(Book book);
    Book updateBook(Long id, Book book);
}
