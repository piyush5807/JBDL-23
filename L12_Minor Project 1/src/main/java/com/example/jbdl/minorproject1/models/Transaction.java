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
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Only for outside world
    private String externalTransactionId; // like 501a26b4-0366-44ec-b537-c3d39d51f67c

    @OneToOne
    @JoinColumn
    private Request request;

    @CreationTimestamp
    private Date transactionDate;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    private Double fine;
}