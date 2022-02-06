package com.example.jbdl.majorproject2;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class Config {

    Properties getKafkaProperties(){
        Properties properties = new Properties();

        // Consumer related props
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return properties;
    }

    ConsumerFactory<String, String> getConsumerFactory(){
        return new DefaultKafkaConsumerFactory(getKafkaProperties());
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, String> getListenerFactory(){
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(getConsumerFactory());
        return factory;

    }

    // smtp - simple mail transfer protocol

    @Bean
    JavaMailSender getMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setUsername("geeks.ewallet@gmail.com");
        javaMailSender.setPassword("geeks@123");
        javaMailSender.setPort(587);

        Properties properties = javaMailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.debug", true); // Not necessary, helps in getting debug messages of java mail sender

        return javaMailSender;
    }

    @Bean
    SimpleMailMessage getMailMessage(){
        return new SimpleMailMessage();
    }
}
