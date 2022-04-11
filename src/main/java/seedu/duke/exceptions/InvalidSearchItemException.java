package seedu.duke.exceptions;

/**
 * Represents an exception which would be thrown when the user enters an invalid format for the add item command.
 */
public class InvalidSearchItemException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Invalid Search Item Command -> Search Item KEYWORD";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}