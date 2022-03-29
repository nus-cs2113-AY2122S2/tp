package seedu.duke;

public class InvalidEventException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Please add event with format: event DESCRIPTION ^ DATE";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}