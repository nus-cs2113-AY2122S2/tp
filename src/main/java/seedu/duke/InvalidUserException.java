package seedu.duke;

/**
 * An exception to prevent multiple adding of the same name.
 * This exception is thrown if housekeeper profile has already been entered.
 */
public class InvalidUserException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! This person has already been recorded.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}

