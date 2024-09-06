package com.example.kafka.avro;

import com.example.Customer;

public class AvroProcessing {
    public void generateAvroData() {

        Customer.Builder customerBuilder = Customer.newBuilder();
        customerBuilder.setAge(30);
        customerBuilder.setFirstName("Mark");
        customerBuilder.setLastName("Simpson");
        customerBuilder.setAutomatedEmail(true);
        customerBuilder.setHeight(180f);
        customerBuilder.setWeight(90f);

        Customer customer = customerBuilder.build();
        ReadAndWriteToFile readAndWriteToFile = new ReadAndWriteToFile();

        readAndWriteToFile.writeAvroToDataFile(customer);
        readAndWriteToFile.readAvroDataFromFile();

    }
}
