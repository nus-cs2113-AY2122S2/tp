package seedu.duke;

/*
 * Class to handle the Goods information
 *
 * FOR NOW id we can manually input?
 * Ideas - id can track and give to the goods we add automatically
 */
public class Good {
    private int id;

    private String name;
    private int quantity;
    private String description;
    private UnitGood unitGood;

    public Good(int id, String name, int quantity, String description) {
        this.id = id;
        this.quantity = quantity;
        this.description = description;
        this.unitGood = new UnitGood(name,
                description,
                0.0F,
                "piece",
                -1.0F,
                -1.0F,
                false);
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return unitGood.getDescription();
    }

    public String getName() {
        return unitGood.getName();
    }

    public String toString() {
        return String.format("%d - %s (%s)",id, name, description);
    }
}
