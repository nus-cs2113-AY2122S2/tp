package seedu.duke.exceptions;

public class DuplicateCommandException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Duplicate command string detected. Please enter the "
            + "command string only once.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
