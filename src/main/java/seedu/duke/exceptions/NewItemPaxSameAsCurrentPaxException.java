package seedu.duke.exceptions;

/**
 * Represents an exception which would be thrown when the new item pax is the same as the current item pax of the item
 * that the user wants to update.
 */
public class NewItemPaxSameAsCurrentPaxException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! The new item pax is the same as the current item pax.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }

}