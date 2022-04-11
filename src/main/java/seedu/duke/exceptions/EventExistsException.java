package seedu.duke.exceptions;


public class EventExistsException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Event already exists.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
