package seedu.duke.command.itemcommand;

import seedu.duke.command.Command;
import seedu.duke.HotelLiteManagerException;
import seedu.duke.EmptyItemPaxException;
import seedu.duke.EmptyItemNameException;
import seedu.duke.InvalidCommandException;
import seedu.duke.Item;
import seedu.duke.ItemList;
import seedu.duke.Ui;
import seedu.duke.ListContainer;
import seedu.duke.InvalidItemPaxException;

import java.util.Locale;
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
    private Item item;
    private static Logger itemLogger = Logger.getLogger("itemLogger");

    /**
     * Takes in the user input and checks if the formatting of the Add Item Command within the user input is
     * valid.
     * Extracts out the item name and item pax from the user input and creates an AddItemCommand object.
     *
     * @param userInput The user's input.
     * @throws HotelLiteManagerException if the formatting of the add item pax command is invalid, the item pax is
     *                                   empty or invalid, the item name is empty, or the item name and pax are both
     *                                   empty.
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
     * @param token A string token containing the user input after the delimiter.
     * @return The item pax within the user input.
     * @throws HotelLiteManagerException if item pax is empty or invalid.
     */

    private int extractItemPax(StringTokenizer token) throws HotelLiteManagerException {
        int itemPax;
        String itemPaxStringVersion = token.nextToken();
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
        if (itemPax <= 0) {
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
     * @param ui The object that deals with user interface for the program.
     * @throws HotelLiteManagerException if the item name within the item object does not exist in the item list.
     */
    public void execute(ListContainer listContainer, Ui ui) throws HotelLiteManagerException {
        ItemList listOfItems = listContainer.getItemList();
        Item item = getItem();
        assert (item != null) : "Assertion Failed! There is no item within the AddItemCommand object.";
        boolean isItemAlreadyInTheList = listOfItems.addItemToList(item);
        if (isItemAlreadyInTheList == true) {
            String nameOfItemToAdd = item.getName();
            nameOfItemToAdd = nameOfItemToAdd.toUpperCase();
            ui.printItemAlreadyInTheListErrorMessage(nameOfItemToAdd);
            return;
        }
        ui.printAddItemAcknowledgementMessage(listOfItems);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
