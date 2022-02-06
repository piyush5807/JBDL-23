package com.example.jbdl.majorproject2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private static Logger logger = LoggerFactory.getLogger(WalletService.class);

    private final WalletRepository walletRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public WalletService(WalletRepository walletRepository,
                         KafkaTemplate<String, String> kafkaTemplate) {
        this.walletRepository = walletRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = {"USER_CREATE"}, groupId = "waqllet123")
    public void createWallet(String message) throws JsonProcessingException {

        JSONObject walletCreateRequest =
                objectMapper.readValue(message, JSONObject.class);

        Wallet wallet = to(walletCreateRequest);
        wallet = walletRepository.save(wallet);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("walletID", wallet.getId());
        jsonObject.put("balance", wallet.getBalance());
        jsonObject.put("email", wallet.getUserEmail());
        jsonObject.put("message", "Hi, your wallet has created with default balance as " + wallet.getBalance());

        kafkaTemplate.send("WALLET_CREATE",
                objectMapper.writeValueAsString(jsonObject));
    }


    @KafkaListener(topics = {"TRANSACTION_CREATE"}, groupId = "wallet123")
    public void updateWallet(String msg) throws Exception {
        JSONObject walletUpdateRequest =
                objectMapper.readValue(msg, JSONObject.class);

        JSONObject senderWalletUpdateEvent = new JSONObject();

        String receiver = (String) walletUpdateRequest.getOrDefault("receiver", null);
        String sender = (String) walletUpdateRequest.getOrDefault("sender", null);
        Double amount = (Double) walletUpdateRequest.getOrDefault("amount", null);
        String transactionId = (String) walletUpdateRequest.getOrDefault("transactionId", null);

        senderWalletUpdateEvent.put("transactionId", transactionId);

        if(receiver == null || sender == null || amount == null || amount == 0.0){
            logger.warn("Either receiver, sender or amount is not correct");
            senderWalletUpdateEvent.put("txnStatus", "FAILED");
            kafkaTemplate.send("WALLET_UPDATE", objectMapper.writeValueAsString(senderWalletUpdateEvent));
            return;
        }

        Wallet senderWallet = walletRepository.findByUserEmail(sender); // 10 w1
        Wallet receiverWallet = walletRepository.findByUserEmail(receiver); // 10 w2

        if(receiverWallet == null || senderWallet == null || senderWallet.getBalance() < amount){
            logger.warn("Either wallets does not exist or sender has insufficient balance");
            senderWalletUpdateEvent.put("txnStatus", "FAILED");
            kafkaTemplate.send("WALLET_UPDATE", objectMapper.writeValueAsString(senderWalletUpdateEvent));
            return;
        }

        senderWallet.setBalance(senderWallet.getBalance() - amount); // 9 w1
        receiverWallet.setBalance(receiverWallet.getBalance() + amount); // 11 w2

        walletRepository.save(senderWallet); // 9 w1 --> 1
        walletRepository.save(receiverWallet); // 11 w2 --> 1

        senderWalletUpdateEvent.put("txnStatus", "SUCCESS");
        senderWalletUpdateEvent.put("email", sender);
        senderWalletUpdateEvent.put("message", "Hi, your wallet has been debited by amount " + amount);

        kafkaTemplate.send("WALLET_UPDATE", objectMapper.writeValueAsString(senderWalletUpdateEvent));

        JSONObject receiverWalletUpdateEvent = new JSONObject();
        receiverWalletUpdateEvent.put("transactionId", transactionId);
        receiverWalletUpdateEvent.put("txnStatus", "SUCCESS");
        receiverWalletUpdateEvent.put("email", receiver);
        receiverWalletUpdateEvent.put("message", "Hi, your wallet has been credited by amount " + amount);

        kafkaTemplate.send("WALLET_UPDATE", objectMapper.writeValueAsString(receiverWalletUpdateEvent));

    }

    private Wallet to(JSONObject walletRequest){
        return Wallet.builder()
                .nationalID((String)walletRequest
                        .getOrDefault("nationalID", null))
                .balance((Double)walletRequest
                        .getOrDefault("amount", 0.0))
                .country((String) walletRequest
                        .getOrDefault("country", null))
                .userEmail((String) walletRequest
                        .getOrDefault("email", null))
                .build();
    }
}
