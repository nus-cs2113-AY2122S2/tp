package seedu.duke.command.itemcommand;

import seedu.duke.command.Command;
import seedu.duke.ItemList;
import seedu.duke.ListContainer;
import seedu.duke.Ui;

public class ViewItemWithZeroPaxCommand extends Command {
    public void execute(ListContainer listContainer, Ui ui) {
        ItemList listOfItems = listContainer.getItemList();
        ItemList listOfItemsWithZeroPax = listOfItems.findItemsWithZeroPaxInList();
        ui.printItemList(listOfItemsWithZeroPax);
    }
}
