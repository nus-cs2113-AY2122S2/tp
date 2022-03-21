package data.schedule;

public class InvalidScheduleException extends Exception {
    // Pre-defined error messages

    public static final String DAY_NUMBER_OUT_OF_RANGE = "Uh oh, the day number specified is invalid.\n"
            + "Please make sure it is between 1-7.";
    public static final String INVALID_PLAN = "Uh oh, the plan number entered is invalid.\n"
            + "Please specify the plan again.";
    public static final String INVALID_DAY = "Uh oh, the day number entered is invalid.\n";

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
