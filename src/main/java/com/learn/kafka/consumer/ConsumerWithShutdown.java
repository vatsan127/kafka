package com.learn.kafka.consumer;

import com.learn.kafka.config.KafkaConfig;
import com.learn.kafka.producer.Producer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;

import static com.learn.kafka.constants.Constant.*;

public class ConsumerWithShutdown {
    private static Logger log = LoggerFactory.getLogger(Producer.class);

    public static void main(String[] args) {

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(KafkaConfig.getConsumerProperties());
        consumer.subscribe(Arrays.asList(kafkaTopic_phones));

        /*main thread */
        final Thread mainThread = Thread.currentThread();
        try {

            /*shutdown hook*/
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    log.info("Exiting --- calling consumer.wakeup()");
                    consumer.wakeup();

                    try {
                        mainThread.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            });

            while (runnable) {
                log.info("POLLING");
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(twoSecInMillis));
                for (ConsumerRecord<String, String> record : records) {
                    log.info("Keys : {}", record.key());
                    log.info("Keys : {}", record.value());
                }
            }
        } catch (WakeupException we) {
            log.info("Cosumer is Shutting Down ");
        }

    }
}
