package seedu.duke.command.itemcommands;

import seedu.duke.command.Command;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.EmptyItemNameException;
import seedu.duke.exceptions.EmptyItemPaxException;
import seedu.duke.itemlists.Item;
import seedu.duke.itemlists.ItemList;
import seedu.duke.Ui;
import seedu.duke.ListContainer;
import seedu.duke.exceptions.InvalidItemPaxException;
import seedu.duke.exceptions.InvalidUpdateItemPaxCommandException;
import seedu.duke.exceptions.DuplicateCommandException;

import seedu.duke.storage.ItemListFileManager;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Represents a command to update the pax of an item within the item list. An UpdateItemPaxCommand object consists of
 * the name of the item to update and the new pax value.
 */
public class UpdateItemPaxCommand extends Command {
    private Item item;
    private static final String DELIMITER = "/";
    private static final int NUMBER_OF_PARTS_IN_COMMAND = 2;
    private static final String UPDATE_ITEM_PAX_COMMAND = "update item pax";
    private static Logger itemLogger = Logger.getLogger("itemLogger");

    /**
     * Takes in the user input and checks if the formatting of the Update Item Pax Command within the user input is
     * valid.
     * Extracts out the item name and item pax from the user input and creates an UpdateItemPaxCommand object.
     *
     * @param userInput The user's input.
     * @throws HotelLiteManagerException if the formatting of the update item pax command is invalid, the item pax is
     *                                   empty or invalid, the item name is empty, or the item name and pax are both
     *                                   empty.
     */
    public UpdateItemPaxCommand(String userInput) throws HotelLiteManagerException {
        if (userInput.startsWith(DELIMITER)) {
            itemLogger.log(Level.WARNING, "Detected an empty item name for UpdateItemCommand. "
                    + "Exception thrown.");
            throw new EmptyItemNameException();
        }
        if (userInput.endsWith(DELIMITER)) {
            itemLogger.log(Level.WARNING, "Detected an empty item Pax for UpdateItemCommand. "
                    + "Exception thrown.");
            throw new EmptyItemPaxException();
        }

        if (userInput.contains(UPDATE_ITEM_PAX_COMMAND)) {
            itemLogger.log(Level.WARNING, "Repeated update item pax command given.");
            throw new DuplicateCommandException();
        }

        StringTokenizer tokens = new StringTokenizer(userInput, DELIMITER);
        if (tokens.countTokens() != NUMBER_OF_PARTS_IN_COMMAND) {
            itemLogger.log(Level.WARNING, "Invalid formatting for UpdateItemCommand detected."
                    + " Exception thrown.");
            throw new InvalidUpdateItemPaxCommandException();
        }
        String itemName = extractItemName(tokens);
        int itemPax = extractItemPax(tokens);
        Item item = new Item(itemName, itemPax);
        setItem(item);
    }

    /**
     * Returns the item name extracted from the user input.
     *
     * @param tokens String tokens of the user input.
     * @return The item name within the user input.
     * @throws HotelLiteManagerException if item name is empty.
     */
    private String extractItemName(StringTokenizer tokens) throws HotelLiteManagerException {
        String itemName = tokens.nextToken();
        itemName = itemName.trim();
        if (itemName.isEmpty()) {
            itemLogger.log(Level.WARNING, "Detected an empty item name for UpdateItemPaxCommand. "
                    + "Exception thrown.");
            throw new EmptyItemNameException();
        }
        return itemName;
    }

    /**
     * Returns the item pax extracted from the user input.
     *
     * @param token A string token containing the user input after the delimiter.
     * @return The item pax within the user input.
     * @throws HotelLiteManagerException if item pax is empty or invalid.
     */

    private int extractItemPax(StringTokenizer token) throws HotelLiteManagerException {
        int itemPax;
        String itemPaxStringVersion = token.nextToken();
        itemPaxStringVersion = itemPaxStringVersion.trim();

        if (itemPaxStringVersion.isEmpty()) {
            itemLogger.log(Level.WARNING, "Detected an empty item pax for UpdateItemPaxCommand. "
                    + "Exception thrown.");
            throw new EmptyItemPaxException();
        }
        try {
            itemPax = Integer.parseInt(itemPaxStringVersion);
        } catch (NumberFormatException e) {
            itemLogger.log(Level.WARNING, "Detected an invalid item pax for UpdateItemPaxCommand. "
                    + "Exception thrown.");
            throw new InvalidItemPaxException();
        }
        if (itemPax < 0 || itemPax > 1000000) {
            itemLogger.log(Level.WARNING, "Detected an invalid item pax for UpdateItemPaxCommand. "
                    + "Exception thrown.");
            throw new InvalidItemPaxException();
        }
        return itemPax;
    }

    /**
     * Updates the pax of the item within the item list using the item name and new pax found in the instance
     * variable named item within the UpdateItemPaxCommand object.
     *
     * @param ui The object that deals with user interface for the program.
     * @throws HotelLiteManagerException if the item name within the item object does not exist in the item list.
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui) throws HotelLiteManagerException {
        ItemList listOfItems = listContainer.getItemList();
        assert (item != null) : "Assertion Failed! There is no item within the UpdateItemPaxCommand object.";
        listOfItems.updateItemPaxInList(item);
        ui.printUpdateItemPaxAcknowledgementMessage(item);
    }

    public void writeItemListToFile(ListContainer listContainer) throws IOException {
        ItemList listOfItems = listContainer.getItemList();
        ItemListFileManager itemListFileManager = new ItemListFileManager();
        itemListFileManager.writeItemListToFile(listOfItems);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
