package com.example.jbdl.minorproject1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Admin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "admin")
    @JsonIgnoreProperties("admin")
    private List<Request> requestsToProcess;

    @CreationTimestamp
    private Date createdOn;

    @OneToOne
    @JoinColumn
    @JsonIgnoreProperties({"admin", "student", "password"})
    private User user;

}
