package seedu.allonus.modules.exceptions;

/**
 * Signifies invalid input for find module feature.
 */
public class InvalidFindInputException extends Exception {
    public InvalidFindInputException(String message) {
        super(message);
    }
}
