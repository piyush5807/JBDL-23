package com.example.jbdl.majorproject2;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionRequest {

    @NotNull
    private String sender;

    @NotNull
    private String receiver;

    @NotNull
    private double amount;

    private String txnMessage;

    public Transaction to(){
        return Transaction.builder()
                .amount(amount)
                .sender(sender)
                .receiver(receiver)
                .txnMessage(txnMessage)
                .transactionId(UUID.randomUUID().toString())
                .transactionStatus(TransactionStatus.PENDING)
                .build();
    }
}
