package seedu.duke.commands;

import static java.util.Objects.requireNonNull;

import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        requireNonNull(index, "no index was indicated!");
        this.index = index;
    }

    @Override
    public void execute(Ui ui, ItemList itemList) {
        Item removedItem = itemList.removeItem(index);
        ui.showOutput(removedItem + " has been deleted.");
    }
}
