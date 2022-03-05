package data.workouts;

/**
 * Thrown to indicate that the Workout being created has some invalid parameters.
 */
public class InvalidWorkoutException extends Exception {
    // Pre-defined error messages
    public static final String INVALID_REPS_VALUE = "The number of reps specified is invalid.\n" +
            "(Needs to be a value of at least 1.)";

    private String throwingClass;

    /**
     * Constructs an InvalidWorkoutException with the class name that this exception instance was
     * thrown from and the accompanying error message.
     *
     * @param throwingClass The class name that this exception instance was thrown from.
     * @param errorMessage The error message given by the throwing method.
     */
    public InvalidWorkoutException(String throwingClass, String errorMessage) {
        super(errorMessage);
        this.throwingClass = throwingClass;
    }
}
