package com.example.kafka.avro;

import com.example.Customer;
import com.example.kafka.config.KafkaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class AvroData {

    private static final Logger log = LoggerFactory.getLogger(AvroData.class);

    @Autowired
    private KafkaConfig config;

    @Autowired
    private ReadAndWriteToFile readAndWriteToFile;

    public void generateAvroMessage() throws InterruptedException {
        log.info("AvroData :: generateAvroMessage");

        /*
        processAvroWithFile();
        */

        ExecutorService publishMessageService = Executors.newSingleThreadExecutor();
        publishMessageService.submit(new AvroMessageKafkaProducer(config));

        ExecutorService pollKafkaService = Executors.newSingleThreadExecutor();
        pollKafkaService.submit(new AvroMessageKafkaConsumer(config));

    }

    /**
     * Read and Write to a avroFile
     */
    private void processAvroWithFile() {
        Customer.Builder customerBuilder = Customer.newBuilder();
        customerBuilder.setFirstName("Mark");
        customerBuilder.setLastName("Simpson");
        customerBuilder.setAge(30);
        customerBuilder.setAutomatedEmail(true);
        Customer customer = customerBuilder.build();

        readAndWriteToFile.writeAvroToDataFile(customer);
        readAndWriteToFile.readAvroDataFromFile();
    }
}
