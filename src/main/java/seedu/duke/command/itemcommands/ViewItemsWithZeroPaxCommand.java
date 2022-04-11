package seedu.duke.command.itemcommands;

import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.command.Command;
import seedu.duke.itemlists.ItemList;
import seedu.duke.ListContainer;
import seedu.duke.Ui;
import seedu.duke.exceptions.InvalidViewItemsWithZeroPaxCommandException;

/**
 * Represents a command to view all the items currently within the item list that have a pax of zero.
 */
public class ViewItemsWithZeroPaxCommand extends Command {
    public ViewItemsWithZeroPaxCommand(String userInput) throws HotelLiteManagerException {
        if (!userInput.isEmpty()) {
            throw new InvalidViewItemsWithZeroPaxCommandException();
        }
    }

    /**
     * Prints out the item name ,pax as well as index for items within the item list that have a pax of zero.
     *  @param listContainer The object containing the data structures necessary for viewing all the items within the
     *                      item list with a pax of zero. In this case, we require access to the ItemList object which
     *                      is within listContainer.
     * @param ui            The object that deals with user interface for the program.
     * @return
     */
    @Override
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
