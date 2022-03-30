package seedu.duke.exceptions;

public class EmptyNameException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Name is empty";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
