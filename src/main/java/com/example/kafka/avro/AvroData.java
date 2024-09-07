package com.example.kafka.avro;

import com.example.Customer;
import com.example.kafka.config.KafkaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvroData {

    private static final Logger log = LoggerFactory.getLogger(AvroData.class);

    @Autowired
    private KafkaConfig config;

    @Autowired
    private ReadAndWriteToFile readAndWriteToFile;

    @Autowired
    private AvroMessageKafkaProducer avroMessageKafkaProducer;

    public void generateAvroMessage() throws InterruptedException {
        log.info("AvroData :: generateAvroMessage");

        /*
        processAvroWithFile();
        */

        avroMessageKafkaProducer.sendKafkaMessage();

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
