package seedu.duke;

/**
 * An exception to check room number.
 * This exception is thrown if the room number is not in the room list.
 */
public class InvalidRoomNumberException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Room number is invalid";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
