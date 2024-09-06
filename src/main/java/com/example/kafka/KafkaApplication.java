package com.example.kafka;

import com.example.kafka.producer.KafkaProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(KafkaApplication.class, args);
        KafkaProducer kafkaProducer = applicationContext.getBean(KafkaProducer.class);
        while (Boolean.TRUE) {
            kafkaProducer.sendMessage();
            TimeUnit.SECONDS.sleep(10);
        }
    }

}
