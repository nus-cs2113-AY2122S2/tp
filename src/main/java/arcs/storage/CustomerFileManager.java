package arcs.storage;

import arcs.data.customer.Customer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerFileManager {
    private static String DIR_PATH = "data";
    private static final String FILE_NAME = "customer.txt";

    public CustomerFileManager() {
        DIR_PATH = System.getProperty("user.dir") + File.separator + "data";
    }

    /**
     * Loads customer data from storage file.
     *
     * @return a list of customers.
     * @throws IOException if file reading exception occurs.
     */
    public ArrayList<Customer> loadData() throws IOException  {
        File dir = new File(DIR_PATH);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(DIR_PATH + File.separator + FILE_NAME);
        if (!file.exists()) {
            file.createNewFile();
        }
        Scanner s = new Scanner(file);
        ArrayList<String> records = new ArrayList<>();
        while (s.hasNextLine()) {
            records.add(s.nextLine());
        }

        return decodeData(records);
    }

    /**
     * Saves customer data into the storage file.
     *
     * @param customers the list of customers to be stored
     * @throws IOException if errors occur when writing into the storage file.
     */
    public void saveData(ArrayList<Customer> customers) throws IOException {
        FileWriter fw = new FileWriter(DIR_PATH + File.separator + FILE_NAME);
        ArrayList<String> records = encodeData(customers);
        for (String record: records) {
            fw.write(record);
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    private ArrayList<Customer> decodeData(ArrayList<String> records) {
        ArrayList<Customer> customers = new ArrayList<>();
        for (String record: records) {
            String[] data = record.split("/");
            if (data.length != 4) {
                continue;
            }

            customers.add(new Customer(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim()));
        }

        return customers;
    }

    private ArrayList<String> encodeData(ArrayList<Customer> customers) {
        ArrayList<String> records = new ArrayList<>();
        for (Customer customer: customers) {
            records.add(customer.toString());
        }
        return records;
    }
}
