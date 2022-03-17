package seedu.duke.commands;

import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.exceptions.InvMgrException;
import seedu.duke.ui.Ui;

public class DescCommand extends Command {
    private final int index;

    public DescCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(ItemList itemList, Ui ui) {
        // Get item from itemList by searching for the item's name
        Item item = itemList.getItem(index);

        // Print item name and description
        ui.showMessages("Name of Item: " + item.getName() + System.lineSeparator()
                + "Description: " + item.getDescription());
    }
}
