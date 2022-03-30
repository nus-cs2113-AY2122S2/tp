package seedu.duke.command.itemcommands;

import seedu.duke.command.Command;
import seedu.duke.HotelLiteManagerException;
import seedu.duke.InvalidUpdateItemNameCommandException;
import seedu.duke.ListContainer;
import seedu.duke.Ui;
import seedu.duke.ItemList;
import seedu.duke.storage.ItemListFileManager;
import seedu.duke.DuplicateItemNameException;

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
    private String oldItemName;
    private String newItemName;
    private static final int NUMBER_OF_PARTS_IN_COMMAND = 2;
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
        StringTokenizer tokens = new StringTokenizer(userInput, DELIMITER);
        if (tokens.countTokens() != NUMBER_OF_PARTS_IN_COMMAND) {
            itemLogger.log(Level.WARNING, "Invalid formatting for the Update Item Name Command detected."
                    + " Exception thrown.");
            throw new InvalidUpdateItemNameCommandException();
        }
        String oldItemName = extractCurrentItemName(tokens);
        String newItemName = extractNewItemName(tokens);
        setOldItemName(oldItemName);
        setNewItemName(newItemName);
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

    public String getOldItemName() {
        return oldItemName;
    }

    public void setOldItemName(String oldItemName) {
        this.oldItemName = oldItemName;
    }

    public String getNewItemName() {
        return newItemName;
    }

    public void setNewItemName(String newItemName) {
        this.newItemName = newItemName;
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
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui) throws HotelLiteManagerException, IOException {
        ItemList listOfItems = listContainer.getItemList();
        String oldItemName = getOldItemName();
        String newItemName = getNewItemName();
        listOfItems.updateItemNameInList(oldItemName, newItemName);
        oldItemName = oldItemName.toUpperCase();
        newItemName = newItemName.toUpperCase();
        if (oldItemName.equals(newItemName)) {
            throw new DuplicateItemNameException();
        }
        ui.printUpdateItemNameAcknowledgementMessage(oldItemName, newItemName);
        ItemListFileManager itemListFileManager = new ItemListFileManager();
        itemListFileManager.writeItemListToFile(listOfItems);
    }
}
