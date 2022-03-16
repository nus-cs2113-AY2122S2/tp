package seedu.duke;

/**
 * An exception for the "Add Housekeeper [housekeeperName] ~ [age] command.
 * This exception is thrown if the add housekeeper command has a given age which is not an integer.
 */
public class InvalidAgeException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! AGE must be integer";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}