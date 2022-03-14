package seedu.duke;

public class AddItemCommand extends Command {
    private String itemName;
    private int itemPax;
    private static final String ITEM_NAME_INDICATOR = "Name:";
    private static final int ITEM_NAME_INDICATOR_LENGTH = 5;
    private static final String ITEM_PAX_INDICATOR = "Pax:";
    private static final int ITEM_PAX_INDICATOR_LENGTH = 4;

    public AddItemCommand(String userInput) throws HotelLiteManagerException {
        boolean isValidAddItemCommand = userInput.contains(ITEM_NAME_INDICATOR)
                && userInput.contains(ITEM_PAX_INDICATOR);
        if (!isValidAddItemCommand) {
            throw new InvalidCommandException();
        }
        int itemPax = extractItemPax(userInput);
        String itemName = extractItemName(userInput);
        setItemName(itemName);
        setItemPax(itemPax);
    }

    private String extractItemName(String userInput) throws HotelLiteManagerException {
        String itemName;
        int itemNameStartingPosition = userInput.indexOf(ITEM_NAME_INDICATOR) + ITEM_NAME_INDICATOR_LENGTH;
        int itemPaxIndicatorPosition = userInput.indexOf(ITEM_PAX_INDICATOR);
        itemName = userInput.substring(itemNameStartingPosition, itemPaxIndicatorPosition);
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
        return itemPax;
    }

    @Override
    public void execute() {
    }

    public String getItemName() {
        return itemName;
    }

    private void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPax() {
        return itemPax;
    }

    private void setItemPax(int itemPax) {
        this.itemPax = itemPax;
    }

}
