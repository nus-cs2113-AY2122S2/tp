package seedu.duke.exceptions;

/**
 * Represents an exception which would be thrown when the new item name that the user wants to update an item
 * to already exists within the item list.
 */
public class ItemNameAlreadyInListException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! The new item name already exists within the item list.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
