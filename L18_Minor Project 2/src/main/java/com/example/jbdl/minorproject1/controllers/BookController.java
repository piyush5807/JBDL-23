package com.example.jbdl.minorproject1.controllers;

import com.example.jbdl.minorproject1.models.Book;
import com.example.jbdl.minorproject1.requests.BookCreateRequest;
import com.example.jbdl.minorproject1.requests.BookFilterQuery;
import com.example.jbdl.minorproject1.responses.BookResponse;
import com.example.jbdl.minorproject1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    // Admin
    @PostMapping("/book")
    public Book createBook(@Valid @RequestBody BookCreateRequest bookCreateRequest){
        return bookService.createBook(bookCreateRequest);
    }

    // Student + Admin
    @GetMapping("/book")
    public List<BookResponse> getBook(@RequestParam("filterType") String filterType,
                                      @RequestParam("filterValue") String filterValue){

        switch (BookFilterQuery.valueOf(filterType)){
            case ID:
                return bookService.getBookById(Integer.parseInt(filterValue));
            case GENRE:
                return bookService.getBookByGenre(filterValue);
            case AUTHOR:
                return bookService.getBookByAuthorEmail(filterValue);
            case AVAILABILITY:
                return bookService.getBooksByAvail(Boolean.valueOf(filterValue));
        }

        return null;
    }
}
