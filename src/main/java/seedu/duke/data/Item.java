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

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns a boolean indicating if an Item contains the search term in the item name.
     *
     * @param searchTerm User input of search term
     * @return True if search term found in item name. Returns False, if otherwise.
     */
    public boolean contains(String searchTerm) {
        if (name == searchTerm) {
            return true;
        } else {
            return false;
        }
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
