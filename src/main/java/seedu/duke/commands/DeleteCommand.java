package seedu.duke.commands;

import static seedu.duke.common.Messages.ERROR_MESSAGE;
import static seedu.duke.common.Messages.INVALID_INDEX;

import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

/**
 * Deletes an item identified using it's displayed index from the item list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_NAME = "Delete Item";
    public static final String USAGE_MESSAGE = "Deletes an item from the inventory list";
    public static final String COMMAND_FORMAT = COMMAND_WORD + " [item number]";
    public static final String HELP_MESSAGE = COMMAND_NAME + ":\n" + "[Function] " + USAGE_MESSAGE + "\n"
            + "[Command Format] " + COMMAND_FORMAT + "\n";
    private final int index;

    /**
     * Creates a DeleteCommand to delete item using specified index.
     *
     * @param index index specified.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(ItemList itemList, Ui ui) {
        assert (index > -1) : "index less than or equal to -1";
        try {
            Item removedItem = itemList.removeItem(index);
            ui.showMessages(removedItem + " has been deleted.");
        } catch (IndexOutOfBoundsException e) {
            ui.showMessages(ERROR_MESSAGE, INVALID_INDEX);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && (this.index == ((DeleteCommand) other).index));
    }
}
