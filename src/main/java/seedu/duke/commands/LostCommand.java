package seedu.duke.commands;

import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.exceptions.InvMgrException;
import seedu.duke.ui.Ui;
import seedu.duke.common.Messages;

import static seedu.duke.parser.CliSyntax.PREFIX_ITEM_INDEX;
import static seedu.duke.parser.CliSyntax.PREFIX_QUANTITY;

public class LostCommand extends Command {
    private int itemIndex;
    public static final String COMMAND_WORD = "lost";
    public static final String COMMAND_NAME = "Report Lost Item";
    public static final String USAGE_MESSAGE = "Marks item as lost";
    public static final String COMMAND_FORMAT = COMMAND_WORD + " " + PREFIX_ITEM_INDEX + "[item number]";
    public static final String HELP_MESSAGE = COMMAND_NAME + ":\n"
            + "[Function] "
            + USAGE_MESSAGE
            + "\n"
            + "[Command Format] "
            + COMMAND_FORMAT
            + "\n";

    /**
     * Prepares the lost command for execution by extracting the task number of the task to be marked.
     *
     * @param itemIndex Index of item to be marked as lost.
     */
    public LostCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    protected boolean checkItemListSize(ItemList itemList) {
        if (itemList.getSize() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Marks an item in ItemList.itemArrayList as lost.
     * Updates the lost status in the user's item list file in the user's hard disk.
     *
     * @param itemList Manages the user's task list.
     * @param ui Displays messages to the user.
     */
    @Override
    public void execute(ItemList itemList, Ui ui) throws InvMgrException {
        boolean isEmptyItemList = checkItemListSize(itemList);
        if (isEmptyItemList) {
            throw new InvMgrException(Messages.EMPTY_ITEM_LIST_MESSAGE);
        }
        Item lostItem = null;
        try {
            lostItem = itemList.getItem(itemIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new InvMgrException(Messages.ITEM_NUMBER_OUT_OF_RANGE_MESSAGE);
        }
        itemList.removeItem(itemIndex);
        ui.showMessages(lostItem + " has been deleted.");
        ui.showMessages(Messages.REPORTED_LOST_AND_DELETED_MESSAGE);
    }

    /**
     * Returns true if a LostCommand object is the same object compared to another LostCommand object
     * or contains the same attributes as another LostCommand object.
     *
     * @param o The other LostCommand object to compare to.
     * @return True if this LostCommand and another LostCommand are the same object or contains the same attributes.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LostCommand that = (LostCommand) o;
        return itemIndex == that.itemIndex;
    }

}
