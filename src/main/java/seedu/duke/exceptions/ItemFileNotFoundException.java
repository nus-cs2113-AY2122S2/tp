package seedu.duke.exceptions;

/**
 * Represents an exception which would be thrown if the item file ItemList.txt is not found.
 */
public class ItemFileNotFoundException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "The file ItemList.txt was not found and is unable to be created.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }

}
