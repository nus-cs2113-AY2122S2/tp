package seedu.duke.exceptions;

import seedu.duke.exceptions.HotelLiteManagerException;

public class SatisfactionFileNotFoundException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "The file satisfaction_list.txt was not found and "
            + "is unable to be created.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
