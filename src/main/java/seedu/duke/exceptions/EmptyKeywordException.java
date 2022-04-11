package seedu.duke.exceptions;

public class EmptyKeywordException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! The keywords used to search for items within the item list "
            + "cannot be empty.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
