package seedu.duke.command.itemcommands;

import seedu.duke.command.Command;
import seedu.duke.exceptions.DuplicateCommandException;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.itemlists.Item;
import seedu.duke.exceptions.EmptyItemNameException;
import seedu.duke.ListContainer;
import seedu.duke.Ui;
import seedu.duke.itemlists.ItemList;
import seedu.duke.storage.ItemListFileManager;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a command to delete an existing item within the item list. A DeleteItemCommand object consists of the
 * name of the item to delete.
 */
public class DeleteItemCommand extends Command {
    private Item item;
    private static final String DELETE_ITEM_COMMAND = "delete item";
    private static Logger itemLogger = Logger.getLogger("itemLogger");

    /**
     * Takes in the user input and checks if the formatting of the delete item command within the user input is
     * valid.
     * Takes the user input and creates an DeleteItemCommand object using it.
     *
     * @param userInput The user's input.
     * @throws HotelLiteManagerException if the item name is empty.
     */
    public DeleteItemCommand(String userInput) throws HotelLiteManagerException {
        if (userInput.isEmpty()) {
            throw new EmptyItemNameException();
        }

        if (userInput.contains(DELETE_ITEM_COMMAND)) {
            itemLogger.log(Level.WARNING, "Repeated delete item command given.");
            throw new DuplicateCommandException();
        }

        String itemName = userInput.trim();
        Item item = new Item(itemName.trim());
        setItem(item);
    }

    public Item getItem() {
        return item;
    }

    private void setItem(Item item) {
        this.item = item;
    }

    /**
     * Deletes the item in the item list specified by the user.
     * Returns an acknowledgement message to inform the user that the item has been deleted to the item list as well
     * as the number of items within the item list.
     * Updates the item list saved within the file ListFolder/ItemList.txt
     *
     * @param listContainer The object containing the data structure necessary to delete items from the item
     *                      list.
     *                      In this case, we require access to the ItemList object which is within listContainer.
     * @param ui            The object that deals with user interface for the program.
     * @throws HotelLiteManagerException if the item name within the item object does not exist in the item list.
     * @throws IOException               if we are unable to write to the file ListFolder/ItemList.txt
     */

    @Override
    public void execute(ListContainer listContainer, Ui ui) throws HotelLiteManagerException {
        Item itemToDelete = getItem();
        ItemList listOfItems = listContainer.getItemList();
        listOfItems.deleteItemInList(itemToDelete);
        ui.printDeleteItemAcknowledgementMessage(itemToDelete, listOfItems);
    }

    public void writeItemListToFile(ListContainer listContainer) throws IOException {
        ItemList listOfItems = listContainer.getItemList();
        ItemListFileManager itemListFileManager = new ItemListFileManager();
        itemListFileManager.writeItemListToFile(listOfItems);
    }
}
