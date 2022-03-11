package seedu.Equipment;

import java.util.ArrayList;
import java.util.HashMap;

public class EquipmentManager {
    private final HashMap<String, Equipment> equipmentList = new HashMap<>();

    public void addEquipment(String itemName, String serialNumber, EquipmentType type, double cost, String purchasedFrom, String purchasedDate) {
        Equipment equipment = new Equipment(itemName, serialNumber, type, cost, purchasedFrom, purchasedDate);
        equipmentList.put(serialNumber, equipment);
    }

    public Equipment checkEquipment(String serialNumber) {
        return equipmentList.get(serialNumber);
    }

    public ArrayList<Equipment> listEquipment(EquipmentType type) {
        ArrayList<Equipment> listOfEquipments = new ArrayList<>();
        for (Equipment equipment : equipmentList.values()) {
            if (equipment.getType() == type) {
                listOfEquipments.add(equipment);
            }
        }
        return listOfEquipments;
    }

    public ArrayList<Equipment> listEquipment() {
        return new ArrayList<>(equipmentList.values());
    }

    public void updateEquipment(String serialNumber) {
        
    }

    public void deleteEquipment(String serialNumber) {
        equipmentList.remove(serialNumber);
    }
}
