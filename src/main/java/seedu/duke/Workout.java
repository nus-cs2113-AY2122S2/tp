package seedu.duke;

/**
 * This class represents a Workout, which is an exercise with a specified number of repetitions.
 */
public class Workout {
    private Exercise exercise;
    private int repetitions;

    // Accessor methods
    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }
}
