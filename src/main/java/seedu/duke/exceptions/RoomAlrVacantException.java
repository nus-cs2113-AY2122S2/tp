package seedu.duke.exceptions;

public class RoomAlrVacantException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! The room is already vacant!";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
