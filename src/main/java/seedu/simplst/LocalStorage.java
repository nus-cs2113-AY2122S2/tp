package seedu.simplst;

import org.json.simple.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Storage class that handles save file IO for task list.
 */
public class LocalStorage {
    private static final String OUT_DIR = "output";
    public static final String WAREHOUSE_PATH = String.format("%s/%s", OUT_DIR, "WAREHOUSE.json");

    /**
     * Opens and reads file at SAVE_PATH.
     *
     * @return saveStr the JSON string from save file
     */
    public static String readSaveFile(String filePath) {
        String saveStr = "";
        try {
            FileReader fr = new FileReader(filePath);
            int i;
            while ((i = fr.read()) != -1) {
                char newChar = ((char) i);
                saveStr += (newChar);
            }
            fr.close();
        } catch (IOException e) {
            System.err.println("Failed to open save file! " + e.getMessage() + "\n");
        }
        return saveStr;
    }

    /**
     * Opens and writes serialised string versino of tasklist to file at SAVE_PATH.
     */

    public static Boolean writeSaveFile(String storeStr, String filePath) {
        Path dir = Paths.get(filePath).toAbsolutePath().normalize().getParent();
        if (!Files.exists(dir)) {
            try {
                Files.createDirectory(dir);
                System.out.println("Output Directory created at current directory!");
            } catch (IOException e) {
                System.err.println("Failed to create directory! " + e.getMessage());
                return false;
            }
        }
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(storeStr);
            fw.close();
        } catch (IOException e) {
            System.err.println("Failed to write to save file!" + e.getMessage());
            return false;
        }
        return true;
    }

}
