package seedu.duke.exceptions;

public abstract class HotelLiteManagerException extends Exception {
    private static final String ERROR_MESSAGE = "";

    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}