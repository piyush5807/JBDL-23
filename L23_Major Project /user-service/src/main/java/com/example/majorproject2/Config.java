package com.example.majorproject2;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Properties;

@Configuration
public class Config {

    // Redis configs

    public JedisConnectionFactory getRedisFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(
                "redis-12476.c89.us-east-1-3.ec2.cloud.redislabs.com", 12476
        );
        redisStandaloneConfiguration.setPassword("EDYLWiqGGLSBzi8vHAGANqWhX18QcJp5");
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> getTemplate(){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(getRedisFactory());

        return redisTemplate;
    }

    // Kafka configs

    Properties getKafkaProperties(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return properties;
    }

    ProducerFactory<String, String> getProducerFactory(){
        return new DefaultKafkaProducerFactory(getKafkaProperties());
    }

    @Bean
    KafkaTemplate<String, String> getKafkaTemplate(){
        return new KafkaTemplate(getProducerFactory());
    }
}
