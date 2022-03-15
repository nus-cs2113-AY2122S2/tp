package seedu.duke.data;

public class Item {
    private String name;
    private String quantity;
    private String description;

    public Item(String name, String quantity, String description) {
        this.name = name;
        this.quantity = quantity;
        this.description = description;
    }

    @Override
    public String toString() {
        return "To be done";
    }

    public String saveString() {
        return "To be done";
    }
}
