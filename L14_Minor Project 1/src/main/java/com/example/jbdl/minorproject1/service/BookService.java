package com.example.jbdl.minorproject1.service;

import com.example.jbdl.minorproject1.models.Author;
import com.example.jbdl.minorproject1.models.Book;
import com.example.jbdl.minorproject1.models.Genre;
import com.example.jbdl.minorproject1.models.Student;
import com.example.jbdl.minorproject1.repository.BookRepository;
import com.example.jbdl.minorproject1.requests.BookCreateRequest;
import com.example.jbdl.minorproject1.requests.StudentCreateRequest;
import com.example.jbdl.minorproject1.responses.BookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    AuthorService authorService;

    @Autowired
    BookRepository bookRepository;

    public Book createBook(BookCreateRequest bookCreateRequest) {
        // 1. If the author is not created, we need to create the author
        // 2. Fetch the authorId and attach it in the book object
        // 3. Save the book object

        Author author = bookCreateRequest.toAuthor();
        author = authorService.createOrGetAuthor(author); // Fetch the authorId

        Book book = bookCreateRequest.to(author);
//        book.setAuthor(author); // attach it in the book object

        return bookRepository.save(book); // Save the book object
    }

    public Book createOrUpdateBook(Book book){
        return bookRepository.save(book);
    }

    public List<BookResponse> getBookById(int id) {
        Book book = bookRepository.findById(id).orElse(null);
        return Collections.singletonList(book.to());
    }

    public List<BookResponse> getBookByGenre(String genre){
        return bookRepository.getBooksInGenreSql(genre).stream()
                .map(Book::to)
                .collect(Collectors.toList());
    }

    public List<BookResponse> getBookByAuthorEmail(String authorEmail){
        return null;
    }

    public List<BookResponse> getBooksByAvail(boolean avail){
        return null;
    }
}
