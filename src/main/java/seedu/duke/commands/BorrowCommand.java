package seedu.duke.commands;

import seedu.duke.common.Messages;
import seedu.duke.data.BorrowRecord;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.exceptions.InvMgrException;
import seedu.duke.ui.Ui;
import java.time.LocalDate;

/**
 * Borrow an item from the inventory for a specified duration.
 */
public class BorrowCommand extends Command {
    public static final String COMMAND_WORD = "borrow";
    public static final String COMMAND_NAME = "Borrow an Item";
    public static final String USAGE_MESSAGE = "Borrows an item from the inventory list";
    public static final String COMMAND_FORMAT = COMMAND_WORD
            + " i/[item number] q/[quantity] s/[start date] e/[end date] p/[name]";
    public static final String HELP_MESSAGE = COMMAND_NAME + ":\n" + "[Function] " + USAGE_MESSAGE + ":\n"
            + "[Command Format] " + COMMAND_FORMAT + "\n";

    private final int itemIndex;
    private final int quantity;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String borrowerName;

    public BorrowCommand(int itemIndex, int quantity, LocalDate startDate, LocalDate endDate, String borrowerName) {
        // itemIndex is parsed in as zero-based indexing.
        this.itemIndex = itemIndex;
        this.quantity = quantity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.borrowerName = borrowerName;
    }

    @Override
    public void execute(ItemList itemList, Ui ui) throws InvMgrException {
        // Check if index is within itemList, otherwise throw Exception
        if (itemIndex > itemList.getSize() - 1) {
            throw new InvMgrException(Messages.INVALID_INDEX);
        }
        Item item = itemList.getItem(itemIndex);
        // Create a new borrow record and add to item
        BorrowRecord newRecord = new BorrowRecord(quantity, startDate, endDate, borrowerName);
        item.addBorrowRecord(newRecord);
        ui.showMessages("Item has been successfully borrowed!",
                "Name of Item: " + item.getName(),
                newRecord.toString());
    }

    /**
     * Returns true if a BorrowRecord object is the same object compared to another BorrowRecord object
     * or contains the same attributes as another BorrowRecord object.
     *
     * @param o The other BorrowRecord object to compare to.
     * @return True if this BorrowRecord and another BorrowRecord
     *     are the same object or contains the same attributes.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BorrowCommand that = (BorrowCommand) o;
        return itemIndex == that.itemIndex
                && startDate.equals(that.startDate)
                && endDate.equals(that.endDate)
                && borrowerName.equals(that.borrowerName);
    }

}
