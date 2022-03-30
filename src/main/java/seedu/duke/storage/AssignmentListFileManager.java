package seedu.duke.storage;

import seedu.duke.exceptions.HotelLiteManagerException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AssignmentListFileManager extends FileManager {
    private static final String FILE_PATH = "ListFolder/assignment_list.txt";

    public void load(HashMap<Integer, String> map) throws IOException, HotelLiteManagerException {
        Boolean isNewFile = !isFileExist(FILE_PATH);
        File file = getFile(FILE_PATH);
        if (isNewFile) {
            return;
        }

        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            String line = scan.nextLine();
            String[] splitData = line.split(FILE_SEPARATOR);
            int roomId = Integer.parseInt(splitData[0].trim());
            String name = splitData[1].trim();
            map.put(roomId, name);
        }
    }


    public void save(HashMap<Integer, String> map) throws IOException, HotelLiteManagerException {
        File file = getFile(FILE_PATH);
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        for (Map.Entry<Integer, String> set :
                map.entrySet()) {
            fileWriter.write(set.getKey() + " | " + set.getValue()
                    + System.lineSeparator());
        }
        fileWriter.close();
    }
}
