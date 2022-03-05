package data.exercises;

public class InvalidExerciseException extends Exception {
    public static final String INVALID_EXERCISE_NAME_ERROR_MSG = "The exercise name given does not exist.";

    private String throwingClass;

    public InvalidExerciseException(String throwingClass, String errorMessage) {
        super(errorMessage);
        this.throwingClass = throwingClass;
    }

    public String getThrowingClass() {
        return this.throwingClass;
    }
}
