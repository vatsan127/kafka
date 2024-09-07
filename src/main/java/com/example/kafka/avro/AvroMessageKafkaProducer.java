package com.example.kafka.avro;

import com.example.TestAvro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Service
public class AvroMessageKafkaProducer implements Runnable {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Override
    public void run() {
        while (true) {
            TestAvro.Builder testAvroBuilder = TestAvro.newBuilder();
            testAvroBuilder.setLocalDateTime(String.valueOf(LocalDateTime.now()));
            TestAvro testAvro = testAvroBuilder.build();
            kafkaTemplate.sendDefault(testAvro);
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
