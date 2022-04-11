package seedu.duke.exceptions;


public class InvalidEventException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Please add event with format: event DESCRIPTION / DATE."
            + " The date should be in format yyyy-mm-dd.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}