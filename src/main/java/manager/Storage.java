package manager;

import records.Product;
import records.Record;
import records.Subscription;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a <code>manager.Storage</code> object that contains support for
 * saving tasks/loading tasks from storage (ensures persistent storage
 * of tasks)
 */

public class Storage {
    protected static String filePath;
    protected static final char PRODUCT = 'P';
    protected static final char SUBSCRIPTION = 'S';

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Helper for loadRecordList
     * Loads tasks from records.txt (file of saved tasks)
     * Returns ArrayList of Record objects
     */
    public static ArrayList<Record> loadFile() throws IOException {
        try {
            File f = new File(filePath);
            f.getParentFile().mkdirs();
            f.createNewFile();
            Scanner s = new Scanner(f);
            ArrayList<Record> listArray = new ArrayList<>();
            while (s.hasNext()) {
                String message = s.nextLine();
                String[] splitMessage = message.split(" \\| ");
                char type = splitMessage[0].charAt(0);
                String name = splitMessage[1];
                double price= Double.parseDouble(splitMessage[2]);
                String date = splitMessage[3];
                switch (type) {
                    case PRODUCT:
                        String productType = splitMessage[4];
                        listArray.add(new Product(name, price, date, productType));
                        break;
                    case SUBSCRIPTION:
                        String renewal = splitMessage[4];
                        listArray.add(new Subscription(name, price, date, renewal));
                        break;
                    default:
                        break;
                }
            }
            s.close();
            return listArray;
        } catch (IOException e) {
            System.out.println("IO exception");
        }
        return null;
    }
    /**
     * Helper for saveRecordList
     * Saves the list of records to the filepath of the manager.Storage object
     * @param listArray an ArrayList of tasks to write to file
     */
    public static void saveFile(ArrayList<Record> listArray) throws IOException {
        try {
            FileWriter fw = new FileWriter("data/records.txt");
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < listArray.size(); i++) {
                sb.append(listArray.get(i).saveRecords());
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("IO exception");
        }
    }
}
