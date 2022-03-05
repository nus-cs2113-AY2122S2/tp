package data.workouts;

public class InvalidWorkoutsException extends Exception {
    public static final String INVALID_REPS_VALUE = "The number of reps specified is invalid.";

    private String throwingClass;

    public InvalidWorkoutsException(String throwingClass, String errorMessage) {
        super(errorMessage);
        this.throwingClass = throwingClass;
    }
}
