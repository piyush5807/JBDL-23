package com.example.jbdl.minorproject1.repository;

import com.example.jbdl.minorproject1.models.Transaction;
import com.example.jbdl.minorproject1.models.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findTransactionsByRequestBook_IdAndTransactionStatusOrderByTransactionDate(
            int bookId, TransactionStatus transactionStatus);

    List<Transaction> findTransactionByRequest_Book_IdAndTransactionStatusOrderByTransactionDateDesc(int bookId, TransactionStatus transactionStatus);
}
