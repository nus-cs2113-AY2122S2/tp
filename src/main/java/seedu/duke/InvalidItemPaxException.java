package seedu.duke;

/**
 * Represents an exception which would be thrown when the user enters an invalid item pax.
 */
public class InvalidItemPaxException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Item Pax is invalid.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
