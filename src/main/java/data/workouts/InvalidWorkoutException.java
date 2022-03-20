package data.workouts;

/**
 * Thrown to indicate that the Workout being created has some invalid parameters.
 */
public class InvalidWorkoutException extends Exception {
    // Pre-defined error messages
    public static final String INVALID_REPS_VALUE_ERROR_MSG = "Uh oh, the number of reps specified is invalid.\n"
            + "(Needs to be a value of at least 1.)";
    public static final String DUPLICATE_WORKOUT_ERROR_MSG = "Uh oh, there already is an existing workout with "
            + "an identical\nexercise name and number of repetitions.";
    public static final String INVALID_WORKOUT_ERROR_MSG = "The workout given does not exist.";

    private String throwingClass;

    /**
     * Constructs an InvalidWorkoutException with the class name that this exception instance was
     * thrown from and the accompanying error message.
     *
     * @param throwingClass The class name that this exception instance was thrown from.
     * @param errorMessage  The error message given by the throwing method.
     */
    public InvalidWorkoutException(String throwingClass, String errorMessage) {
        super(errorMessage);
        this.throwingClass = throwingClass;
    }
}
