package seedu.duke;

public class AddItemCommand extends Command {
    private static final String ITEM_NAME_INDICATOR = "Name:";
    private static final int ITEM_NAME_INDICATOR_LENGTH = 5;
    private static final String ITEM_PAX_INDICATOR = "Pax:";
    private static final int ITEM_PAX_INDICATOR_LENGTH = 4;
    private Item item;

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

    @Override
    public void execute() {}

    @Override
    public void execute(SatisfactionList satisfactionList) {}

    public String getItemName() {
        return itemName;
    }

    private void setItemName(String itemName) {
        this.itemName = itemName;
    }
  
    public void execute(ItemList listOfItems, Ui ui) {
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
