package com.example.jbdl.minorproject1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Author { // Referenced class (Parent)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String website;

    @OneToMany(mappedBy = "author") // mappedBy param is used to tell which attribute in the child table is acting as a foreign key
    @JsonIgnoreProperties("author")
    private List<Book> bookList;

}
