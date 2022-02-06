package com.example.jbdl.majorproject2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final SimpleMailMessage simpleMailMessage;

    public EmailService(JavaMailSender javaMailSender,
                        SimpleMailMessage simpleMailMessage) {

        this.javaMailSender = javaMailSender;
        this.simpleMailMessage = simpleMailMessage;
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = {"WALLET_CREATE", "WALLET_UPDATE", "TRANSACTION_COMPLETE"}, groupId = "notif123")
    public void sendEmail(String msg) throws Exception {
        JSONObject sendEmailRequest =
                objectMapper.readValue(msg, JSONObject.class);

        String txtMsg = (String) sendEmailRequest.getOrDefault("message", "");
        String email = (String) sendEmailRequest.getOrDefault("email", null);
        boolean notify = (Boolean) sendEmailRequest.getOrDefault("notify", true);

        if(email == null || !notify){
            throw new Exception();
        }

        simpleMailMessage.setText(txtMsg);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setFrom("geeks.ewallet@gmail.com");
        simpleMailMessage.setSubject("Wallet update");

        javaMailSender.send(simpleMailMessage);

    }
}
