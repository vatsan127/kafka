package com.example.kafka.avro;

import com.example.Customer;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;


@Slf4j
@Component
public class ReadAndWriteToFile {

    private static String FILE_PATH = "src/main/sample/customer-specific.avro";

    /**
     * Method to Write avro data to file
     */

    public void writeAvroToDataFile(Customer customer) {
        final DatumWriter<Customer> datumWriter = new SpecificDatumWriter<>(Customer.class);

        try (DataFileWriter<Customer> dataFileWriter = new DataFileWriter<>(datumWriter)) {
            dataFileWriter.create(customer.getSchema(), new File(FILE_PATH));
            dataFileWriter.append(customer);
            System.out.println("successfully wrote customer-specific.avro");
        } catch (IOException e) {
            log.error("Exception while writing data - {}", String.valueOf(e));
        }
    }

    /**
     * Method to Read avro data from file
     */

    public void readAvroDataFromFile() {
        final File file = new File(FILE_PATH);
        final DatumReader<Customer> datumReader = new SpecificDatumReader<>(Customer.class);
        System.out.println("Reading our specific record");
        try (DataFileReader<Customer> dataFileReader = new DataFileReader<>(file, datumReader)) {
            while (dataFileReader.hasNext()) {
                Customer readCustomer = dataFileReader.next();
                System.out.println(readCustomer.toString());
            }
        } catch (IOException e) {
            log.error("Exception while reading data - {}", String.valueOf(e));
        }
    }


}
