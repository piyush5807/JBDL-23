package com.example.jbdl.minorproject1.repository;

import com.example.jbdl.minorproject1.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
