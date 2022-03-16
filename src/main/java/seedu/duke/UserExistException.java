package seedu.duke;

/**
 * This exception is thrown if housekeeper profile has has not been entered and user tries to add his/her availability.
 */
public class UserExistException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! User does not exist.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
