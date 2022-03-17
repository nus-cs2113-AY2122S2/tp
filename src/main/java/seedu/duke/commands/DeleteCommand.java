package seedu.duke.commands;

import static java.util.Objects.requireNonNull;

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
     * @param index index specified.
     */
    public DeleteCommand(int index) {
        requireNonNull(index, "no index was indicated!");
        this.index = index;
    }

    @Override
    public void execute(ItemList itemList, Ui ui) {
        Item removedItem = itemList.removeItem(index);
        ui.showMessages(removedItem + " has been deleted.");
    }
}
