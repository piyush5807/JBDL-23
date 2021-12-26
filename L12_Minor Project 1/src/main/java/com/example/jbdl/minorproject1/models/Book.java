package com.example.jbdl.minorproject1.models;

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
    private Author author;

    @JoinColumn
    @ManyToOne // 2 parts Many (Book) to One (Student)
    private Student my_student;

    @OneToMany(mappedBy = "book")
    private List<Request> requestList;

    @CreationTimestamp
    private Date createdOn;
}
