package com.neelesh.bookservice.service;

import com.neelesh.bookservice.model.Book;
import com.neelesh.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImp implements BookService{

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        bookRepository.findAll().forEach(book-> bookList.add(book));
        return bookList;
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(()->new RuntimeException("Not able to find book with id: "+id));
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.delete(bookRepository.findById(id).orElseThrow(()->new RuntimeException("Not able to find book with id: "+id)));
    }

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);

    }

    @Override
    public Book updateBook(Long id, Book updatedBook) {
        Book book = bookRepository.findById(id).orElseThrow(()->new RuntimeException("book not found with id: "+id));
        book.setAuthor(updatedBook.getAuthor());
        book.setCategory(updatedBook.getCategory());
        book.setDescription(updatedBook.getDescription());
        book.setStock(updatedBook.getStock());
        book.setPrice(updatedBook.getPrice());
        book.setTitle(updatedBook.getTitle());

        return bookRepository.save(book);
    }


}
