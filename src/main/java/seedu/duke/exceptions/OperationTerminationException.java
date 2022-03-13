package seedu.duke.exceptions;

/**
 * Exception to signal that an operation is to be terminated.
 */
public final class OperationTerminationException extends Exception {
    public OperationTerminationException(String msg) {
        super(msg);
    }

    public OperationTerminationException() {
        super();
    }
}
