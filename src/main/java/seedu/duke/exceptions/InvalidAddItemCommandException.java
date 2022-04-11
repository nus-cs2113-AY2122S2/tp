package seedu.duke.exceptions;

/**
 * Represents an exception which would be thrown when the user enters an invalid format for the add item command.
 */
public class InvalidAddItemCommandException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Invalid Add Item Command -> Add Item ITEM NAME / ITEM PAX";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}