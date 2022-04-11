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
    public static final String COMMAND_NAME = "List available items";
    public static final String USAGE_MESSAGE = 
            "List all items avaliable between a start and end date";
    public static final String COMMAND_FORMAT = COMMAND_WORD + "[start date] [end date]";
    public static final String HELP_MESSAGE = COMMAND_NAME + ":\n" + "[Function] " + USAGE_MESSAGE
            + ":\n" + "[Command Format] " + COMMAND_FORMAT + "\n";
    public static final String AVAILABLE_RESULT = "Here are the items available for borrowing:";
    public static final String NO_AVAILABLE_RESULT = 
            "Sorry. There are no items available for borrowings.";
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructor for ListAvailableBorrowingsCommand.
     * @param startDate the start date of interest
     * @param endDate the end date of interest
     */
    public ListAvailableBorrowingsCommand(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Check if an item is available throughout the time period between startDate and endDate.
     * @param item Item being checked
     * @return Boolean value whether the Item is available
     */
    private boolean isAvailable(Item item) {
        ArrayList<BorrowRecord> borrowRecord = item.getBorrowRecords();
        for (BorrowRecord record : borrowRecord) {
            LocalDate recordStartDate = record.getStartDate();
            LocalDate recordEndDate = record.getEndDate();
            if (hasClash(startDate, endDate, recordStartDate, recordEndDate)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compares startDate and endDate of a BorrowRecord to startDate and endDate of interest.
     * @param startDate start date of interest
     * @param endDate end date of interest
     * @param recordStartDate start date of a specific record
     * @param recordEndDate end date of a specific record
     * @return Boolean value whether there are overlaps in dates
     */
    private boolean hasClash(LocalDate startDate, LocalDate endDate, 
            LocalDate recordStartDate, LocalDate recordEndDate) {
        if (recordEndDate.compareTo(startDate) < 0) {
            return false;

        } else if (recordStartDate.compareTo(endDate) > 0) {
            return false;

        } else {
            return true;
        }
    }

    /**
     * Prints out a list of available items for borrowing throughout the time period.
     * @param itemList ItemList of all Item
     * @param ui User Interface
     */
    @Override
    public void execute(ItemList itemList, Ui ui) {
        boolean hasItem = false;
        ui.showMessages(AVAILABLE_RESULT);
        for (int i = 0; i < itemList.getSize(); i++) {
            Item item = itemList.getItem(i);
            if (isAvailable(item)) {
                hasItem = true;
                ui.showMessages(String.valueOf(i + 1) + "." + item);
            }
        }
        if (!hasItem) {
            ui.showMessages(NO_AVAILABLE_RESULT);
        }
    }
}