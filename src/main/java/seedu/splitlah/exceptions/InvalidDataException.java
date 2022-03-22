package seedu.splitlah.exceptions;

/**
 * Represents an exception that is thrown when data-related errors (such as unexpected null objects) occur.
 *
 * @author Saurav
 */
public class InvalidDataException extends Exception {
    
    private final String message;
    private final Throwable cause;

    /**
     * Initializes a InvalidDataException object.
     * Use this to store another exception (such as a Java exception) inside this exception to access it later.
     *
     * @param message A String object containing a description of the error.
     * @param cause   A Throwable object to be stored in this exception.
     */
    public InvalidDataException(String message, Throwable cause) {
        this.message = message;
        this.cause = cause;
    }

    /**
     * Constructor that includes a message only.
     * Use this when no exception needs to be stored inside this one.
     *
     * @param message A String object containing a description of the error.
     */
    public InvalidDataException(String message) {
        this.message = message;
        this.cause = null;
    }

    /**
     * Default constructor. No message or exception will be stored.
     */
    public InvalidDataException() {
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