package seedu.splitlah.exceptions;

/**
 * Represents an exception that is thrown when format-related errors (such as invalid input) occur.
 *
 * @author Saurav
 */
public class InvalidFormatException extends Exception {
    
    private final String message;
    private final Throwable cause;

    /**
     * Constructor that includes a message and an exception.
     * Use this to store another exception (such as a Java exception) inside this exception to access it later.
     *
     * @param message A String object containing a description of the error.
     * @param cause   A Throwable object to be stored in this exception.
     */
    public InvalidFormatException(String message, Throwable cause) {
        this.message = message;
        this.cause = cause;
    }

    /**
     * Constructor that includes a message only.
     * Use this when no exception needs to be stored inside this one.
     *
     * @param message A String object containing a description of the error.
     */
    public InvalidFormatException(String message) {
        this.message = message;
        this.cause = null;
    }

    /**
     * Default constructor. No message or exception will be stored.
     */
    public InvalidFormatException() {
        this.message = null;
        this.cause = null;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getCause() {
        return cause;
    }
}
