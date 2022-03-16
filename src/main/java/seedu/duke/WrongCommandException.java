package seedu.duke;

/**
 * This exception is thrown if any user input command is not part of command or is invalid.
 */
public class WrongCommandException extends Exception {
    public WrongCommandException(String errorMessage) {
        super(errorMessage);
    }
}
