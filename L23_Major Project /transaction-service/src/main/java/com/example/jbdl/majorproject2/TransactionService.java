package com.example.jbdl.majorproject2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {
    private static Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private final TransactionRepository transactionRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public TransactionService(TransactionRepository transactionRepository,
                              KafkaTemplate<String, String> kafkaTemplate) {
        this.transactionRepository = transactionRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public String doTxn(TransactionRequest transactionRequest) throws JsonProcessingException {

        Transaction transaction = transactionRequest.to();
        transaction = transactionRepository.save(transaction);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sender", transaction.getSender());
        jsonObject.put("receiver", transaction.getReceiver());
        jsonObject.put("amount", transaction.getAmount());
        jsonObject.put("transactionId", transaction.getTransactionId());

        kafkaTemplate.send("TRANSACTION_CREATE",
                objectMapper.writeValueAsString(jsonObject));

        return transaction.getTransactionId();
    }

    @KafkaListener(topics = {"WALLET_UPDATE"}, groupId = "txn123")
    public void updateTxn(String msg) throws Exception{

        JSONObject transactionUpdateRequest =
                objectMapper.readValue(msg, JSONObject.class);

        String txnId = (String)transactionUpdateRequest.getOrDefault("transactionId", null);
        if(txnId == null){
            logger.warn("Txn id is not present in the event, cannot update the transaction status");
            return;
        }


        JSONObject jsonObject = new JSONObject();

        String txnStatus = (String) transactionUpdateRequest.getOrDefault("txnStatus", "PENDING");
        TransactionStatus status = TransactionStatus.valueOf(txnStatus);

        Transaction txnFromDB = transactionRepository.findByTransactionId(txnId);
        txnFromDB.setTransactionStatus(status);

        if(txnFromDB.isSenderNotified()){
            jsonObject.put("notify", false);
        }

        txnFromDB.setSenderNotified(true);
        txnFromDB = transactionRepository.save(txnFromDB);

        jsonObject.put("email", txnFromDB.getSender());
        jsonObject.put("message", "Hi, your transaction with id " + txnId + " is " + txnStatus);

        kafkaTemplate.send("TRANSACTION_COMPLETE", objectMapper.writeValueAsString(jsonObject));
    }
}
