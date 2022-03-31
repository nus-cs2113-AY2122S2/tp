package seedu.duke.data;

import java.util.ArrayList;
import java.util.Objects;

public class Item {

    private static final String NON_ZERO_QUANTITY = "quantity must be non-zero positive integer!";
    private static final String NOT_NULL_NAME = "name must not be null!";
    private static final String NOT_NULL_DESCRIPTION = "description must not be null!";

    private String name;
    private int quantity;
    private String description;
    private boolean isLost = false;
    public ArrayList<BorrowRecord> borrowRecords = new ArrayList<BorrowRecord>();

    public Item(String name, int quantity, String description) {
        this.name = name;
        this.quantity = quantity;
        this.description = description;
    }

    public boolean getLost() {
        return isLost;
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

    public void setName(String name) {
        Objects.requireNonNull(name, NOT_NULL_NAME);
        this.name = name;
    }

    public void setLost(boolean isLost) {
        this.isLost = isLost;
    }

    public void markTaskAsLost() {
        this.setLost(true);
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(NON_ZERO_QUANTITY);
        }
        this.quantity = quantity;
    }

    public void setDescription(String description) {
        Objects.requireNonNull(description, NOT_NULL_DESCRIPTION);
        this.description = description;
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
     * Add a new borrow record to the item.
     * @param newRecord A borrow record.
     */
    public void addBorrowRecord(BorrowRecord newRecord) {
        this.borrowRecords.add(newRecord);
    }

    /**
     * Returns the string representation of an Item when saved to storage.
     *
     * @return String representation of an item for saving.
     */
    public String saveString() {
        return String.format("%s | %d | %s", name, quantity, description);
    }

    @Override
    public String toString() {
        return String.format("%s | %d", name, quantity);
    }
    /**
     *     // String representation of an item when printed on Ui
     *     @Override
     *     public String toString() {
     *         String string1 = (name + " | " + quantity);
     *         if (isLost) {
     *             string1 = string1 + " |[LOST]";
     *         }
     *         return string1;
     *     }
     * */


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Item // instanceof handles nulls
                && this.name.equals(((Item) other).name)
                && this.description.equals(((Item) other).description)
                && (this.quantity == ((Item) other).quantity));
    }
}
