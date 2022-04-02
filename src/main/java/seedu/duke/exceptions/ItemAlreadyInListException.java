package seedu.duke.exceptions;

public class ItemAlreadyInListException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! The item to add already exists within the item list.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
