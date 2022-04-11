package seedu.duke.exceptions;

/**
 * Represents an exception which would be thrown when the user enters an invalid format for the delete item command.
 */

public class InvalidDeleteItemCommandException extends HotelLiteManagerException {

    private static final String ERROR_MESSAGE = "Error! Invalid Delete Item Command -> Delete Item ITEM NAME ";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
