package com.example.kafka.avro;

import com.example.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class AvroData {

    @Autowired
    private ReadAndWriteToFile readAndWriteToFile;

    public void generateAvroMessage() {

        /*processAvroWithFile();*/

        ExecutorService producerThread = Executors.newSingleThreadExecutor();
        producerThread.submit(new AvroMessageKafkaProducer());

        /*ExecutorService consumerThread = Executors.newSingleThreadExecutor();
        consumerThread.submit(new AvroMessageKafkaProducer());*/

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
