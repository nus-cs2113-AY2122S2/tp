package seedu.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import seedu.equipment.DuplicateSerialNumberException;
import seedu.equipment.Equipment;
import seedu.equipment.EquipmentManager;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Storage {
    public static final String FILE_NOT_FOUND_ERROR_MESSAGE = "Save file not found: "
            + "a new file will be created after this session.";
    public static final String SAVE_ERROR_MESSAGE = "An error occurred while saving!";
    public static final String DUPLICATE_SERIAL_NUMBER_ERROR = "Duplicate serial number found!";
    public static final String path = "./equipments.json";
    private static final String EMPTY_FILE_ERROR_MESSAGE = "File is empty. Added Equipments will be saved in the file.";
    private static final String INVALID_DATE_ERROR_MESSAGE = "Invalid date format found in the file. "
            + "Please check the file and try again. Do not proceed unless you want to rewrite the file.";
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    /**
     * Loads data from ./equipments.json
     *
     * @param equipmentManager EquipmentManager object to put all the Equipments in ./equipments.json.
     */
    public void loadData(EquipmentManager equipmentManager) {
        try {
            Reader reader = Files.newBufferedReader(Path.of(path));
            List<Equipment> equipments = gson.fromJson(reader, new TypeToken<List<Equipment>>() {}.getType());
            for (Equipment equipment : equipments) {
                equipmentManager.addEquipment(equipment);
            }
        } catch (IOException e) {
            System.out.println(FILE_NOT_FOUND_ERROR_MESSAGE);
        } catch (DuplicateSerialNumberException e) {
            System.out.println(DUPLICATE_SERIAL_NUMBER_ERROR);
        } catch (NullPointerException e) {
            System.out.println(EMPTY_FILE_ERROR_MESSAGE);
        } catch (DateTimeException e) {
            System.out.println(INVALID_DATE_ERROR_MESSAGE);
        }
    }

    /**
     * Saves the Equipments in equipmentManager into a json file.
     *
     * @param equipmentManager EquipmentManager object with Equipments that will be saved to the json file.
     */
    public void saveData(EquipmentManager equipmentManager) {
        List<Equipment> equipments = new ArrayList<>(equipmentManager.getEquipmentList().values());
        try {
            Writer writer = Files.newBufferedWriter(Path.of(path));
            gson.toJson(equipments, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println(SAVE_ERROR_MESSAGE);
        }
    }
}
