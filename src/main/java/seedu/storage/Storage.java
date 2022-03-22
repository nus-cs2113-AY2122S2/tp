package seedu.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import seedu.equipment.DuplicateSerialNumberException;
import seedu.equipment.Equipment;
import seedu.equipment.EquipmentManager;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class Storage {
    public static final String FILE_NOT_FOUND_ERROR_MESSAGE = "File not found! "
            + "A new file will be created after this session.";
    public static final String SAVE_ERROR_MESSAGE = "An error occurred while saving!";
    public static final String DUPLICATE_SERIAL_NUMBER_ERROR = "Duplicate serial number found!";
    public static final String path = "./equipments.json";
    private final Gson gson = new Gson();

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
        }
    }

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
