package seedu.equipment;

public class Equipment {
    private String itemName;
    private String serialNumber;
    private EquipmentType type;
    private double cost;
    private String purchasedFrom;
    private String purchasedDate;

    public Equipment(String itemName, String serialNumber, EquipmentType type, double cost, String purchasedFrom, String purchasedDate) {
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

    public String getPurchasedDate() {
        return this.purchasedDate;
    }

    public void setPurchasedDate(String purchasedDate) {
        this.purchasedDate = purchasedDate;
    }
}
