package com.example.jbdl.minorproject1.requests;

import com.example.jbdl.minorproject1.models.Author;
import com.example.jbdl.minorproject1.models.Book;
import com.example.jbdl.minorproject1.models.Genre;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookCreateRequest {

    @NotNull
    private String bookName;

    @NotNull
    private Genre genre;

    @NotNull
    private String authorName;

    @NotNull
    @Email
    private String authorEmail;

    private String authorWebsite;

    public Book to(Author author){
        return Book.builder()
                .name(this.getBookName())
                .genre(this.getGenre())
                .author(author)
                .build();
    }

    public Book to(){
        return Book.builder()
                .name(this.getBookName())
                .genre(this.getGenre())
                .build();
    }

    public Author toAuthor(){
        return Author.builder()
                .email(this.getAuthorEmail())
                .website(this.getAuthorWebsite())
                .name(this.getAuthorName())
                .build();
    }
}
