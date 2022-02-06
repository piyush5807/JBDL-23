package com.example.jbdl.majorproject2;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String transactionId;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    private String txnMessage;

    private String sender;
    private String receiver;

    private double amount;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    private boolean senderNotified;

}
