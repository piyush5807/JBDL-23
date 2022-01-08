package com.example.jbdl.minorproject1;

import com.example.jbdl.minorproject1.models.Transaction;
import com.example.jbdl.minorproject1.models.TransactionStatus;
import com.example.jbdl.minorproject1.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class MinorProject1Application implements CommandLineRunner {

	@Autowired
	TransactionRepository transactionRepository;

	public static void main(String[] args) {
		SpringApplication.run(MinorProject1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

////		List<Transaction> transactionList = transactionRepository.
////				findTransactionsByRequestBook_IdAndTransactionStatusOrderByTransactionDateDesc(3, TransactionStatus.SUCCESS);
//
//		transactionRepository.findTransactionsByRequest_Book_IdAndTransactionStatusOrderByTransactionDateAsc(3, TransactionStatus.SUCCESS);
//
////		for(Transaction transaction : transactionList){
////			System.out.println(transaction.getId());
////		}
	}
}
