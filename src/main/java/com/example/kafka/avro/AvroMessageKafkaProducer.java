package com.example.kafka.avro;

import com.example.TestAvro;
import com.example.kafka.config.KafkaConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Slf4j
public class AvroMessageKafkaProducer implements Runnable {

    private KafkaConfig config;

    public AvroMessageKafkaProducer(KafkaConfig config) {
        this.config = config;
    }

    private Properties getStringTestAvroKafkaProducer() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getBootstrapServers());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        properties.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, config.getSchemaRegistryUrl());
        return properties;
    }

    public void sendKafkaMessage() throws InterruptedException {
        KafkaProducer<String, TestAvro> producer = new KafkaProducer<>(getStringTestAvroKafkaProducer());
        while (true) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            TestAvro.Builder testAvroBuilder = TestAvro.newBuilder();
            testAvroBuilder.setLocalDateTime(
                    LocalDateTime.now(ZoneId.of("Asia/Kolkata")).format(formatter)
            );
            TestAvro testAvro = testAvroBuilder.build();

            ProducerRecord<String, TestAvro> record = new ProducerRecord<>(config.getAvroTopic(), testAvro);
            producer.send(record);
            log.info("AvroMessageKafkaProducer :: message sent :: {} ", testAvro);
            TimeUnit.SECONDS.sleep(10);
        }
    }

    @Override
    public void run() {
        try {
            sendKafkaMessage();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
