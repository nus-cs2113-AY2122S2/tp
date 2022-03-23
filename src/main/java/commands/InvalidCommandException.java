package commands;

/**
 * Thrown to indicate that a command entered by the user is invalid.
 */
public class InvalidCommandException extends Exception {
    public static final String INVALID_COMMAND_ERROR_MSG = "Uh oh, the command entered is not recognised.";
    public static final String INVALID_ACTION_ERROR_MSG = "Uh oh, an invalid user action was specified.";
    public static final String ILLEGAL_CHARACTER_USED_ERROR_MSG = "Uh oh, an illegal character was found "
            + "in your input.";
    public static final String INVALID_WORKOUT_LIST_COMMAND_ERROR_MSG = "Uh oh, the command entered is wrong.\n"
            + "Do you mean 'workout /list?'";
    public static final String INVALID_WORKOUT_LIST_ALL_COMMAND_ERROR_MSG = "Uh oh, the command entered is wrong.\n"
            + "Do you mean 'workout /listall?'";
    public static final String INVALID_EXERCISE_LIST_COMMAND_ERROR_MSG = "Uh oh, the command entered is wrong.\n"
            + "Do you mean 'exercise /list?'";
    public static final String INVALID_NEW_WORKOUT_COMMAND_ERROR_MSG = "Uh oh, the command entered is wrong.\n"
            + "Do you mean 'workout /new <exercise name> /reps <number of reps>'?";
    public static final String INVALID_DELETE_WORKOUT_COMMAND_ERROR_MSG = "Uh oh, the command entered is wrong.\n"
            + "Do you mean 'workout /delete <workout number to delete>'?";
    public static final String INVALID_UPDATE_WORKOUT_COMMAND_ERROR_MSG = "Uh oh, the command entered is wrong.\n"
            + "Do you mean 'workout /update <workout number to update> <new number of reps>'?";
    public static final String INVALID_SEARCH_EXERCISE_COMMAND_ERROR_MSG = "Uh oh, the command entered is wrong.\n"
            + "Do you mean 'search /exercise <exercise keyword>'?";
    public static final String INVALID_NEW_PLAN_COMMAND_ERROR_MSG = "Uh oh, the command entered is wrong.\n"
            + "Do you mean 'plan /new <plan name> /workouts <workout number(s) to add, separated by comma>'?";
    public static final String INVALID_PLAN_LIST_COMMAND_ERROR_MSG = "Uh oh, the command entered is wrong.\n"
            + "Do you mean 'plan /list?'";
    public static final String INVALID_DELETE_PLAN_COMMAND_ERROR_MSG = "Uh oh, the command entered is wrong.\n"
            + "Do you mean 'plan /delete <plan number to delete>'?";
    public static final String INVALID_SCHEDULE_UPDATE_COMMAND_ERROR_MSG = "Uh oh, the command entered is wrong.\n"
            + "Do you mean 'schedule /update <day number [1-7]> <plan number>'";
    public static final String INVALID_SCHEDULE_LIST_COMMAND_ERROR_MSG = "Uh oh, the command entered is wrong.\n"
            + "Do you mean 'schedule /list?'";
    public static final String INVALID_SCHEDULE_CLEAR_ALL_COMMAND_ERROR_MSG = "Uh oh, the command entered is wrong.\n"
            + "Do you mean 'schedule /clearall?'";
    public static final String INVALID_SCHEDULE_CLEAR_COMMAND_ERROR_MSG = "Uh oh, the command entered is wrong.\n"
            + "Do you mean 'schedule /clear <day number [1-7]>?'";

    private String throwingClass;

    /**
     * Constructs an InvalidCommandException with the class that threw it and the error message.
     *
     * @param throwingClass The class that threw this InvalidCommandException.
     * @param errorMessage  The accompanying error message.
     */
    public InvalidCommandException(String throwingClass, String errorMessage) {
        super(errorMessage);
        this.throwingClass = throwingClass;
    }

    /**
     * Gets the class that threw this InvalidCommandException.
     *
     * @return A string containing the name of the class that threw this exception.
     */
    public String getThrowingClass() {
        return this.throwingClass;
    }
}
