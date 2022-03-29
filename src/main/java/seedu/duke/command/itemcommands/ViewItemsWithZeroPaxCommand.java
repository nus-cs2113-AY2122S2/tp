package seedu.duke.command.itemcommands;

import seedu.duke.HotelLiteManagerException;
import seedu.duke.command.Command;
import seedu.duke.ItemList;
import seedu.duke.ListContainer;
import seedu.duke.Ui;
import seedu.duke.InvalidViewItemsWithZeroPaxCommandException;

public class ViewItemsWithZeroPaxCommand extends Command {
    public ViewItemsWithZeroPaxCommand(String userInput) throws HotelLiteManagerException {
        if (!userInput.isEmpty()) {
            throw new InvalidViewItemsWithZeroPaxCommandException();
        }
    }

    public void execute(ListContainer listContainer, Ui ui) {
        ItemList listOfItems = listContainer.getItemList();
        ItemList listOfItemsWithZeroPax = listOfItems.findItemsWithZeroPaxInList();
        if (listOfItems.getSize() == 0) {
            ui.printNoItemsFoundInListAcknowledgementMessage();
            return;
        }
        ui.printItemList(listOfItemsWithZeroPax);
    }
}
