package seedu.duke.exceptions;

/**
 * Represents an exception which would be thrown when the user enters an invalid format for the view items with zero pax
 * command.
 */
public class InvalidViewItemsWithZeroPaxCommandException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Invalid Command -> view items with zero pax";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
