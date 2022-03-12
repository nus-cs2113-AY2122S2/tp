package data.workouts;

/**
 * This class represents a Workout, which is an exercise with a specified number of repetitions.
 */
public class Workout {
    private String exerciseName;
    private int repetitions;

    public Workout(String exerciseName, int repetitions) {
        this.exerciseName = exerciseName;
        this.repetitions = repetitions;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public String toString() {
        String displayString = getExerciseName() + " (" + getRepetitions() + " reps)";
        return displayString;
    }
}
