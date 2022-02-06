package com.example.majorproject2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserCacheRepository userCacheRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private double onboardingAmount;
    private final ObjectMapper objectMapper = new ObjectMapper();


    UserService(UserRepository userRepository,
                UserCacheRepository userCacheRepository,
                KafkaTemplate<String, String> kafkaTemplate,
                @Value("${user.onboarding.reward}") double onboardingAmount){
        this.userCacheRepository = userCacheRepository;
        this.userRepository = userRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.onboardingAmount = onboardingAmount;
    }

    public void createUser(UserCreateRequest userCreateRequest) throws JsonProcessingException {

        User user = userCreateRequest.to();
        user = userRepository.save(user);
        userCacheRepository.save(user);

        // id, email, nationalId, country, amount
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userID", user.getId());
        jsonObject.put("email", user.getEmail());
        jsonObject.put("nationalID", user.getNationalID());
        jsonObject.put("country", user.getCountry());
        jsonObject.put("amount", onboardingAmount);

        kafkaTemplate.send("USER_CREATE",
                objectMapper.writeValueAsString(jsonObject));
    }

    public User getUser(int userId){
        User user = userCacheRepository.get(userId);
        if(user == null){
            user = userRepository.findById(userId).orElse(null);
            if(user != null) {
                userCacheRepository.save(user);
            }
        }
        return user;
    }
}
