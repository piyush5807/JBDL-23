package com.example.majorproject2;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    private String country;
    private String nationalID;

    private String password;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

}
