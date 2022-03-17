package seedu.duke.commands;

import static java.util.Objects.requireNonNull;
import static seedu.duke.common.Messages.ERROR_MESSAGE;
import static seedu.duke.common.Messages.INVALID_INDEX;

import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

/**
 * Deletes an item identified using it's displayed index from the item list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Creates a DeleteCommand to delete item using specified index.
     *
     * @param index index specified.
     */
    public DeleteCommand(int index) {
        requireNonNull(index, "no index was indicated!");
        this.index = index;
    }

    @Override
    public void execute(ItemList itemList, Ui ui) {
        assert index != 0 : "index indicated is 0.";

        try {
            Item removedItem = itemList.removeItem(index);
            ui.showMessages(removedItem + " has been deleted.");
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showMessages(ERROR_MESSAGE, INVALID_INDEX);
        }
    }
}
