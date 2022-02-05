package com.example.jbdl.majorproject2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public WalletService(WalletRepository walletRepository,
                         KafkaTemplate<String, String> kafkaTemplate) {
        this.walletRepository = walletRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = {"USER_CREATE"}, groupId = "group123")
    public void createWallet(String message) throws JsonProcessingException {

        JSONObject walletCreateRequest =
                objectMapper.readValue(message, JSONObject.class);

        Wallet wallet = to(walletCreateRequest);
        wallet = walletRepository.save(wallet);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("walletID", wallet.getId());
        jsonObject.put("balance", wallet.getBalance());
        jsonObject.put("email", wallet.getUserEmail());

        kafkaTemplate.send("WALLET_CREATE",
                objectMapper.writeValueAsString(jsonObject));
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
                .userId((Integer) walletRequest
                        .getOrDefault("userID", null))
                .build();
    }
}
