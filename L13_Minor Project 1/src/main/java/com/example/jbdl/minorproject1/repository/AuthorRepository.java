package com.example.jbdl.minorproject1.repository;

import com.example.jbdl.minorproject1.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    // : works on argumentName, '?' works on argumentNumber

    @Query("select a from Author a where a.email = :email")
    Author findByEmailId( String email);

    // select * from author where email = "abc@google.com";
}
