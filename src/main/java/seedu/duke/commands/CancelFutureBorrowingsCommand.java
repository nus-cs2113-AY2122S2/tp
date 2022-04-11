package seedu.duke.commands;

import seedu.duke.data.BorrowStatus;
import seedu.duke.data.ItemList;
import seedu.duke.exceptions.InvMgrException;
import seedu.duke.ui.Ui;
import seedu.duke.data.BorrowRecord;
import seedu.duke.data.Item;
import seedu.duke.common.Messages;

import java.util.ArrayList;

/**
 * Looks for a bookings under a borrower's name and removes it from the item.
 */
public class CancelFutureBorrowingsCommand extends Command {

    public static final String COMMAND_WORD = "cancel";
    public static final String COMMAND_NAME = "Cancel future Borrowing";
    public static final String USAGE_MESSAGE = 
            "Cancels a future borrowing made by a person";
    public static final String COMMAND_FORMAT = COMMAND_WORD 
            + " p/[borrower name] i/[borrow index]";
    public static final String HELP_MESSAGE = COMMAND_NAME + ":\n" + "[Function] " + USAGE_MESSAGE
            + "\n" + "[Command Format] " + COMMAND_FORMAT + "\n";
    private final String borrowerName;
    private final int borrowIndex;

    /**
     * Constructor of CancelFutureBorrowingsCommand.
     * 
     * @param borrowerName the name of the borrower
     * @param borrowIndex the index of the borrower's future borrowings
     */
    public CancelFutureBorrowingsCommand(String borrowerName, int borrowIndex) {
        this.borrowerName = borrowerName;
        this.borrowIndex = borrowIndex;
    }

    /**
     * Get an ArrayList of BorrowRecord in order of the borrower's future borrowings.
     * 
     * @param borrowerName the name of the borrower
     * @param itemList the ItemList of all Items
     * @return List of BorrowRecord in order of borrower's future borrowings.
     */
    private ArrayList<BorrowRecord> getBorrowRecords(String borrowerName, ItemList itemList) {
        ArrayList<BorrowRecord> records = new ArrayList<BorrowRecord>();

        

        for (int i = 0; i < itemList.getSize(); i++) {
            Item borrowedItem = itemList.getItem(i);
            ArrayList<BorrowRecord> borrowRecords = borrowedItem.getBorrowRecords();

            for (BorrowRecord record : borrowRecords) {
                boolean isFutureBorrowing = record.getBorrowStatus() == BorrowStatus.FUTURE;
                boolean matchesName = record.getBorrowerName().equals(borrowerName);
                if (isFutureBorrowing && matchesName) {
                    records.add(record);
                }
            }
        }
        return records;
    }

    /**
     * Get an ArrayList of Item in order of the borrower's future borrowings.
     * 
     * @param borrowerName the name of the borrower
     * @param itemList the ItemList of all Items
     * @return List of items in order of borrower's future borrowings.
     */
    private ArrayList<Item> getItems(String borrowerName, ItemList itemList) {
        ArrayList<Item> items = new ArrayList<Item>();

        for (int i = 0; i < itemList.getSize(); i++) {
            Item borrowedItem = itemList.getItem(i);
            ArrayList<BorrowRecord> borrowRecords = borrowedItem.getBorrowRecords();

            for (BorrowRecord record : borrowRecords) {
                boolean isFutureBorrowing = record.getBorrowStatus() == BorrowStatus.FUTURE;
                boolean matchesName = record.getBorrowerName().equals(borrowerName);
                if (isFutureBorrowing && matchesName) {
                    items.add(borrowedItem);
                }
            }
        }
        return items;
    }

    /**
     * Uses borrowIndex to get Item and BorrowRecord from ArrayLists from getItems() and 
     * getBorrowRecords(), then removes BorrowRecord from BorrowRecords in Item.
     * 
     * @param itemList ItemList of all Items
     * @param ui User Interface
     */
    public void execute(ItemList itemList, Ui ui) throws InvMgrException {
        ArrayList<BorrowRecord> records = getBorrowRecords(borrowerName, itemList);
        ArrayList<Item> items = getItems(borrowerName, itemList);

        assert borrowIndex + 1 < records.size() : "index must be smaller than number of future borrowings";
        assert borrowIndex + 1 < items.size() : "index must be smaller than number of future borrowings";

        if (borrowIndex + 1 > records.size() || borrowIndex + 1 > items.size() 
                || borrowIndex < 0) {
            throw new InvMgrException(Messages.INVALID_INDEX);
        }
        
        BorrowRecord removeRecord = records.get(borrowIndex);
        Item item = items.get(borrowIndex);
        int itemQuantity = removeRecord.getQuantity();
        ArrayList<BorrowRecord> itemRecords = item.getBorrowRecords();
        itemRecords.remove(removeRecord);
        ui.showMessages("Future borrowing of " + item.getName() + " | " + itemQuantity + " from " 
                + removeRecord.getBorrowDuration() + " by " + removeRecord.getBorrowerName() 
                + " has been removed");
    }
}