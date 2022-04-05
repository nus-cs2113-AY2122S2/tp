package arcs.storage;

import arcs.data.menuitems.MenuItem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class MenuItemFileManager {
    private static String DIR_PATH = "data";
    private static final String fileName = "menuItem.txt";

    public MenuItemFileManager() {
        DIR_PATH = System.getProperty("user.dir") + File.separator + "data";
    }

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
    public void saveData(ArrayList<MenuItem> menuItems) throws IOException {
        FileWriter fw = new FileWriter(DIR_PATH + File.separator + fileName);
        ArrayList<String> records = encodeData(menuItems);
        for (int i = 0; i < records.size(); i++) {
            fw.write(records.get(i));
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    private ArrayList<MenuItem> decodeData(ArrayList<String> records) {
        ArrayList<MenuItem> menuItems = new ArrayList<>();

        for (String record: records) {
            String[] data = record.split("/");
            if (data.length != 3) {
                continue;
            }
            menuItems.add(new MenuItem(data[0], data[1], data[2]));
        }

        return menuItems;
    }

    private ArrayList<String> encodeData(ArrayList<MenuItem> menuItems) {
        ArrayList<String> records = new ArrayList<>();
        for (MenuItem menuItem: menuItems) {
            records.add(menuItem.toString());
        }
        return records;
    }
}
