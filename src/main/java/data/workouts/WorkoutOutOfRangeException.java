package data.workouts;

/**
 * Thrown to indicate that the index specified for operations such as
 * update and delete is not within the range of the workout list.
 */
public class WorkoutOutOfRangeException extends Exception {
    // Pre-defined error messages
    public static final String INDEX_VALUE_OUT_OF_RANGE = "Uh oh, the index specified is out of range.\n"
            + "(Index specified needs to be within the number of workouts)";

    private String throwingClass;

    /**
     * Constructs WorkoutOutOfRangeException with the class name that this exception instance was
     * thrown from and the accompanying error message.
     *
     * @param throwingClass The class name that this exception instance was thrown from.
     * @param errorMessage The error message given by the throwing method.
     */
    public WorkoutOutOfRangeException(String throwingClass, String errorMessage) {
        super(errorMessage);
        this.throwingClass = throwingClass;
    }
}
