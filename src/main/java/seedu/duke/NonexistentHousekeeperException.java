package seedu.duke;

public class NonexistentHousekeeperException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! You are attempting to record a houseekeeper performance"
            + " for a housekeeper that does not exist in the housekeeper records.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
