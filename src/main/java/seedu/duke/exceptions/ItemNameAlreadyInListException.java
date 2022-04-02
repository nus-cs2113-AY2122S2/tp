package seedu.duke.exceptions;

public class ItemNameAlreadyInListException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! The new item name already exists within the item list.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
