package seedu.duke.command.itemcommands;

import seedu.duke.command.Command;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.EmptyItemPaxException;
import seedu.duke.exceptions.EmptyItemNameException;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.exceptions.InvalidItemPaxException;
import seedu.duke.exceptions.ItemAlreadyInListException;
import seedu.duke.exceptions.DuplicateCommandException;

import seedu.duke.itemlists.Item;
import seedu.duke.itemlists.ItemList;
import seedu.duke.Ui;
import seedu.duke.ListContainer;
import seedu.duke.storage.ItemListFileManager;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Represents a command to add a new item to the item list. An AddItemCommand object consists of the name of the item
 * to add as well as its pax.
 */
public class AddItemCommand extends Command {
    private static final String DELIMITER = "/";
    private static final int NUMBER_OF_PARTS_IN_COMMAND = 2;
    private static final String ADD_ITEM_COMMAND = "add item";
    private Item item;
    private static Logger itemLogger = Logger.getLogger("itemLogger");

    /**
     * Takes in the user input and checks if the formatting of the add item command within the user input is
     * valid.
     * Extracts out the item name and item pax from the user input and creates an AddItemCommand object.
     *
     * @param userInput The user's input.
     * @throws HotelLiteManagerException if the edited user input still contains the string "add item", the formatting
     *                                   of the add item command is invalid, the item pax is empty or invalid, the item
     *                                   name is empty, or the item name and pax are both empty.
     */
    public AddItemCommand(String userInput) throws HotelLiteManagerException {
        if (userInput.startsWith(DELIMITER)) {
            itemLogger.log(Level.WARNING, "Detected an empty item name for AddItemCommand. Exception thrown.");
            throw new EmptyItemNameException();
        }
        if (userInput.endsWith(DELIMITER)) {
            itemLogger.log(Level.WARNING, "Detected an empty item name for AddItemCommand. Exception thrown.");
            throw new EmptyItemPaxException();
        }

        if (userInput.contains(ADD_ITEM_COMMAND)) {
            itemLogger.log(Level.WARNING, "Repeated add item command given.");
            throw new DuplicateCommandException();
        }

        StringTokenizer tokens = new StringTokenizer(userInput, DELIMITER);
        if (tokens.countTokens() != NUMBER_OF_PARTS_IN_COMMAND) {
            itemLogger.log(Level.WARNING, "Invalid formatting for AddItemCommand detected. Exception thrown.");
            throw new InvalidCommandException();
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
            itemLogger.log(Level.WARNING, "Detected an empty item name for AddItemCommand. Exception thrown.");
            throw new EmptyItemNameException();
        }
        return itemName;
    }

    /**
     * Returns the item pax extracted from the user input.
     *
     * @param tokens String tokens containing the user input after the delimiter.
     * @return The item pax within the user input.
     * @throws HotelLiteManagerException if item pax is empty or invalid.
     */

    private int extractItemPax(StringTokenizer tokens) throws HotelLiteManagerException {
        int itemPax;
        String itemPaxStringVersion = tokens.nextToken();
        itemPaxStringVersion = itemPaxStringVersion.trim();
        if (itemPaxStringVersion.isEmpty()) {
            itemLogger.log(Level.WARNING, "Detected an empty item name for AddItemCommand. Exception thrown.");
            throw new EmptyItemPaxException();
        }
        try {
            itemPax = Integer.parseInt(itemPaxStringVersion);
        } catch (NumberFormatException e) {
            itemLogger.log(Level.WARNING, "Detected an invalid item pax for AddItemCommand. Exception thrown.");
            throw new InvalidItemPaxException();
        }
        if (itemPax <= 0 || itemPax > 1000000) {
            itemLogger.log(Level.WARNING, "Detected an invalid item pax for AddItemCommand. Exception thrown.");
            throw new InvalidItemPaxException();
        }
        return itemPax;
    }

    /**
     * Adds a new item to the item list using the item name and pax found in the instance variable
     * named item within the AddItemPaxCommand object.
     * Returns an acknowledgement message to inform the user that the item has been added to the item list as well
     * as the number of items within the item list.
     *
     * @param listContainer The object containing the data structure necessary for adding an item into the item list.
     *                      In this case, we require access to the ItemList object which is within listContainer.
     * @param ui            The object that deals with user interface for the program.
     * @throws HotelLiteManagerException if the item name within the item object already exists in the item list.
     */
    public void execute(ListContainer listContainer, Ui ui) throws HotelLiteManagerException {
        ItemList listOfItems = listContainer.getItemList();
        Item item = getItem();
        String nameOfItemToAdd = item.getName();
        assert (item != null) : "Assertion Failed! There is no item within the AddItemCommand object.";
        boolean isItemAlreadyInTheList = listOfItems.checkForItemDuplicates(nameOfItemToAdd);
        if (isItemAlreadyInTheList == true) {
            throw new ItemAlreadyInListException();
        }

        listOfItems.addItemToList(item);
        ui.printAddItemAcknowledgementMessage(listOfItems);
    }

    /**
     * Updates the item list saved within the file ListFolder/ItemList.txt with the current item list.
     *
     * @param listContainer The object containing the data structure necessary for adding an item into the item list.
     *                      In this case, we require access to the ItemList object which is within listContainer.
     * @throws IOException if we are unable to write to the file ListFolder/ItemList.txt
     */
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
