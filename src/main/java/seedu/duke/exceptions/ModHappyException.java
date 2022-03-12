package seedu.duke.exceptions;

/**
 * Base class for all program-specific exceptions.
 */
public class ModHappyException extends Exception {
    protected static final String LS = System.lineSeparator();
    protected String errorMessage;

    public ModHappyException(String message) {
        super(message);
        errorMessage = message;
    }

    public ModHappyException() {
    }

    @Override
    public String toString() {
        return errorMessage;
    }

}
