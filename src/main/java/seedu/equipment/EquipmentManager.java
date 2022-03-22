package seedu.equipment;

import seedu.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class EquipmentManager {
    private final HashMap<String, Equipment> equipmentList;

    public EquipmentManager() {
        this.equipmentList = new HashMap<>();
    }

    public void addEquipment(String itemName, String serialNumber, EquipmentType type, double cost,
                             String purchasedFrom, String purchasedDate) throws DuplicateSerialNumberException {
        if (!equipmentList.containsKey(serialNumber)) {
            Equipment equipment = new Equipment(itemName, serialNumber, type, cost, purchasedFrom, purchasedDate);
            equipmentList.put(serialNumber, equipment);
        } else if (equipmentList.containsKey(serialNumber)) {
            throw new DuplicateSerialNumberException();
        }
    }

    public void addEquipment(Equipment equipment) throws DuplicateSerialNumberException {
        String serialNumber = equipment.getSerialNumber();
        if (!equipmentList.containsKey(serialNumber)) {
            equipmentList.putIfAbsent(equipment.getSerialNumber(), equipment);
        } else {
            throw new DuplicateSerialNumberException();
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

    public ArrayList<Equipment> listEquipment() {
        return new ArrayList<>(equipmentList.values());
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

    public HashMap<String, Equipment> getEquipmentList() {
        return equipmentList;
    }

    public boolean updateEquipment(String serialNumber, ArrayList<Pair<String, ?>> updatePairs) {
        if (!equipmentList.containsKey(serialNumber)) {
            return false;
        }
        Equipment updatedEquipment = equipmentList.get(serialNumber);
        for (Pair<String, ?> updates : updatePairs) {
            String key = updates.getKey();
            switch (key) {
            case "itemName":
                updatedEquipment.setItemName((String) updates.getValue());
                break;
            case "type":
                try {
                    updatedEquipment.setType(EquipmentType.valueOf((String)updates.getValue()));
                } catch (IllegalArgumentException e) {
                    return false;
                }
                break;
            case "cost":
                updatedEquipment.setCost(Double.valueOf((String) updates.getValue()));
                break;
            case "purchaseDate":
                updatedEquipment.setPurchasedDate((String) updates.getValue());
                break;
            case "purchaseFrom":
                updatedEquipment.setPurchasedFrom((String) updates.getValue());
                break;
            default:
                break;
            }
        }
        return true;
    }

    public void deleteEquipment(String serialNumber) {
        equipmentList.remove(serialNumber);
    }
}
