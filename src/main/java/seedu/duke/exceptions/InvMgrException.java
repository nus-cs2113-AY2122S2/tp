package seedu.duke.exceptions;

public class InvMgrException extends Exception {

    public InvMgrException(String message) {
        super(message);
    }

    public InvMgrException(String message, Throwable cause) {
        super(message, cause);
    }
}
