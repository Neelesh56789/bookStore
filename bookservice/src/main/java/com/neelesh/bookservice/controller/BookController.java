package com.neelesh.bookservice.controller;

import com.neelesh.bookservice.model.Book;
import com.neelesh.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add/new")
    public void addBook(@RequestBody Book book)
    {
        bookService.addBook(book);
    }

    @PutMapping("/update/id/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book)
    {
        return bookService.updateBook(id,book);
    }

    @DeleteMapping("/delete/id/{id}")
    public void deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
    }

    @GetMapping("/get/id/{id}")
    public Book getById(@PathVariable Long id)
    {
        return bookService.getBookById(id);
    }

    @GetMapping("/getAll")
    public List<Book> getAll(){
        return bookService.getAllBooks();
    }
}
