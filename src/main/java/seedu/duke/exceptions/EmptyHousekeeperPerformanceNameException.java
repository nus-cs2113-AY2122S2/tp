package seedu.duke.exceptions;

public class EmptyHousekeeperPerformanceNameException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Housekeeper name cannot be empty.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
