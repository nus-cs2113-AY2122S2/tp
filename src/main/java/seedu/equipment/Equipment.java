package seedu.equipment;

import java.time.LocalDate;
import java.util.Objects;

public class Equipment {
    private String itemName;
    private String serialNumber;
    private EquipmentType type;
    private double cost;
    private String purchasedFrom;
    private LocalDate purchasedDate;

    /**
     * Constructor for the Equipment object.
     * @param itemName Name of the equipment in String.
     * @param serialNumber Unique serial number of equipment in String.
     * @param type Type of the equipment in EquipmentType enum.
     * @param cost Cost of the equipment in double.
     * @param purchasedFrom Where the equipment was purchased in String.
     * @param purchasedDate When the equipment was purchased in String.
     */
    public Equipment(String itemName, String serialNumber, EquipmentType type, double cost, String purchasedFrom,
                     LocalDate purchasedDate) {
        this.itemName = itemName;
        this.serialNumber = serialNumber;
        this.type = type;
        this.cost = cost;
        this.purchasedFrom = purchasedFrom;
        this.purchasedDate = purchasedDate;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public EquipmentType getType() {
        return this.type;
    }

    public void setType(EquipmentType type) {
        this.type = type;
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getPurchasedFrom() {
        return this.purchasedFrom;
    }

    public void setPurchasedFrom(String purchasedFrom) {
        this.purchasedFrom = purchasedFrom;
    }

    public LocalDate getPurchasedDate() {
        return this.purchasedDate;
    }

    public void setPurchasedDate(LocalDate purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Equipment equipment = (Equipment) o;
        return Double.compare(equipment.cost, cost) == 0
                && itemName.equals(equipment.itemName)
                && serialNumber.equals(equipment.serialNumber)
                && type == equipment.type
                && purchasedFrom.equals(equipment.purchasedFrom)
                && purchasedDate.equals(equipment.purchasedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, serialNumber, type, cost, purchasedFrom, purchasedDate);
    }

    @Override
    public String toString() {
        return "serialNumber=" + serialNumber + ","
                + "itemName=" + itemName + ","
                + "type=" + type + ","
                + "cost=" + cost + ","
                + "purchasedFrom=" + purchasedFrom + ","
                + "purchasedDate=" + purchasedDate;
    }
}
