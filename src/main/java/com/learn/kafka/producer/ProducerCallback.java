package com.learn.kafka.producer;

import com.learn.kafka.config.KafkaConfig;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.learn.kafka.constants.Constant.kafkaTopic_myTopic;

public class ProducerCallback {
    private static Logger log = LoggerFactory.getLogger(Producer.class);

    public static void main(String[] args) {

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(KafkaConfig.getProducerProperties());
        ProducerRecord<String, String> record = new ProducerRecord<>(kafkaTopic_myTopic, "GOOD MORNING");

        /*
        kafkaProducer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception e) {
                if (e == null) {
                    log.info("Topic : {}", metadata.topic());
                    log.info("Partition : {}", metadata.partition());
                    log.info("Offset : {}", metadata.offset());
                }
            }
        });
         */

        /*callback using lambda*/
        kafkaProducer.send(record , (metadata, e) -> {
            if(e == null){
                log.info("Topic : {}", metadata.topic());
                log.info("Partition : {}", metadata.partition());
                log.info("Offset : {}", metadata.offset());
            }
        });
        kafkaProducer.flush();
        kafkaProducer.close();

    }
}
