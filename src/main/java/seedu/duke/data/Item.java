package seedu.duke.data;

public class Item {
    private String name;
    private int quantity;
    private String description;

    public Item(String name, int quantity, String description) {
        this.name = name;
        this.quantity = quantity;
        this.description = description;
    }

    /**
     * Returns the string representation of an Item when saved to storage.
     *
     * @return String representation of an item for saving.
     */
    public String saveString() {
        return String.format("%s | %d | %s", name, quantity, description);
    }

    // String representation of an item when printed on Ui
    @Override
    public String toString() {
        return String.format("%s | %d", name, quantity);
    }
}
