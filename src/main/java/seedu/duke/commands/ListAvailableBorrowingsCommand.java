package seedu.duke.commands;

import seedu.duke.data.BorrowRecord;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Lists down Items that are available throughout a time period.
 */
public class ListAvailableBorrowingsCommand extends Command {
    public static final String COMMAND_WORD = "listab";
    public static final String COMMAND_NAME = "List available items between 2 dates";
    public static final String USAGE_MESSAGE = 
            "List all items avaliable between a start and end date";
    public static final String COMMAND_FORMAT = COMMAND_WORD + " s/[start date] e/[end date]";
    public static final String HELP_MESSAGE = COMMAND_NAME + ":\n" + "[Function] " + USAGE_MESSAGE
            + ":\n" + "[Command Format] " + COMMAND_FORMAT + "\n";
    public static final String AVAILABLE_RESULT = "Here are the items available for borrowing:";
    public static final String NO_AVAILABLE_RESULT = 
            "Sorry. There are no items available for borrowings.";
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructor for ListAvailableBorrowingsCommand.
     * 
     * @param startDate the start date of interest
     * @param endDate the end date of interest
     */
    public ListAvailableBorrowingsCommand(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Check the minimum quantity of an item available throughout the time period between
     * startDate and endDate.
     * 
     * @param item Item being checked
     * @return minimum quantity available
     */
    private int minQuantityAvailable(Item item) {
        int itemQuantity = item.getQuantity();
        int minAvailable = itemQuantity;
        ArrayList<BorrowRecord> borrowRecord = item.getBorrowRecords();
        LocalDate dayAfterEndDate = endDate.plusDays(1);
        for (LocalDate date = startDate; date.isBefore(dayAfterEndDate); date = date.plusDays(1)) {
            int availableQuantity = findMin(date, borrowRecord, itemQuantity);
            if (availableQuantity < minAvailable) {
                minAvailable = availableQuantity;
            }
        }
        return minAvailable;
    }

    /**
     * Finds the quantity of items that can be borrowed on a specific date.
     * 
     * @param date date of interest
     * @param borrowRecord borrow record of the item
     * @param itemQuantity original item quantity in the store
     * @return quantity of items that can be borrowed
     */
    private int findMin(LocalDate date, ArrayList<BorrowRecord> borrowRecord, int itemQuantity) {
        for (BorrowRecord record : borrowRecord) {
            LocalDate recordStartDate = record.getStartDate();
            LocalDate recordEndDate = record.getEndDate();
            if (hasDayClash(date, recordStartDate, recordEndDate)) {
                itemQuantity -= record.getQuantity();
            }
        }
        return itemQuantity;
    }

    /**
     * Checks if a specific date is between the startDate and endDate of a record.
     * 
     * @param date date of interest
     * @param recordStartDate start date of a record
     * @param recordEndDate end date of a record
     * @return boolean value whether the date of interest is between start and end date
     */
    private boolean hasDayClash(LocalDate date, LocalDate recordStartDate, 
            LocalDate recordEndDate) {
        if (recordEndDate.compareTo(date) < 0) {
            return false;

        } else if (recordStartDate.compareTo(date) > 0) {
            return false;

        } else {
            return true;
        }
    }

    /**
     * Prints out a list of available items for borrowing throughout the time period.
     * 
     * @param itemList ItemList of all Item
     * @param ui User Interface
     */
    @Override
    public void execute(ItemList itemList, Ui ui) {
        boolean hasItem = false;
        ui.showMessages(AVAILABLE_RESULT);
        for (int i = 0; i < itemList.getSize(); i++) {
            Item item = itemList.getItem(i);
            if (minQuantityAvailable(item) > 0) {
                hasItem = true;
                ui.showMessages(String.valueOf(i + 1) + "." + item.getName() + " | " + 
                        minQuantityAvailable(item));
            }
        }
        if (!hasItem) {
            ui.showMessages(NO_AVAILABLE_RESULT);
        }
    }
}