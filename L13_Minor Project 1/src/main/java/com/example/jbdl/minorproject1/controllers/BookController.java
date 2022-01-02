package com.example.jbdl.minorproject1.controllers;

import com.example.jbdl.minorproject1.models.Book;
import com.example.jbdl.minorproject1.models.Student;
import com.example.jbdl.minorproject1.requests.BookCreateRequest;
import com.example.jbdl.minorproject1.requests.StudentCreateRequest;
import com.example.jbdl.minorproject1.service.BookService;
import com.example.jbdl.minorproject1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/book")
    public void createBook(@Valid @RequestBody BookCreateRequest bookCreateRequest){
        bookService.createBook(bookCreateRequest);
    }

    @GetMapping("/book/{bookId}")
    public Book getBook(@PathVariable("bookId") int id){
        return bookService.getBookById(id);
    }
}
