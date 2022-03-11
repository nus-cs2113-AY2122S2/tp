package commands;

public class InvalidCommandException extends Exception {
    public static final String INVALID_COMMAND_ERROR_MSG = "Uh oh, the command entered is not recognised.";
    public static final String INVALID_ACTION_ERROR_MSG = "Uh oh, an invalid user action was specified.";
    public static final String ILLEGAL_CHARACTER_USED_ERROR_MSG = "Uh oh, an illegal character was found "
            + "in your input.";
    public static final String INVALID_LIST_COMMAND_ERROR_MSG = "Uh oh, the command entered is wrong.\n"
            + "Do you mean 'workout /list?'";
    public static final String INVALID_NEW_WORKOUT_COMMAND_ERROR_MSG = "Uh oh, the command entered is wrong.\n"
            + "Do you mean 'workout /new <exercise name> /reps <number of reps>'?";
    public static final String INVALID_DELETE_WORKOUT_COMMAND_ERROR_MSG = "Uh oh, the command entered is wrong.\n"
            + "Do you mean 'workout /delete <workout number to delete>'?";

    private String throwingClass;

    public InvalidCommandException(String throwingClass, String errorMessage) {
        super(errorMessage);
        this.throwingClass = throwingClass;
    }

    public String getThrowingClass() {
        return this.throwingClass;
    }
}
