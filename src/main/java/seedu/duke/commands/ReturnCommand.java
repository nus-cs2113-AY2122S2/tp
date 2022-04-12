package seedu.duke.commands;

import seedu.duke.common.Messages;
import seedu.duke.data.BorrowRecord;
import seedu.duke.data.BorrowStatus;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.exceptions.InvMgrException;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import static seedu.duke.parser.CliSyntax.PREFIX_ITEM_INDEX;


public class ReturnCommand extends Command {
    private final int itemIndex;
    private boolean isValidReturnRequest = false;
    public static final String COMMAND_WORD = "return";
    public static final String COMMAND_NAME = "Return an item:";
    public static final String USAGE_MESSAGE = "Marks item as returned";
    public static final String COMMAND_FORMAT = COMMAND_WORD + " " + PREFIX_ITEM_INDEX + "[item number]";
    public static final String HELP_MESSAGE = COMMAND_NAME + ":\n"
            + "[Function] "
            + USAGE_MESSAGE
            + "\n"
            + "[Command Format] "
            + COMMAND_FORMAT
            + "\n";

    public boolean getValidityOfReturn() {
        return isValidReturnRequest;
    }

    /**
     * Prepares the return command for execution by extracting the task number of the task to be marked.
     *
     * @param itemIndex Index of item to be returned.
     */
    public ReturnCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    protected static boolean checkItemListSize(ItemList itemList) {
        if (itemList.getSize() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Updates a current borrow record of item in ItemList.itemArrayList as returned.
     *
     * @param itemList Manages the user's task list.
     * @param ui Displays messages to the user.
     */
    @Override
    public void execute(ItemList itemList, Ui ui) throws InvMgrException {
        // If the item list is empty, then no item can be returned.
        boolean isEmptyItemList = checkItemListSize(itemList);
        if (isEmptyItemList) {
            throw new InvMgrException(Messages.EMPTY_ITEM_LIST_MESSAGE);
        }
        Item returnedItem = null;
        try {
            returnedItem = itemList.getItem(itemIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new InvMgrException(Messages.ITEM_NUMBER_OUT_OF_RANGE_MESSAGE);
        }
        ArrayList<BorrowRecord> itemBorrowRecords = returnedItem.getBorrowRecords();
        for (BorrowRecord record : itemBorrowRecords) {
            boolean isOverdue = (record.getBorrowStatus() == BorrowStatus.PAST) && (record.getReturnStatus() == false);
            boolean isOutstanding = (record.getBorrowStatus() == BorrowStatus.PRESENT)
                    && (record.getReturnStatus() == false);
            if (isOutstanding || isOverdue) {
                record.setReturnStatus(true);
                record.setEndDate();
                ui.showMessages(Messages.RETURNED_MESSAGE);
                ui.showMessages("Name of Item: " + returnedItem.getName(),
                        "Name of Borrower: " + record.getBorrowerName(),
                        "Borrow Duration: " + record.getBorrowDuration());
                isValidReturnRequest = true;
                break;
            }
        }
        if (!isValidReturnRequest) {
            throw new InvMgrException(Messages.RETURN_ERROR_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        ReturnCommand that = (ReturnCommand) object;
        return itemIndex == that.itemIndex;
    }
}
