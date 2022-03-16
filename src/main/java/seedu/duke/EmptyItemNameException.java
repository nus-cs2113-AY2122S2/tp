package seedu.duke;

/**
 * Represents an exception which would be thrown when the user enters an empty item name in the Add Item or Update Item
 * Pax Command.
 */
public class EmptyItemNameException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Item Name cannot be empty.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
