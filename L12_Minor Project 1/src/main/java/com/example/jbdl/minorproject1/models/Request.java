package com.example.jbdl.minorproject1.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Request {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    private String requestId;

    @JoinColumn
    @ManyToOne
    private Student student;

    @JoinColumn
    @ManyToOne
    private Admin admin;

    @JoinColumn
    @ManyToOne
    private Book book;

    @OneToOne(mappedBy = "request")
    private Transaction transaction;

    @CreationTimestamp
    private Date requestDate;

    @Enumerated(value = EnumType.STRING)
    private RequestStatus requestStatus;

    @Enumerated(value = EnumType.STRING)
    private RequestType requestType;
}
