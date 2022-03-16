package seedu.duke;

/**
 * Represents a command to add a new item to the item list. An AddItemCommand object consists of the name of the item
 * to add as well as its pax.
 */
public class AddItemCommand extends Command {
    private static final String ITEM_NAME_INDICATOR = "Name:";
    private static final int ITEM_NAME_INDICATOR_LENGTH = 5;
    private static final String ITEM_PAX_INDICATOR = "Pax:";
    private static final int ITEM_PAX_INDICATOR_LENGTH = 4;
    private Item item;

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
        boolean isValidAddItemCommand = userInput.contains(ITEM_NAME_INDICATOR)
                && userInput.contains(ITEM_PAX_INDICATOR);
        if (!isValidAddItemCommand) {
            throw new InvalidCommandException();
        }
        int itemPax = extractItemPax(userInput);
        String itemName = extractItemName(userInput);
        Item item = new Item(itemName, itemPax);
        setItem(item);
    }

    /**
     * Returns the item name extracted from the user input.
     *
     * @param userInput The user's input.
     * @return The item name within the user input.
     * @throws HotelLiteManagerException if item name is empty.
     */
    private String extractItemName(String userInput) throws HotelLiteManagerException {
        int itemNameStartingPosition = userInput.indexOf(ITEM_NAME_INDICATOR) + ITEM_NAME_INDICATOR_LENGTH;
        int itemPaxIndicatorPosition = userInput.indexOf(ITEM_PAX_INDICATOR);
        String itemName = userInput.substring(itemNameStartingPosition, itemPaxIndicatorPosition);
        itemName = itemName.trim();
        if (itemName.isEmpty()) {
            throw new EmptyItemNameException();
        }
        return itemName;
    }

    /**
     * Returns the item pax extracted from the user input.
     *
     * @param userInput The user's input.
     * @return The item pax within the user input.
     * @throws HotelLiteManagerException if item pax is empty or invalid.
     */
    private int extractItemPax(String userInput) throws HotelLiteManagerException {
        int itemPax;
        String itemPaxStringVersion;
        int stringEndingPosition = userInput.length();
        int itemPaxStartingPosition = userInput.indexOf(ITEM_PAX_INDICATOR) + ITEM_PAX_INDICATOR_LENGTH;
        if (itemPaxStartingPosition == stringEndingPosition) {
            throw new EmptyItemPaxException();
        }
        itemPaxStringVersion = userInput.substring(itemPaxStartingPosition);
        try {
            itemPax = Integer.parseInt(itemPaxStringVersion);
        } catch (NumberFormatException e) {
            throw new InvalidItemPaxException();
        }
        if (itemPax <= 0) {
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
     * @param housekeeperList  The list of housekeeper recorded. N/A for this class, but
     *                         must be included for the execution override.
     * @param satisfactionList The given list of Satisfaction objects. N/A for this class, but
     *                         must be included for the execution override.
     * @param assignmentMap    The assignments of the various housekeepers.
     * @param roomList         The given list of Room objects. N/A for this class, but
     *                         must be included for the execution override.
     * @param listOfItems      The list of items within the inventory.
     * @param ui               The object that deals with user interface for the program.
     * @throws HotelLiteManagerException if the item name within the item object does not exist in the item list.
     */
    public void execute(HousekeeperList housekeeperList, SatisfactionList satisfactionList,
                        AssignmentMap assignmentMap, RoomList roomList, ItemList listOfItems, Ui ui) throws
            HotelLiteManagerException, WrongCommandException {
        Item item = getItem();
        listOfItems.addItemToList(item);
        ui.printAddItemAcknowledgementMessage(listOfItems);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
