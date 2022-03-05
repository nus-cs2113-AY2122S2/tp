package data.workouts;

/**
 * This class represents a Workout, which is an exercise with a specified number of repetitions.
 */
public class Workout {
    private String exercise;
    private int repetitions;

    public Workout(String exerciseName, int repetitions) {
        this.exercise = exerciseName;
        this.repetitions = repetitions;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String string) {
        this.exercise = string;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }
}
