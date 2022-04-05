package seedu.duke.exceptions;

public class InvalidHousekeeperPerformanceNameException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Housekeeper's name must only contain alphabetical characters.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
