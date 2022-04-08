package seedu.duke.data;

import seedu.duke.common.Messages;
import seedu.duke.exceptions.InvMgrException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Item {

    private static final String NON_ZERO_QUANTITY = "quantity must be non-zero positive integer!";
    private static final String NOT_NULL_NAME = "name must not be null!";
    private static final String NOT_NULL_DESCRIPTION = "description must not be null!";

    private String name;
    private int quantity;
    private String description;
    public ArrayList<BorrowRecord> borrowRecords = new ArrayList<BorrowRecord>();

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

    public ArrayList<BorrowRecord> getBorrowRecords() {
        return borrowRecords;
    }

    public void setName(String name) {
        Objects.requireNonNull(name, NOT_NULL_NAME);
        this.name = name;
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
     * Returns True if an Item contains the search term in the item name.
     *
     * @param searchTerm User input of search term
     * @return True if search term found in item name. Returns False, if otherwise.
     */
    public boolean contains(String searchTerm) {
        return name.equals(searchTerm);
    }

    /**
     * Add a new borrow record to the item.
     * @param newRecord A borrow record.
     * @return This item that has been added with a new borrow record.
     */
    public Item addBorrowRecord(BorrowRecord newRecord) throws InvMgrException {
        // Iterate through each borrow records.
        // Throw exception if newRecord conflicts with existing records.
        for (BorrowRecord record : borrowRecords) {
            if (newRecord.isConflict(record)) {
                throw new InvMgrException(Messages.INVALID_DATES_CONCLICT);
            }
        }

        this.borrowRecords.add(newRecord);
        return this;
    }

    /**
     * Returns a list of borrow records filtered by borrow status
     *
     * @param status Filter out borrow records with this status
     * @return List of BorrowRecords
     */
    public List<BorrowRecord> getRecordsByStatus(BorrowStatus status) {
        return borrowRecords.stream()
                .filter(record -> record.isStatus(status))
                .collect(Collectors.toList());
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
        return String.format("%s | %d", this.name, this.quantity);
    }

    public String toDetailedString() {
        if (this.description.length() > 15) {
            return String.format("%s | %d | %s", this.name, this.quantity, this.description.substring(0, 14) + "...");
        }
        return String.format("%s | %d | %s", this.name, this.quantity, this.description);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Item // instanceof handles nulls
                && this.name.equals(((Item) other).name)
                && this.description.equals(((Item) other).description)
                && (this.quantity == ((Item) other).quantity));
    }
}
