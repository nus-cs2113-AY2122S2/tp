package seedu.duke.exceptions;

public class DuplicateItemNameException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! The item names cannot be the same.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }

}
