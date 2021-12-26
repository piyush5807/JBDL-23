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
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int age;
    private String rollNo;

    @OneToMany(mappedBy = "my_student")
    private List<Book> bookList;

    @OneToMany(mappedBy = "student")
    private List<Request> requestsCreated;

    @CreationTimestamp
    private Date createdOn;

}
