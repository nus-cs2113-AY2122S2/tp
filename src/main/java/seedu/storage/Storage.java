package seedu.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import seedu.equipment.DuplicateSerialNumber;
import seedu.equipment.Equipment;
import seedu.equipment.EquipmentManager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class Storage {
    public final String path = "./equipments.json";
    private final Gson gson = new Gson();

    public void loadData(EquipmentManager equipmentManager) throws DuplicateSerialNumber {
        try {
            Reader reader = Files.newBufferedReader(Path.of(path));
            List<Equipment> equipments = gson.fromJson(reader, new TypeToken<List<Equipment>>() {}.getType());
            for (Equipment equipment : equipments) {
                equipmentManager.addEquipment(equipment);
            }
        } catch (IOException e) {
            System.out.println("io1");
        }
    }

    public void saveData(EquipmentManager equipmentManager) {
        List<Equipment> equipments = new ArrayList<>(equipmentManager.getEquipmentList().values());
        try {
            Writer writer = Files.newBufferedWriter(Path.of(path));
            gson.toJson(equipments, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("io2");
        }
    }
}
