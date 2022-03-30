package seedu.duke.exceptions;

/**
 * Represents an exception which would be thrown when the user enters an empty day when querying for available
 * housekeepers.
 */
public class EmptyDayException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Day given were Empty";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
