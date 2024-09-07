package com.example.kafka;

import com.example.kafka.avro.AvroData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(KafkaApplication.class, args);
        AvroData avroData = applicationContext.getBean(AvroData.class);
        avroData.generateAvroMessage();
    }

}
