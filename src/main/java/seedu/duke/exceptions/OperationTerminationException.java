package seedu.duke.exceptions;

/**
 * Exception to signal that an operation is to be terminated. This happens when the user inputs a "-" character.
 */
public final class OperationTerminationException extends Exception {
    public OperationTerminationException() {
        super();
    }
}
