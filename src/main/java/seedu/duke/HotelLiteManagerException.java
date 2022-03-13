package seedu.duke;

public abstract class HotelLiteManagerException extends Exception {
    private final String ERROR_MESSAGE = "";

    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}