package data.schedule;

public class InvalidScheduleException extends Exception {
    // Pre-defined error messages

    public static final String DAY_NUMBER_OUT_OF_RANGE = "Uh oh, the day number specified is invalid.\n"
            + "Please make sure it is between 1-7.";
    public static final String INVALID_PLAN = "Uh oh, the plan number entered is invalid.\n"
            + "Please specify a valid plan number.";
    public static final String INVALID_DAY = "Uh oh, the day number entered is invalid.\n";
    public static final String INPUT_NOT_NUMBER_FORMATTABLE = "Uh oh, a number was expected in your input, "
            + "but a non-formattable number was received.\n"
            + "Please try again. Alternatively, type 'help' if you need\n"
            + "more information on the commands.";
    public static final String INDEX_OUT_OF_BOUND = "Uh oh, it seems like too few arguments were entered.\n"
            + "Please try again. Alternatively, type 'help' if you need\n"
            + "more information on the commands.";


    private String throwingClass;

    /**
     * Constructs an InvalidPlanException with the class name that this exception instance was
     * thrown from and the accompanying error message.
     *
     * @param throwingClass The class name that this exception instance was thrown from.
     * @param errorMessage  The error message given by the throwing method.
     */
    public InvalidScheduleException(String throwingClass, String errorMessage) {
        super(errorMessage);
        this.throwingClass = throwingClass;
    }
}
