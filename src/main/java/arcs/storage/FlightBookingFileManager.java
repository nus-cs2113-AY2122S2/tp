package arcs.storage;

import arcs.data.customer.Customer;
import arcs.data.flightbooking.FlightBooking;
import arcs.data.route.Route;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FlightBookingFileManager {
    private String dirPath = "data";
    private static final String FILE_NAME = "flightBooking.txt";

    public FlightBookingFileManager() {
        dirPath = System.getProperty("user.dir") + File.separator + "data";
    }

    /**
     * Loads customer data from storage file.
     *
     * @return a list of flight bookings.
     * @throws IOException if file reading exception occurs.
     */
    public ArrayList<FlightBooking> loadData() throws IOException {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(dirPath + File.separator + FILE_NAME);
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
     * Saves flight booking data into the storage file.
     *
     * @param bookings the list of flight bookings to be stored
     * @throws IOException if errors occur when writing into the storage file.
     */
    public void saveData(ArrayList<FlightBooking> bookings) throws IOException {
        FileWriter fw = new FileWriter(dirPath + File.separator + FILE_NAME);
        ArrayList<String> records = encodeData(bookings);
        for (int i = 0; i < records.size(); i++) {
            fw.write(records.get(i));
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    public ArrayList<FlightBooking> decodeData(ArrayList<String> records) {
        ArrayList<FlightBooking> bookings = new ArrayList<>();
        for (String record: records) {
            String[] data = record.split("/");
            if (data.length != 11) {
                continue;
            }
            Customer customer = new Customer(data[0], data[1], data[2], data[3]);
            Route route = new Route(data[4], data[5], data[6], data[7], data[8], Integer.parseInt(data[9]));
            route.setSold(Integer.parseInt(data[10]));
            bookings.add(new FlightBooking(customer, route));
        }
        return bookings;
    }

    public ArrayList<String> encodeData(ArrayList<FlightBooking> bookings) {
        ArrayList<String> records = new ArrayList<>();
        for (FlightBooking booking: bookings) {
            records.add(booking.toString());
        }
        return records;
    }
}
