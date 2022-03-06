package commands;

public class InvalidCommandException extends Exception {
    public static final String INVALID_COMMAND_ERROR_MSG = "Uh oh, the command entered is not recognised.";
    public static final String INVALID_ACTION_ERROR_MSG = "Uh oh, an invalid user action was specified.";

    private String throwingClass;

    public InvalidCommandException(String throwingClass, String errorMessage) {
        super(errorMessage);
        this.throwingClass = throwingClass;
    }

    public String getThrowingClass() {
        return this.throwingClass;
    }
}
