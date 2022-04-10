package seedu.equipment;

import seedu.Pair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class EquipmentManager {
    private final HashMap<String, Equipment> equipmentList;

    public EquipmentManager() {
        this.equipmentList = new HashMap<>();
    }

    /**
     * Creates an Equipment object using the parameters and adds it to the equipmentList.
     *
     * @param itemName Name of the equipment in String.
     * @param serialNumber Unique serial number of equipment in String.
     * @param type Type of the equipment in EquipmentType enum.
     * @param cost Cost of the equipment in double.
     * @param purchasedFrom Where the equipment was purchased in String.
     * @param purchasedDate When the equipment was purchased in String.
     * @throws DuplicateSerialNumberException If the serial number exists in equipmentList.
     */
    public void addEquipment(String itemName, String serialNumber, EquipmentType type, double cost,
                             String purchasedFrom, LocalDate purchasedDate) throws DuplicateSerialNumberException {
        if (!equipmentList.containsKey(serialNumber.toLowerCase())) {
            Equipment equipment = new Equipment(itemName, serialNumber, type, cost, purchasedFrom, purchasedDate);
            equipmentList.put(serialNumber.toLowerCase(), equipment);
        } else if (equipmentList.containsKey(serialNumber.toLowerCase())) {
            throw new DuplicateSerialNumberException();
        }
    }

    /**
     * Adds the Equipment object into the equipmentList.
     *
     * @param equipment Equipment object.
     * @throws DuplicateSerialNumberException If the serial number exists in equipmentList.
     */
    public void addEquipment(Equipment equipment) throws DuplicateSerialNumberException {
        String serialNumber = equipment.getSerialNumber();
        if (!equipmentList.containsKey(serialNumber.toLowerCase())) {
            equipmentList.putIfAbsent(equipment.getSerialNumber().toLowerCase(), equipment);
        } else {
            throw new DuplicateSerialNumberException();
        }
    }

    /**
     * Searches the equipmentList for Equipments with the item name given.
     *
     * @param checkParam Name of Equipment in String.
     * @return An ArrayList of Equipments with the same item name as the parameter.
     */
    public ArrayList<Equipment> checkEquipment(Pair<String, ?> checkParam) {
        ArrayList<Equipment> listOfEquipments = new ArrayList<>();
        String arg = checkParam.getKey();
        switch (arg) {
        case "itemName":
            for (Equipment equipment : equipmentList.values()) {
                if (equipment.getItemName().contains((String) checkParam.getValue())) {
                    listOfEquipments.add(equipment);
                }
            }
            break;
        case "serialNumber":
            String serialNumber = (String) checkParam.getValue();
            listOfEquipments.add(equipmentList.get(serialNumber.toLowerCase()));
            break;
        case "type":
            for (Equipment equipment : equipmentList.values()) {
                if (equipment.getType().equals(checkParam.getValue())) {
                    listOfEquipments.add(equipment);
                }
            }
            break;
        case "cost":
            for (Equipment equipment : equipmentList.values()) {
                if (equipment.getCost() == (double) checkParam.getValue()) {
                    listOfEquipments.add(equipment);
                }
            }
            break;
        case "purchasedFrom":
            for (Equipment equipment : equipmentList.values()) {
                if (equipment.getPurchasedFrom().contains((String) checkParam.getValue())) {
                    listOfEquipments.add(equipment);
                }
            }
            break;
        case "purchasedDate":
            for (Equipment equipment : equipmentList.values()) {
                if (equipment.getPurchasedDate().equals(checkParam.getValue())) {
                    listOfEquipments.add(equipment);
                }
            }
            break;
        default:
            break;
        }
        return listOfEquipments;
    }

    /**
     * Creates an ArrayList of all Equipment Objects.
     *
     * @return An ArrayList of all Equipment Objects in equipmentList.
     */
    public ArrayList<Equipment> listEquipment() {
        return new ArrayList<>(equipmentList.values());
    }

    public HashMap<String, Equipment> getEquipmentList() {
        return equipmentList;
    }

    /**
     * Updates the Equipment corresponding to the serial number given.
     * Updates are given in the form of ArrayList of Pairs.
     *
     * @param serialNumber Serial number of Equipment in String.
     * @param updatePairs Updates for Equipment with key being an Equipment
     *                    attribute and value being the updated attribute.
     * @return Boolean value based on whether the update succeeded or not.
     */
    public boolean updateEquipment(String serialNumber, ArrayList<Pair<String, ?>> updatePairs) {
        if (!equipmentList.containsKey(serialNumber.toLowerCase())) {
            return false;
        }
        Equipment updatedEquipment = equipmentList.get(serialNumber.toLowerCase());
        for (Pair<String, ?> updates : updatePairs) {
            String key = updates.getKey();
            switch (key) {
            case "itemName":
                updatedEquipment.setItemName((String) updates.getValue());
                break;
            case "type":
                try {
                    updatedEquipment.setType((EquipmentType) updates.getValue());
                } catch (IllegalArgumentException e) {
                    return false;
                }
                break;
            case "cost":
                updatedEquipment.setCost((Double) updates.getValue());
                break;
            case "purchasedDate":
                updatedEquipment.setPurchasedDate((LocalDate) updates.getValue());
                break;
            case "purchasedFrom":
                updatedEquipment.setPurchasedFrom((String) updates.getValue());
                break;
            default:
                break;
            }
        }
        return true;
    }

    /**
     * Deletes the Equipment with the given serial number.
     *
     * @param serialNumber Serial number of Equipment in String.
     */
    public void deleteEquipment(String serialNumber) {
        equipmentList.remove(serialNumber.toLowerCase());
    }
}
