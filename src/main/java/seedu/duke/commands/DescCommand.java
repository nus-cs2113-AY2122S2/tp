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
        try {
            // Get item from itemList by searching for the item's name
            Item item = itemList.get(index);

            // Get description of item found
            String itemDescription = item.getDescription();

            // Print item name and description
            ui.showOutput("Name of Item: " + item.toString() + System.lineSeparator()
                    + "Description: " + itemDescription);
        } catch (InvMgrException e) {
            ui.showOutput("Please enter another search phrase");
        }
    }
}
