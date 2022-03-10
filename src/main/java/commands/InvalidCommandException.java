package commands;

public class InvalidCommandException extends Exception {
    public static final String INVALID_COMMAND_ERROR_MSG = "Uh oh, the command entered is not recognised.";
    public static final String INVALID_ACTION_ERROR_MSG = "Uh oh, an invalid user action was specified.";
    public static final String ILLEGAL_CHARACTER_USED_ERROR_MSG = "Uh oh, an illegal character was found "
            + "in your input.";
    public static final String INVALID_LIST_COMMAND_ERROR_MSG = "Uh oh, the command entered wrong.\n"
            + "Do you mean workout /list?";

    private String throwingClass;

    public InvalidCommandException(String throwingClass, String errorMessage) {
        super(errorMessage);
        this.throwingClass = throwingClass;
    }

    public String getThrowingClass() {
        return this.throwingClass;
    }
}
