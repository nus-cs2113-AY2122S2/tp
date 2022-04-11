package seedu.duke.command.itemcommands;

import seedu.duke.command.Command;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.InvalidUpdateItemNameCommandException;
import seedu.duke.exceptions.ItemNameAlreadyInListException;
import seedu.duke.exceptions.DuplicateItemNameException;
import seedu.duke.exceptions.DuplicateCommandException;

import seedu.duke.ListContainer;
import seedu.duke.Ui;
import seedu.duke.itemlists.Item;
import seedu.duke.itemlists.ItemList;
import seedu.duke.storage.ItemListFileManager;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a command to update the name of an existing item within the item list.
 * A UpdateItemNameCommand object consists of the name of the item which the user wants to update (oldItemName) and the
 * name the user wants to update the item to (newItemName).
 */
public class UpdateItemNameCommand extends Command {
    private static final String DELIMITER = "/";
    private Item item;
    private static final int NUMBER_OF_PARTS_IN_COMMAND = 2;
    private static final String UPDATE_ITEM_NAME_COMMAND = "update item name";
    private static Logger itemLogger = Logger.getLogger("itemLogger");

    /**
     * Takes in the user input and checks if the formatting of the update item name command within the user input is
     * valid.
     * Extracts out from the user input the current name of the item to update as well as its new name.
     *
     * @param userInput The user's input.
     * @throws HotelLiteManagerException if the formatting of the update item name command is invalid, the current item
     *                                   name is empty or the new item name is empty.
     */
    public UpdateItemNameCommand(String userInput) throws HotelLiteManagerException {
        if (userInput.startsWith(DELIMITER)) {
            itemLogger.log(Level.WARNING, "The item name of the item to update is empty for the Update Item Name "
                    + "Command. Exception thrown.");
            throw new InvalidUpdateItemNameCommandException();
        }
        if (userInput.endsWith(DELIMITER)) {
            itemLogger.log(Level.WARNING, "The new item name of the item to update is empty for the Update Item "
                    + "Name Command. Exception thrown");
            throw new InvalidUpdateItemNameCommandException();
        }

        if (userInput.contains(UPDATE_ITEM_NAME_COMMAND)) {
            itemLogger.log(Level.WARNING, "Repeated update item name command given.");
            throw new DuplicateCommandException();
        }

        StringTokenizer tokens = new StringTokenizer(userInput, DELIMITER);
        if (tokens.countTokens() != NUMBER_OF_PARTS_IN_COMMAND) {
            itemLogger.log(Level.WARNING, "Invalid formatting for the Update Item Name Command detected."
                    + " Exception thrown.");
            throw new InvalidUpdateItemNameCommandException();
        }
        String oldItemName = extractCurrentItemName(tokens);
        String newItemName = extractNewItemName(tokens);
        Item item = new Item(oldItemName, newItemName);
        setItem(item);
    }

    /**
     * Returns the current item name of the item to update (oldItemName) that is extracted from the user input.
     *
     * @param tokens String tokens of the user input.
     * @return The current name of the item name to update.
     * @throws HotelLiteManagerException if the current item name is empty within the user input.
     */
    private String extractCurrentItemName(StringTokenizer tokens) throws HotelLiteManagerException {
        String oldItemName = tokens.nextToken();
        oldItemName = oldItemName.trim();
        if (oldItemName.isEmpty()) {
            itemLogger.log(Level.WARNING, "Detected an empty item name for the item whose name we want to update"
                    + "in the Update Item Name Command. Exception thrown.");
            throw new InvalidUpdateItemNameCommandException();
        }
        return oldItemName;
    }

    /**
     * Returns the new item name for the item that is to be updated (newItemName) which is extracted from the user
     * input.
     *
     * @param tokens String tokens of the user input.
     * @return The current name of the item name to update.
     * @throws HotelLiteManagerException if the current item name is empty within the user input.
     */
    private String extractNewItemName(StringTokenizer tokens) throws HotelLiteManagerException {
        String newItemName = tokens.nextToken();
        newItemName = newItemName.trim();
        if (newItemName.isEmpty()) {
            itemLogger.log(Level.WARNING, "The new name of the item whose name we want to update is empty"
                    + "in the Update Item Name Command. Exception thrown.");
            throw new InvalidUpdateItemNameCommandException();
        }
        return newItemName;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Updates the item name of the item within the item list whose name matches the old item name to the new item name.
     * Returns an acknowledgement message to inform the user that the name of the updated item has been changed from
     * the old item name to the new item name.
     * Updates the item list saved within the file ListFolder/ItemList.txt
     *
     * @param listContainer The object containing the data structure necessary for updating the name of an item within
     *                      the item list.
     *                      In this case, we require access to the ItemList object which is within listContainer.
     * @param ui            The object that deals with user interface for the program.
     * @throws HotelLiteManagerException if the item name within the item object does not exist in the item list.
     * @throws IOException               if we are unable to write to the file ListFolder/ItemList.txt
     * @return
     */
    @Override
    public Object execute(ListContainer listContainer, Ui ui) throws HotelLiteManagerException {
        ItemList listOfItems = listContainer.getItemList();
        Item item = getItem();
        String oldItemName = item.getName();
        String newItemName = item.getUpdatedName();
        if (oldItemName.equals(newItemName)) {
            throw new DuplicateItemNameException();
        }
        boolean isNewItemNameAlreadyInList = listOfItems.checkForItemDuplicates(newItemName);
        if (isNewItemNameAlreadyInList == true) {
            throw new ItemNameAlreadyInListException();
        }
        listOfItems.updateItemNameInList(oldItemName, newItemName);
        oldItemName = oldItemName.toUpperCase();
        newItemName = newItemName.toUpperCase();
        ui.printUpdateItemNameAcknowledgementMessage(oldItemName, newItemName);
        return null;
    }

    public void writeItemListToFile(ListContainer listContainer) throws IOException {
        ItemList listOfItems = listContainer.getItemList();
        ItemListFileManager itemListFileManager = new ItemListFileManager();
        itemListFileManager.writeItemListToFile(listOfItems);
    }
}
