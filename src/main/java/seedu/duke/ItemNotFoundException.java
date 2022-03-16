package seedu.duke;

/**
 * Represents an exception which would be thrown when the item that the user is searching for within the item list
 * is not found.
 */
public class ItemNotFoundException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Item not found in inventory. Kindly add the item into the "
            + "inventory first.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}