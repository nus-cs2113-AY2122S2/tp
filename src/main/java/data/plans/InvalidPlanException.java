package data.plans;

/**
 * Thrown to indicate that the Plan being created has some invalid parameters.
 */
public class InvalidPlanException extends Exception {
    // Pre-defined error messages
    public static final String DUPLICATE_PLAN_NAME_ERROR_MSG = "Uh oh, the plan name already exists.";
    public static final String UNKNOWN_PLAN_NAME_ERROR_MSG = "Uh oh, the plan name given does not exist.";
    public static final String WORKOUT_NUMBER_OUT_OF_RANGE = "Uh oh, the index specified is out of range.\n"
            + "(Index specified needs to be within the number of workouts)";
    public static final String MIN_MAX_WORKOUTS_IN_A_PLAN = "Uh oh, number of workouts specified is out of range.\n"
            + "(A plan should minimally have 1 workout and 10 workouts at most.)";
    public static final String PLAN_INDEX_OUT_OF_RANGE = "Uh oh, the index specified is out of range.\n"
            + "(Index specified needs to be within the number of plans)";
    public static final String PLAN_SAME_WORKOUT_SEQUENCE = "Uh oh, an existing plan with the same\n"
            + "workout sequence already exists.";
    public static final String PLAN_NAME_EXCEED_LIMIT = "Uh oh, the plan name exceeds the character limit (30).";
    public static final String PLAN_NAME_INVALID = "Uh oh, the plan name is invalid.";
    public static final String PLAN_NAME_RESERVED = "Uh oh, this plan name is reserved for use.";

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

