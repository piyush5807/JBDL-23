package com.example.jbdl.majorproject2;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.Properties;

@Configuration
public class Config {


    Properties getKafkaProperties(){
        Properties properties = new Properties();
        // Producer related props
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);


        // Consumer related props
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return properties;
    }

    ProducerFactory<String, String> getProducerFactory(){
        return new DefaultKafkaProducerFactory(getKafkaProperties());
    }

    ConsumerFactory<String, String> getConsumerFactory(){
        return new DefaultKafkaConsumerFactory(getKafkaProperties());
    }

    @Bean
    KafkaTemplate<String, String> getKafkaTemplate(){
        return new KafkaTemplate(getProducerFactory());
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, String> getListenerFactory(){
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(getConsumerFactory());
        return factory;

    }
}
