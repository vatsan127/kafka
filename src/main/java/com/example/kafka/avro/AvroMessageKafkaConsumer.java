package com.example.kafka.avro;

import com.example.TestAvro;
import com.example.kafka.config.KafkaConfig;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

@Slf4j
public class AvroMessageKafkaConsumer implements Runnable {

    private KafkaConfig config;

    public AvroMessageKafkaConsumer(KafkaConfig config) {
        this.config = config;
    }

    private Properties getKafkaConsumerProperties() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getBootstrapServers());
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, config.getGroupId());
        properties.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, config.getSchemaRegistryUrl());
        properties.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, "true");
        return properties;
    }

    public void consumeKafkaMessage() throws InterruptedException {
        try (KafkaConsumer<String, TestAvro> consumer = new KafkaConsumer<>(getKafkaConsumerProperties())) {
            consumer.subscribe(Collections.singleton(config.getAvroTopic()));
            while (true) {
                ConsumerRecords<String, TestAvro> records = consumer.poll(500);
                for (ConsumerRecord<String, TestAvro> record : records) {
                    log.info("consumed record :: key: {}, value: {}", record.key(), record.value());
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            consumeKafkaMessage();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
