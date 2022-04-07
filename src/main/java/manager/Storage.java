package manager;

import records.Product;
import records.Record;
import records.Subscription;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a <code>manager.Storage</code> object that contains support for
 * saving tasks/loading tasks from storage (ensures persistent storage
 * of tasks)
 */

public class Storage {
    protected static String filePath;
    protected static String totalExpenseFilePath;
    protected static String limitFilePath;
    protected static final char PRODUCT = 'P';
    protected static final char SUBSCRIPTION = 'S';

    public Storage(String filePath, String totalExpenseFilePath, String limitFilePath) {
        this.filePath = filePath;
        this.totalExpenseFilePath = totalExpenseFilePath;
        this.limitFilePath = limitFilePath;
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

                // skips invalid lines
                if (splitMessage.length != 5) {
                    continue;
                }

                if (splitMessage[0] == null) {
                    continue;
                }
                char type = splitMessage[0].charAt(0);

                if (splitMessage[1] == null || splitMessage[1].isBlank()){
                    continue;
                }
                String name = splitMessage[1];

                double price;
                if (splitMessage[2] == null) {
                    price = 0;
                } else {
                    price = Double.parseDouble(splitMessage[2]);
                }

                String date;
                if (splitMessage[3] == null) {
                    date = "";
                } else {
                    date = splitMessage[3];
                }

                try {
                    switch (type) {
                    case PRODUCT:
                        String productType = splitMessage[4];
                        if (productType == null || (!productType.equals("fashion") && !productType.equals("food") &&
                                !productType.equals("accessory"))) {
                            productType = "others";
                        }
                        listArray.add(new Product(name, price, date, productType));
                        break;
                    case SUBSCRIPTION:
                        String renewal = splitMessage[4];
                        listArray.add(new Subscription(name, price, date, renewal));
                        break;
                    default:
                        break;
                    }
                } catch (DateTimeParseException e) {
                    continue;
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
            FileWriter fw = new FileWriter(filePath);
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

    public static double loadTotalExpenseFile() throws FileNotFoundException {
        double totalExp;
        try {
            File f = new File(totalExpenseFilePath);
            f.getParentFile().mkdirs();
            f.createNewFile();
            Scanner s = new Scanner(f);
            if (s.hasNext()) {
                String message = s.nextLine();
                totalExp = Double.parseDouble(message);
                s.close();
                return totalExp;
            }
            else {
                return 0;
            }
        } catch (IOException e) {
            System.out.println("IO exception");
        }
        return 0;
    }

    /**
     * Helper for saveTotalExpense
     * Saves the totalExpense to the filepath of the manager.Storage object
     * @param totalExp is the value to write to file
     */
    public static void saveTotalExpenseFile(double totalExp) throws IOException {
        try {
            FileWriter fw = new FileWriter(totalExpenseFilePath);
            StringBuffer sb = new StringBuffer();
            sb.append(totalExp);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("IO exception");
        }
    }

    public static double loadLimitFile() throws FileNotFoundException {
        double limit;
        try {
            File f = new File(limitFilePath);
            f.getParentFile().mkdirs();
            f.createNewFile();
            Scanner s = new Scanner(f);
            if (s.hasNext()) {
                String message = s.nextLine();
                limit = Double.parseDouble(message);
                s.close();
                return limit;
            }
            else {
                return 0;
            }
        } catch (IOException e) {
            System.out.println("IO exception");
        }
        return 0;
    }

    /**
     * Helper for saveTotalExpense
     * Saves the totalExpense to the filepath of the manager.Storage object
     * @param limit is the value to write to file
     */
    public static void saveLimitFile(double limit) throws IOException {
        try {
            FileWriter fw = new FileWriter(limitFilePath);
            StringBuffer sb = new StringBuffer();
            sb.append(limit);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("IO exception");
        }
    }
}
