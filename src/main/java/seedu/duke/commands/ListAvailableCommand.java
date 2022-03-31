package seedu.duke.commands;

import seedu.duke.data.BorrowRecord;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

public class ListAvailableCommand extends Command {
    public static final String COMMAND_WORD = "listab";
    public static final String COMMAND_NAME = "List available items";
    public static final String USAGE_MESSAGE = 
            "List all items avaliable between a start and end date";
    public static final String COMMAND_FORMAT = COMMAND_WORD + "[start date] [end date]";
    public static final String HELP_MESSAGE = COMMAND_NAME + ":\n" + "[Function] " + USAGE_MESSAGE
    + ":\n" + "[Command Format] " + COMMAND_FORMAT + "\n";

    private final LocalDate startDate;
    private final LocalDate endDate;

    public ListAvailableCommand(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

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

    @Override
    public void execute(ItemList itemList, Ui ui) {
        boolean hasItem = false;
        ui.showMessages("Here are the items available for borrowing:");
        for (int i = 0; i < itemList.getSize(); i++) {
            Item item = itemList.getItem(i);
            if (isAvailable(item)) {
                hasItem = true;
                ui.showMessages(String.valueOf(i + 1) + "." + item);
            }
        }
        if (!hasItem) {
            ui.showMessages("Sorry. There are no items available for borrowings.");
        }
    }
}