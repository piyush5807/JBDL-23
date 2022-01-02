package com.example.jbdl.minorproject1.service;

import com.example.jbdl.minorproject1.models.Author;
import com.example.jbdl.minorproject1.models.Book;
import com.example.jbdl.minorproject1.models.Student;
import com.example.jbdl.minorproject1.repository.BookRepository;
import com.example.jbdl.minorproject1.requests.BookCreateRequest;
import com.example.jbdl.minorproject1.requests.StudentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    AuthorService authorService;

    @Autowired
    BookRepository bookRepository;

    public void createBook(BookCreateRequest bookCreateRequest) {
        // 1. If the author is not created, we need to create the author
        // 2. Fetch the authorId and attach it in the book object
        // 3. Save the book object

        Author author = bookCreateRequest.toAuthor();
        author = authorService.createOrGetAuthor(author); // Fetch the authorId

        Book book = bookCreateRequest.to(author);
//        book.setAuthor(author); // attach it in the book object

        bookRepository.save(book); // Save the book object
    }

    public Book getBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }
}
