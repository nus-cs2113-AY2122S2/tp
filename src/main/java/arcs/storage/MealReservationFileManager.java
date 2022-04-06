package arcs.storage;

import arcs.data.customer.Customer;
import arcs.data.mealreservation.MealReservation;
import arcs.data.menuitems.MenuItem;
import arcs.data.route.Route;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MealReservationFileManager {

    private static final int TYPE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int PRICE_INDEX = 2;

    private String dirPath = "data";
    private static final String FILE_NAME = "mealReservation.txt";

    public MealReservationFileManager() {
        dirPath = System.getProperty("user.dir") + File.separator + "data";
    }

    /**
     * Loads user Data from file.
     * @return ArrayList of Meal Reservations loaded.
     * @throws IOException If File cannot be loaded.
     */
    public ArrayList<MealReservation> loadData() throws IOException {
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
     * Saves the Meal Reservation Data into a text file.
     * @param mealReservations Array of Meal Reservations to save.
     * @throws IOException If saving failed.
     */
    public void saveData(ArrayList<MealReservation> mealReservations) throws IOException {
        FileWriter fw = new FileWriter(dirPath + File.separator + FILE_NAME);
        ArrayList<String> records = encodeData(mealReservations);
        for (int i = 0; i < records.size(); i++) {
            fw.write(records.get(i));
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    /**
     * To format the Meal Reservation Data into the correct format to load.
     * @param records Record of Meal Reservations from the text file.
     * @return ArrayList of Meal Reservations.
     */
    public ArrayList<MealReservation> decodeData(ArrayList<String> records) {
        ArrayList<MealReservation> mealReservations = new ArrayList<>();
        ArrayList<MenuItem> reservedMenuItems = new ArrayList<>();
        for (String record: records) {
            String[] data = record.split("/");
            int recordLength = data.length;
            Customer customer = new Customer(data[0], data[1], data[2], data[3]);
            Route route = new Route(data[4], data[5], data[6], data[7], data[8], Integer.parseInt(data[9]));
            int index = 11;
            while (data[index] != null) {
                String[] menuItemData = data[index].split("\\|");
                MenuItem menuItem = new MenuItem(menuItemData[NAME_INDEX], menuItemData[TYPE_INDEX],
                        menuItemData[PRICE_INDEX]);
                reservedMenuItems.add(menuItem);
                index++;
                if (index == recordLength) {
                    break;
                }
            }
            mealReservations.add(new MealReservation(customer,route,reservedMenuItems));
        }
        return mealReservations;
    }

    /**
     * Format Meal Reservations into the proper format for storing.
     * @param mealReservations ArrayList of Meal Reservations to Store.
     * @return ArrayList of Meal Reservations in correct format to store.
     */
    public ArrayList<String> encodeData(ArrayList<MealReservation> mealReservations) {
        ArrayList<String> records = new ArrayList<>();
        for (MealReservation mealReservation: mealReservations) {
            records.add(mealReservation.toString());
        }
        return records;
    }
}
