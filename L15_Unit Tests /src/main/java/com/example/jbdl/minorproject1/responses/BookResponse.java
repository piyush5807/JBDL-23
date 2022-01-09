package com.example.jbdl.minorproject1.responses;

import com.example.jbdl.minorproject1.models.Author;
import com.example.jbdl.minorproject1.models.Genre;
import com.example.jbdl.minorproject1.models.Student;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    private int id;
    private String name;

    private Genre genre;

    @JsonIgnoreProperties("bookList")
    private Author author;
}
