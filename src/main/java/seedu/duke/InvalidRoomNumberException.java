package seedu.duke;

public class InvalidRoomNumberException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Room number is invalid";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
