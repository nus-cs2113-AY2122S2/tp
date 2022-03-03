package seedu.duke.exceptions;

/**
 * Data-related errors (such as unexpected null objects) should throw
 * this exception.
 * @author Saurav
 */
public class InvalidDataException extends Exception {
    private String message;
    private Throwable cause;

    /**
     * Constructor that includes a message and an exception.
     * Use this to store another exception (such as a Java exception) inside this exception to access it later.
     *
     * @param message a String with a descriptive message.
     * @param cause   a Throwable to be stored in this exception.
     */
    public InvalidDataException(String message, Throwable cause) {
        this.message = message;
        this.cause = cause;
    }
}