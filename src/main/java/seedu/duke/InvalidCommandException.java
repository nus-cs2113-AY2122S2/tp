package seedu.duke;

/**
 * Represents an exception which would be thrown when the format of the Add Item, View Item or Update Item Pax command
 * that the user inputs is invalid.
 */
public class InvalidCommandException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Invalid Command.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
