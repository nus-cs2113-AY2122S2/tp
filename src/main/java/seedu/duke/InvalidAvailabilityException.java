package seedu.duke;

/**
 * An exception for the "Availability [housekeeperName] @ [daysAvailable] command.
 * This exception is thrown if the add housekeeper availability command has empty days available.
 */
public class InvalidAvailabilityException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Invalid Command -> Availability NAME @ M,T,W,T,F,S,S";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
