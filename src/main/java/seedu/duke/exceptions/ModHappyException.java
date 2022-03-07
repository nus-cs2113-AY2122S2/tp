package seedu.duke.exceptions;

public class ModHappyException extends Exception {

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
