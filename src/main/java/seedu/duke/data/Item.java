package seedu.duke.data;

import seedu.duke.common.Messages;
import seedu.duke.exceptions.InvMgrException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Item {

    private static final String NON_ZERO_QUANTITY = "quantity must be non-zero positive integer!";
    private static final String NOT_NULL_NAME = "name must not be null!";
    private static final String NOT_NULL_DESCRIPTION = "description must not be null!";

    private String name;
    private int quantity;
    private String description;
    private ArrayList<BorrowRecord> borrowRecords;

    public Item(String name, int quantity, String description) {
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.borrowRecords = new ArrayList<>();
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
     * @param searchTerm User input of search term.
     * @return True if search term found in item name. Returns False, if otherwise.
     */
    public boolean contains(String searchTerm) {
        return name.equals(searchTerm);
    }

    /**
     * Adds a new borrow record to the item.
     *
     * @param newRecord A new borrow record.
     * @return This item that has been added with a new borrow record.
     * @throws InvMgrException If there is insufficient quantity in inventory to borrow.
     */
    public Item addBorrowRecord(BorrowRecord newRecord) throws InvMgrException {
        // Compute total quantity borrowed in overlapping records.
        int quantityBorrowed = newRecord.getQuantity();
        for (BorrowRecord record : borrowRecords) {
            if (newRecord.isConflict(record)) {
                quantityBorrowed += record.getQuantity();
            }
        }

        // Throw exception if there is insufficient quantity in inventory.
        if (quantityBorrowed > quantity) {
            throw new InvMgrException(Messages.INVALID_INSUFFICIENT_QUANTITY);
        }

        // Sufficient quantity in inventory, add borrow record.
        this.borrowRecords.add(newRecord);
        return this;
    }

    /**
     * Returns a list of borrow records filtered by borrower's name (if present)
     * and borrow status.
     *
     * @param name Either an empty Optional instance or
     *             an Optional instance containing a String name in it.
     * @param status Filter out borrow records with this BorrowStatus.
     * @return List of borrow records and item name in string format.
     */
    public List<String> filterRecords(Optional<String> name, BorrowStatus status) {
        String prefix = "Name of Item: " + this.name + System.lineSeparator();
        return borrowRecords.stream()
                .filter(record -> record.containsBorrowerName(name))
                .filter(record -> record.isStatus(status))
                .map(record -> prefix + record.toString())
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of OVERDUE borrow records filtered by borrower's name (if present)
     * and borrow status.
     *
     * <p>
     *     OVERDUE records are records that have the PAST BorrowStatus and have not been returned.
     * </p>
     *
     * @param name Either an empty Optional instance or
     *             an Optional instance containing a String name in it.
     * @return List of borrow records and item name in string format.
     */
    public List<String> filterOverdueRecords(Optional<String> name) {
        String prefix = "Name of Item: " + this.name + System.lineSeparator();
        return borrowRecords.stream()
                // Filter by optional name
                .filter(record -> record.containsBorrowerName(name))
                // Only check for PAST records
                .filter(record -> record.isStatus(BorrowStatus.PAST))
                // The item also needs to have isReturned == false
                .filter(record -> record.getReturnStatus() == false)
                // Add item name as a prefix to every record
                .map(record -> prefix + record.toString())
                // Convert this stream to a list
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

    @Override
    public String toString() {
        String output = String.format("%s | %d", this.name, this.quantity);
        return output;
    }

    /**
     * Returns a more detailed view of the Item.
     * Typically used in EditCommand and SearchCommand.
     *
     * @return more detailed String representation of an item.
     */
    public String toDetailedString() {
        if (this.description.length() > 15) {
            return String.format("%s | %d | %s", this.name, this.quantity, this.description.substring(0, 14) + "...");
        }
        return String.format("%s | %d | %s", this.name, this.quantity, this.description);
    }

    /**
     * Used to check equality between two Items.
     *
     * @param other the Object to compare this Item against.
     * @return true if equals, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true; // short circuit if same object
        }
        // instanceof handles nulls
        if (other instanceof Item) {
            Item otherItem = ((Item) other);
            return this.name.equals(otherItem.name)
                    && this.description.equals(otherItem.description)
                    && this.quantity == (otherItem.quantity)
                    && this.borrowRecords.containsAll(otherItem.borrowRecords);
        }
        return false;
    }

    /**
     * Used to clone an Item.
     * Cloneable is frowned upon, see:
     * https://www.artima.com/articles/josh-bloch-on-design#part13
     *
     * @param item the Item to clone.
     * @return an Item that is the copy of {@code item}.
     */
    public static Item copyItem(Item item) {
        String name = item.getName();
        int quantity = item.getQuantity();
        String description = item.getDescription();
        ArrayList<BorrowRecord> borrowRecords = item.getBorrowRecords();
        Item copiedItem = new Item(name, quantity, description);
        try {
            for (int i = 0; i < borrowRecords.size(); i++) {
                copiedItem.addBorrowRecord(borrowRecords.get(i));
            }
        } catch (InvMgrException e) {
            // suppress error, return null
            return null;
        }
        return copiedItem;
    }
}
