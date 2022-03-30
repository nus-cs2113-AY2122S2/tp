package seedu.duke.exceptions;

import seedu.duke.exceptions.HotelLiteManagerException;

/**
 * Represents an exception which would be thrown when the user enters an invalid format for the view all items command.
 */
public class InvalidViewItemsCommandException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Invalid Command -> view all items";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
