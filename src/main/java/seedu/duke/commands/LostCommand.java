package seedu.duke.commands;

import java.io.IOException;
import java.util.InputMismatchException;

import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.exceptions.InvMgrException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;
import seedu.duke.common.Messages;

import static java.lang.Integer.parseInt;

public class LostCommand extends Command{
    protected int itemNumber = -1;
    public static final String COMMAND_WORD = "lost";
    public static final String COMMAND_NAME = "Report Lost Item";
    public static final String USAGE_MESSAGE = "Marks item as lost";
    public static final String COMMAND_FORMAT = COMMAND_WORD + " [item number]";
    public static final String HELP_MESSAGE = COMMAND_NAME + ":\n"
            + "[Function] "
            + USAGE_MESSAGE
            + ":\n"
            + "[Command Format] "
            + COMMAND_FORMAT
            + "\n";

    /**
     * Prepares the lost command for execution by extracting the task number of the task to be marked
     *
     * @param userInput User's input string
     */
    public LostCommand(String userInput) {
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

    protected static boolean checkItemListSize() {
        if (ItemList.getSize() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Marks an item in ItemList.itemArrayList as list
     * Updates the lost status in the user's item list file in the user's hard disk
     *
     * @param itemList Manages the user's task list
     * @param ui Displays messages to the user
     */
    @Override
    public void execute(ItemList itemList, Ui ui) {
        boolean isEmptyItemList = checkItemListSize();
        if (itemNumber == -1) {
            return;
        }
        if (isEmptyItemList) {
            ui.showMessages(Messages.EMPTY_ITEM_LIST_MESSAGE);
            ui.showDivider();
            return;
        }
        Item lostItem = null;
        try {
            lostItem = itemList.getItem(itemNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            ui.showMessages(Messages.TASK_NUMBER_OUT_OF_RANGE_MESSAGE);
            ui.showDivider();
            return;
        }
        lostItem.markTaskAsLost();
        ui.showMessages(Messages.REPORTED_LOST_MESSAGE);
        System.out.println(lostItem);
        ui.showDivider();
    }

}
