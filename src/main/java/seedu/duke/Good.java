package seedu.duke;

public class Good {
    private int id;
    private Float quantity = 0f;
    private UnitGood unitGood;

    public int getId() {
        return id;
    }

    public UnitGood getUnitGood(){
        return this.unitGood;
    }

    public UnitGood assignUnitGood(String name,
                               String description,
                               Float unitPrice,
                               String unitItem,
                               Float baseArea,
                               Float volume,
                               Boolean isPerishable){
        UnitGood newUnitGood = new UnitGood(
                name, description, unitPrice, unitItem, baseArea, volume, isPerishable
        );
        setUnitGood(newUnitGood);
        return newUnitGood;
    }

    public void setUnitGood(UnitGood unitGood){
        this.unitGood = unitGood;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = (float)quantity;
    }
    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public void removeQuantity(Float quantity){
        this.quantity -= quantity;
    }

    public void removeQuantity(int quantity){
        this.quantity -= quantity;
    }

    public void addQuantity(int quantity){
        this.quantity += quantity;
    }
}
