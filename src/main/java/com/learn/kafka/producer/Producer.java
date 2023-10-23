package com.learn.kafka.producer;

import com.learn.kafka.config.KafkaConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.learn.kafka.constants.Constant.kafkaTopic_myTopic;


public class Producer {
    private static Logger log = LoggerFactory.getLogger(Producer.class);

    public static void main(String[] args) {
        log.info("Fetching Kafka Properties");
        /*create kafka producer with properties*/
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(KafkaConfig.getProducerProperties());

        /*create kafka record */
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(kafkaTopic_myTopic, "Hello");

        log.info("Sending kafka Record");
        kafkaProducer.send(producerRecord);

        /*to make it synchronous*/
        kafkaProducer.flush();
        kafkaProducer.close();
    }
}
