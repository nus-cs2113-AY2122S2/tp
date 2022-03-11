package seedu.Equipment;

import java.util.ArrayList;
import java.util.HashMap;

public class EquipmentManager {
    private final HashMap<String, Equipment> equipmentList = new HashMap<>();

    public void addEquipment(String itemName, String serialNumber, EquipmentType type, double cost, String purchasedFrom, String purchasedDate) throws DuplicateSerialNumber {
        if (!equipmentList.containsKey(serialNumber)) {
            Equipment equipment = new Equipment(itemName, serialNumber, type, cost, purchasedFrom, purchasedDate);
            equipmentList.putIfAbsent(serialNumber, equipment);
        } else if (equipmentList.containsKey(serialNumber)) {
            throw new DuplicateSerialNumber();
        }
    }

    public ArrayList<Equipment> checkEquipment(String itemName) {
        ArrayList<Equipment> listOfEquipments = new ArrayList<>();
        for (Equipment equipment : equipmentList.values()) {
            if (equipment.getItemName().equals(itemName)) {
                listOfEquipments.add(equipment);
            }
        }
        return listOfEquipments;
    }

    public ArrayList<Equipment> listEquipment(EquipmentType type) {
        ArrayList<Equipment> listOfEquipments = new ArrayList<>();
        for (Equipment equipment : equipmentList.values()) {
            if (equipment.getType().equals(type)) {
                listOfEquipments.add(equipment);
            }
        }
        return listOfEquipments;
    }

    public ArrayList<Equipment> listEquipment() {
        return new ArrayList<>(equipmentList.values());
    }

    public void updateEquipment(String serialNumber) {
        // To be updated
    }

    public void deleteEquipment(String serialNumber) {
        equipmentList.remove(serialNumber);
    }
}
