package seedu.duke.exceptions;

public class InvalidDeleteEventException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Please delete event with format: delete event INDEX.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}