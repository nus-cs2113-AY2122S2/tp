package seedu.duke.exceptions;

public class EventDoesNotExist  extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Event does not exist";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
