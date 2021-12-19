package com.example.demojpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    TransactionDAO transactionDAO;

    @PostMapping("/transaction")
    public Transaction createTransaction(@RequestBody Transaction transaction){
        return transactionDAO.save(transaction);
    }
}
