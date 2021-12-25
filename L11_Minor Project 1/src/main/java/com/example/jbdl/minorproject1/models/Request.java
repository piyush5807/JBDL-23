package com.example.jbdl.minorproject1.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Request {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    @JoinColumn
    @ManyToOne
    private Student student;

    @JoinColumn
    @ManyToOne
    private Admin admin;

    @JoinColumn
    @ManyToOne
    private Book book;

    @CreationTimestamp
    private Date requestDate;

    @Enumerated(value = EnumType.STRING)
    private RequestStatus requestStatus;
}
