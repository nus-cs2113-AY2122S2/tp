package seedu.duke;

/**
 * An exception to check room level.
 * This exception is thrown if the room level is beyond level range.
 */
public class InvalidLevelException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Room level is invalid";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
