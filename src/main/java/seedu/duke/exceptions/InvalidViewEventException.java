package seedu.duke.exceptions;

public class InvalidViewEventException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Please view events with format: view events.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
