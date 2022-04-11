package seedu.duke.exceptions;

public class InvalidDescriptionException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! The event description must only contain "
            + "alphanumeric characters.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }

}
