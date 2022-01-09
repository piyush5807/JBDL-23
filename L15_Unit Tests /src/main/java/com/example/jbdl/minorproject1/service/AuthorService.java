package com.example.jbdl.minorproject1.service;

import com.example.jbdl.minorproject1.models.Author;
import com.example.jbdl.minorproject1.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public Author createOrGetAuthor(Author author){
        Author retrievedAuthorFromDB = getAuthorByEmail(author.getEmail());

        if(retrievedAuthorFromDB != null){
            return retrievedAuthorFromDB;
        }

        return authorRepository.save(author);
    }

    private Author getAuthorByEmail(String email){
        return authorRepository.findByEmailId(email);
    }
}
