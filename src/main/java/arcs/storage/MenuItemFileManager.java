package arcs.storage;

import arcs.data.menuitems.MenuItem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class MenuItemFileManager {

    private static final int TYPE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int PRICE_INDEX = 2;

    private static String DIR_PATH = "data";
    private static final String fileName = "menuItem.txt";

    public MenuItemFileManager() {
        DIR_PATH = System.getProperty("user.dir") + File.separator + "data";
    }

    /**
     * Load the data of Menu Items from text file.
     * @return ArrayList of Menu Items loaded.
     * @throws IOException If text file failed to load.
     */
    public ArrayList<MenuItem> loadData() throws IOException {
        File dir = new File(DIR_PATH);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(DIR_PATH + File.separator + fileName);
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
     * Saves the Menu Items data into a text file.
     * @param menuItems ArrayList of Menu Items to save.
     * @throws IOException If file failed to save.
     */
    public void saveData(ArrayList<MenuItem> menuItems) throws IOException {
        FileWriter fw = new FileWriter(DIR_PATH + File.separator + fileName);
        ArrayList<String> records = encodeData(menuItems);
        for (int i = 0; i < records.size(); i++) {
            fw.write(records.get(i));
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Format the raw data into correct format.
     * @param records Raw input records.
     * @return ArrayList of Menu Items.
     */
    private ArrayList<MenuItem> decodeData(ArrayList<String> records) {
        ArrayList<MenuItem> menuItems = new ArrayList<>();

        for (String record: records) {
            String[] data = record.split("/");
            if (data.length != 3) {
                continue;
            }
            menuItems.add(new MenuItem(data[NAME_INDEX], data[TYPE_INDEX], data[PRICE_INDEX]));
        }

        return menuItems;
    }

    /**
     * Formats the data into the correct format to store in.
     * @param menuItems ArrayList of Menu Items to store.
     * @return Formatted ArrayList of Menu Items to store.
     */
    private ArrayList<String> encodeData(ArrayList<MenuItem> menuItems) {
        ArrayList<String> records = new ArrayList<>();
        for (MenuItem menuItem: menuItems) {
            records.add(menuItem.toString());
        }
        return records;
    }
}
