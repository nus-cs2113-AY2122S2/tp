package seedu.duke;

public class InvalidCommandException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Invalid Command";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
