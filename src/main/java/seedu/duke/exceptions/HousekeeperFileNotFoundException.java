package seedu.duke.exceptions;

public class HousekeeperFileNotFoundException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE
            = "The file housekeeper_file.txt was not found and is unable to be created.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }

}