package seedu.duke.exceptions;

public class InvalidHelpException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! You might try typing: help";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}