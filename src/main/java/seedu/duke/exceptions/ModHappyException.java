package seedu.duke.exceptions;

//@@author  Ch40gRv1-Mu
/**
 * Base class for all program-specific exceptions.
 */
public class ModHappyException extends Exception {
    protected static final String LS = System.lineSeparator();
    public String errorMessage;

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
