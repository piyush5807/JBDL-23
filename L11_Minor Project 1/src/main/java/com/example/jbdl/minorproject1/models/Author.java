package com.example.jbdl.minorproject1.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Author { // Referenced class (Parent)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;

    private String website;

    @OneToMany(mappedBy = "author") // mappedBy param is used to tell which attribute in the child table is acting as a foreign key
    private List<Book> bookList;

}
