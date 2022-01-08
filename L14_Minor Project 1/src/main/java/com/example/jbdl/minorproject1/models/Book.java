package com.example.jbdl.minorproject1.models;

import com.example.jbdl.minorproject1.responses.BookResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Book {  // Referencing class (Child)

    // Author attribute in Book class is mapped to booksList attribute in Author class

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @JoinColumn
    @ManyToOne // Many (Books)  to   One (Author)
    @JsonIgnoreProperties("bookList")
    private Author author;

    @JoinColumn
    @ManyToOne // 2 parts Many (Book) to One (Student)
    @JsonIgnoreProperties("bookList")
    private Student my_student;

    @OneToMany(mappedBy = "book")
    @JsonIgnoreProperties("book")
    private List<Request> requestList;

    @CreationTimestamp
    private Date createdOn;


    public BookResponse to(){
        return BookResponse.builder()
                .id(this.getId())
                .name(this.getName())
                .genre(this.getGenre())
                .author(this.getAuthor())
                .build();
    }
}
