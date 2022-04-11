package seedu.duke.exceptions;

public class FolderCreationFailException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Unable to create the file required for storage of the various "
            + "lists.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
