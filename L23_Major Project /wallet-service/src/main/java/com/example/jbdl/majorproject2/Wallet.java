package com.example.jbdl.majorproject2;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Double balance;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    // user related data
    private String userEmail;
    private String nationalID;
    private String country;

}
