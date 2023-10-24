package com.learn.kafka.consumer;

import com.learn.kafka.config.KafkaConfig;
import com.learn.kafka.producer.Producer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;

import static com.learn.kafka.constants.Constant.*;

public class Consumer {
    private static Logger log = LoggerFactory.getLogger(Producer.class);

    public static void main(String[] args) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(KafkaConfig.getConsumerProperties());
        consumer.subscribe(Arrays.asList(kafkaTopic_phones));

        while (runnable) {
            log.info("POLLING");
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(twoSecInMillis));

            for (ConsumerRecord<String, String> record : consumerRecords) {
                log.info("Keys : {}", record.key());
                log.info("Keys : {}", record.value());
            }
        }
    }
}
