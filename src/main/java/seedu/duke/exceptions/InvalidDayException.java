package seedu.duke.exceptions;

public class InvalidDayException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Day give must be an Integer between 1 and 7.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
