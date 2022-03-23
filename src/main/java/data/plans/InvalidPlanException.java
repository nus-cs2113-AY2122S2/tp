package data.plans;

public class InvalidPlanException extends Exception {
    // Pre-defined error messages
    public static final String DUPLICATE_PLAN_NAME_ERROR_MSG = "Uh oh, there already is an existing plan with "
            + "an identical\nplan name.";
    public static final String WORKOUT_NUMBER_OUT_OF_RANGE = "Uh oh, the index specified is out of range.\n"
            + "(Index specified needs to be within the number of workouts)";
    public static final String MIN_MAX_WORKOUTS_IN_A_PLAN = "Uh oh, number of workouts specified is out of range.\n"
            + "(A plan should minimally have 1 workout and 10 workouts at most.)";
    public static final String PLAN_INDEX_OUT_OF_RANGE = "Uh oh, the index specified is out of range.\n"
            + "(Index specified needs to be within the number of plans)";

    private String throwingClass;

    /**
     * Constructs an InvalidPlanException with the class name that this exception instance was
     * thrown from and the accompanying error message.
     *
     * @param throwingClass The class name that this exception instance was thrown from.
     * @param errorMessage  The error message given by the throwing method.
     */
    public InvalidPlanException(String throwingClass, String errorMessage) {
        super(errorMessage);
        this.throwingClass = throwingClass;
    }
}

