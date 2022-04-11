package seedu.duke.exceptions;

/**
 * Represents an exception which would be thrown when the user enters an empty item pax in the Add Item, Update Item Pax
 * Command.
 */
public class EmptyItemPaxException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Item Pax cannot be empty.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
