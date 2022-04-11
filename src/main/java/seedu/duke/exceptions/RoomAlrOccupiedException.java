package seedu.duke.exceptions;

public class RoomAlrOccupiedException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! The room is already occupied!";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}