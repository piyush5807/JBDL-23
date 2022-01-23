package com.example.jbdl.minorproject1.repository;

import com.example.jbdl.minorproject1.models.Author;
import com.example.jbdl.minorproject1.models.Book;
import com.example.jbdl.minorproject1.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    // 1. JPQL - Java Persistence Query language
    // 2. Native sql query - Queries wrt sql tables


    @Query("select b from Book b where b.genre = :genre") // --> mysql
    List<Book> getBooksInGenre(Genre genre);

    @Query(value = "select * from book b where b.genre = ?1", nativeQuery = true)
    List<Book> getBooksInGenreSql(String genre);

    // Get book by author
    // fk - author_id
    // "select * from book b join author a on b.author_id = a.id where a.email = ?1"

    //  Get book which are available
    // select * from book where my_student_id is not null
}
