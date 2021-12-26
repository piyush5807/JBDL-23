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
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "admin")
    private List<Request> requestsToProcess;

    @CreationTimestamp
    private Date createdOn;


}
