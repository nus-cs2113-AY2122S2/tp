package arcs.storage;

import arcs.data.staff.Staff;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StaffFileManager {
    private static String DIR_PATH = "data";
    private static final String FILE_NAME = "Staff.txt";

    public StaffFileManager() {
        DIR_PATH = System.getProperty("user.dir") + File.separator + "data";
    }

    public ArrayList<Staff> loadData() throws IOException  {
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


    public void saveData(ArrayList<Staff> Staffs) throws IOException {
        FileWriter fw = new FileWriter(DIR_PATH + File.separator + FILE_NAME);
        ArrayList<String> records = encodeData(Staffs);
        for (String record: records) {
            fw.write(record);
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    private ArrayList<Staff> decodeData(ArrayList<String> records) {
        ArrayList<Staff> Staffs = new ArrayList<>();
        for (String record: records) {
            String[] data = record.split("/");
            if (data.length != 6) {
                continue;
            }

            Staffs.add(new Staff(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(),data[4].trim(),data[5].trim()));
        }

        return Staffs;
    }

    private ArrayList<String> encodeData(ArrayList<Staff> Staffs) {
        ArrayList<String> records = new ArrayList<>();
        for (Staff Staff: Staffs) {
            records.add(Staff.toString());
        }
        return records;
    }
}
