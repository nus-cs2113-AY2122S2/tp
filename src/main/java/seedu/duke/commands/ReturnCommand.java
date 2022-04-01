package seedu.duke.commands;

import seedu.duke.common.Messages;
import seedu.duke.data.BorrowRecord;
import seedu.duke.data.BorrowStatus;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class ReturnCommand extends Command {
    protected int itemNumber = -1;
    public static final String COMMAND_WORD = "return";
    public static final String COMMAND_NAME = "Return an item:";
    public static final String USAGE_MESSAGE = "Marks item as returned";
    public static final String COMMAND_FORMAT = COMMAND_WORD + " [item number]";
    public static final String HELP_MESSAGE = COMMAND_NAME + ":\n"
            + "[Function] "
            + USAGE_MESSAGE
            + ":\n"
            + "[Command Format] "
            + COMMAND_FORMAT
            + "\n";

    /**
     * Prepares the return command for execution by extracting the task number of the task to be marked.
     *
     * @param userInput User's input string
     */
    public ReturnCommand(String userInput) {
        Ui ui = new Ui();
        try {
            itemNumber = parseInt(userInput.trim());
        } catch (NumberFormatException e) {
            ui.showMessages(Messages.MISSING_ITEM_NUMBER_MESSAGE);
            ui.showDivider();
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showMessages(Messages.INVALID_ITEM_NUMBER_MESSAGE);
            ui.showDivider();
        }
    }

    protected static boolean checkItemListSize(ItemList itemList) {
        if (itemList.getSize() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Updates a current borrow record of item in ItemList.itemArrayList as returned
     *
     * @param itemList Manages the user's task list
     * @param ui Displays messages to the user
     */
    @Override
    public void execute(ItemList itemList, Ui ui) {
        boolean isEmptyItemList = checkItemListSize(itemList);
        if (itemNumber == -1) {
            return;
        }
        if (isEmptyItemList) {
            ui.showMessages(Messages.EMPTY_ITEM_LIST_MESSAGE);
            ui.showDivider();
            return;
        }
        Item returnedItem = null;
        try {
            returnedItem = itemList.getItem(itemNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            ui.showMessages(Messages.ITEM_NUMBER_OUT_OF_RANGE_MESSAGE);
            ui.showDivider();
            return;
        }
        ArrayList<BorrowRecord> itemBorrowRecords = returnedItem.getBorrowRecords();
        for (BorrowRecord record : itemBorrowRecords) {
            if (record.getBorrowStatus() == BorrowStatus.PRESENT) {
                record.setReturnStatus(true);
                record.setEndDate();
                ui.showMessages(Messages.RETURNED_MESSAGE);
                ui.showMessages("Name of Item: " + returnedItem.getName(),
                        "Name of Borrower: " + record.getBorrowerName(),
                        "Borrow Duration: " + record.getBorrowDuration() + "\n");
                ui.showDivider();
                break;
            }
        }
    }
}