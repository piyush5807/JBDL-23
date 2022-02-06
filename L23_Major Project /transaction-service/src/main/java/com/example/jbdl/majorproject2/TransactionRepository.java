package com.example.jbdl.majorproject2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

//@Transactional
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {


//    @Modifying
//    @Query("update Transaction t set t.transactionStatus = ?1 where t.transactionId = ?2")
//    void update(TransactionStatus transactionStatus, String transactionId);


    Transaction findByTransactionId(String txnId);
}
