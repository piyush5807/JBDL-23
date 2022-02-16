package com.example.jbdl.minorproject1.models;

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
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;
    private int age;

    @Column(unique = true, nullable = false)
    private String rollNo;

    @OneToMany(mappedBy = "my_student")
    @JsonIgnoreProperties({"my_student", "requestList"})
    private List<Book> bookList;

    @OneToMany(mappedBy = "student")
    @JsonIgnoreProperties({"student", "admin", "book", "transaction"})
    private List<Request> requestsCreated;

    @CreationTimestamp
    private Date createdOn;

}
