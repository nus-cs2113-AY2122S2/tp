package seedu.duke.commands;

import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.exceptions.InvMgrException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

public class DescCommand extends Command {
    private final String itemName;

    public DescCommand(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public void execute(ItemList itemList, Ui ui, Storage storage) {
        try {
            // Get item from itemList by searching for itemName
            Item item = itemList.contains(itemName);

            // Get description of item found
            String itemDescription = item.getDescription();

            // Print item name and description
            ui.showOutput("Name of Item: " + itemName + System.lineSeparator()
                    + "Description: " + itemDescription);
        } catch (InvMgrException e) {
            ui.showOutput("Please enter another search phrase");
        }
    }
}
