package seedu.duke.command.itemcommands;

import seedu.duke.command.Command;
import seedu.duke.ListContainer;
import seedu.duke.Ui;
import seedu.duke.itemlists.ItemList;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.InvalidViewItemsCommandException;

/**
 * Represents a command to view all the items currently within the item list.
 */
public class ViewItemListCommand extends Command {
    /**
     * Takes in the user input and checks if the formatting of the view item list command within the user input is
     * valid.
     * If the command is valid, it creates a ViewItemListCommand object.
     *
     * @param userInput The user's input.
     * @throws HotelLiteManagerException if the formatting of the view item list command that the user inputted is
     *                                   invalid.
     */
    public ViewItemListCommand(String userInput) throws HotelLiteManagerException {
        if (!userInput.isEmpty()) {
            throw new InvalidViewItemsCommandException();
        }
    }

    /**
     * Prints out the item name, pax as well as index for each item that is found within the item list.
     *
     * @param listContainer The object containing the data structures necessary for viewing all the items within the
     *                      item list. In this case, we require access to the ItemList object which is within
     *                      listContainer.
     * @param ui            The object that deals with user interface for the program.
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui) {
        ItemList listOfItems = listContainer.getItemList();
        ui.printItemList(listOfItems);
    }
}
