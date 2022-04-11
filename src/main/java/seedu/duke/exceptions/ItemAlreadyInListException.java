package seedu.duke.exceptions;

/**
 * Represents an exception which would be thrown when the user tries to add an item that is already in the item list.
 */
public class ItemAlreadyInListException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! The item to add already exists within the item list.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
