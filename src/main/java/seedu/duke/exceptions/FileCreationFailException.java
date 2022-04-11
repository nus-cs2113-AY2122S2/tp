package seedu.duke.exceptions;

public class FileCreationFailException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Unable to create the folder required to store the file.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }

}
