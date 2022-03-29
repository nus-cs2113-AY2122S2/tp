package seedu.duke;

public class InvalidDateException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Date give must be in the format yyyy-mm-dd.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}