package com.example.jbdl.minorproject1;

import com.example.jbdl.minorproject1.models.*;
import com.example.jbdl.minorproject1.repository.TransactionRepository;
import com.example.jbdl.minorproject1.service.BookService;
import com.example.jbdl.minorproject1.service.RequestService;
import com.example.jbdl.minorproject1.service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @InjectMocks
    TransactionService transactionService;

    // TransactionService ts = new TransactionService();

    @Mock
    BookService bookService;

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    RequestService requestService;

    @Test
    public void createTransactionTest_Issue() throws Exception {

        Book book = Book.builder()
                .id(2000)
                .name("Random book name")
                .build();


        Student student = Student.builder()
                .id(3000)
                .name("Ram")
                .build();

        Book updatedBook = Book.builder()
                .id(2000)
                .my_student(student)
                .name("Random book name")
                .build();

        Request request = Request.builder()
                .id(1000)
                .book(book)
                .student(student)
                .requestType(RequestType.ISSUE)
                .build();

        Transaction transaction = Transaction.builder()
                .id(4000)
                .externalTransactionId(UUID.randomUUID().toString())
                .transactionDate(new Date(System.currentTimeMillis()))
                .build();

        when(transactionRepository.save(any())).thenReturn(transaction);
//        when(requestService.getRequestById(request.getId())).thenReturn(requestByService);


        when(bookService.createOrUpdateBook(any())).thenReturn(updatedBook);

        String txnId = transactionService.createTransaction(request);

        verify(bookService, times(1)).createOrUpdateBook(any());
        verify(transactionRepository, times(2)).save(any());
        verify(requestService, times(0)).getRequestById(request.getId());

    }

    @Test(expected = Exception.class)
    public void createTransactionTest_Return_Negative() throws Exception{
        Book book = Book.builder()
                .id(2000)
                .name("Random book name")
                .build();


        Student student = Student.builder()
                .id(3000)
                .name("Ram")
                .build();

        Request returnRequest = Request.builder()
                .id(1000)
                .book(book)
                .student(student)
                .requestType(RequestType.RETURN)
                .build();

        Request issueRequest = Request.builder()
                .requestType(RequestType.RETURN)
                .build();

        List<Transaction> transactionList = Arrays.asList(
                Transaction.builder()
                        .request(issueRequest)
                        .transactionDate(new Date(1639032314000l))
                        .build()
        );

        when(transactionRepository.findTransactionByRequest_Book_IdAndTransactionStatusOrderByTransactionDateDesc(book.getId(), TransactionStatus.SUCCESS))
                .thenReturn(transactionList);

//        when(transactionRepository.save(any())).thenReturn(transaction);
//
//        when(bookService.createOrUpdateBook(any())).thenReturn(updatedBook);

        String txnId = transactionService.createTransaction(returnRequest);

//        verify(bookService, times(1)).createOrUpdateBook(any());
//        verify(transactionRepository, times(2)).save(any());
//        verify(requestService, times(0)).getRequestById(returnRequest.getId());

    }
}
