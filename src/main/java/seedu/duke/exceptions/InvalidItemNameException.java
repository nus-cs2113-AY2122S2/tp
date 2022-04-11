package seedu.duke.exceptions;

/**
 * Represents an exception which would be thrown when the user enters an item name which violates the restriction where
 * item names can only contain alphabetical characters, numbers, blank spaces or apostrophes.
 */
public class InvalidItemNameException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! The item name is invalid. Item Names can only contain "
            + "alphabetical characters, numbers, blank spaces or apostrophes.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
