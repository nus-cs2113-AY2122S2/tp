package seedu.duke.exceptions;

/**
 * Represents an exception which would be thrown when the user enters an invalid format for the update item pax
 * command.
 */
public class InvalidUpdateItemPaxCommandException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Invalid Command -> Update Item Pax ITEM NAME / NEW PAX";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
