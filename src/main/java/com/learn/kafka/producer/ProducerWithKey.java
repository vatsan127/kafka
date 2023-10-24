package com.learn.kafka.producer;

import com.learn.kafka.config.KafkaConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.learn.kafka.constants.Constant.*;

public class ProducerWithKey {

    private static Logger log = LoggerFactory.getLogger(Producer.class);

    public static void main(String[] args) {

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(KafkaConfig.getProducerProperties());
        for (String key : kafkaKeys_List) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(kafkaTopic_phones, key, "kafka with " + key);
            kafkaProducer.send(producerRecord, (metadata, exception) -> {
                if (exception == null) {
                    log.info("Topic : {}", metadata.topic());
                    log.info("Partition : {}", metadata.partition());
                    log.info("Key : {}", key);
                    log.info("Offset : {}", metadata.offset());
                } else {
                    log.error("Error : {}", exception);
                }
            });

        }
        kafkaProducer.flush();
        kafkaProducer.close();
    }
}