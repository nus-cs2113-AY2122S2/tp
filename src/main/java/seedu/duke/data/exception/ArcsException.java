package seedu.duke.data.exception;

/**
 * Throws exceptions relating ARCS application
 */
public class ArcsException extends Exception {
    /**
     * Error Message.
     */
    private static final String ERROR_MESSAGE = "Sorry! ";

    /**
     * Initializes message to throw.
     *
     * @param message Exception message to show.
     */
    public ArcsException(String message) {
        super(ERROR_MESSAGE + message);
    }
}

