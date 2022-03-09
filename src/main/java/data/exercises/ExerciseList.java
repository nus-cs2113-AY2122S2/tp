package data.exercises;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ExerciseList {
    private static final String EXERCISE_FILE_PATH = "exercises/Exercises.txt";
    private static final File EXERCISE_FILE = new File(EXERCISE_FILE_PATH);
    private final ArrayList<String> exercises = new ArrayList<>();
    private int numberOfExercises = 0;

    /**
     * Adds a new exercise into the exercises ArrayList and updates
     * the relevant variables on the change.
     *
     * @param exerciseName The name of the exercise to be added to the list.
     */
    public void addExerciseToList(String exerciseName) {
        exercises.add(exerciseName);
        incrementNumberOfExercises();
    }

    /**
     * Prints the list of exercises starting with their index.
     */
    protected void printExerciseList() {
        for (int i = 0; i < numberOfExercises; i++) {
            System.out.println((i + 1) + ". " + exercises.get(i));
        }
    }

    /**
     * Returns the exercise at given index.
     * @param index index of exercise.
     * @return the exercise at given index.
     * @throws IndexOutOfBoundsException when the index provided is less than 0 or greater than the numberOfExercises.
     */
    protected String getExerciseAt(int index) throws IndexOutOfBoundsException {
        return exercises.get(index - 1);
    }

    /**
     * Returns the total number of exercises that is loaded.
     * @return number of exercises.
     */
    protected int getNumberOfExercises() {
        return numberOfExercises;
    }

    /**
     * Increments the number of exercises by 1.
     */
    protected void incrementNumberOfExercises() {
        this.numberOfExercises += 1;
    }

    /**
     * Checks if the specified exercise name exists in the exercises ArrayList.
     *
     * @param exerciseName The name of the exercise that will be checked for its existence in the list.
     * @return Returns true if the exercise name exists in the list. Otherwise, false is returned.
     */
    public boolean checkIfExerciseExists(String exerciseName) {
        if (!exercises.contains(exerciseName)) {
            return false;
        }

        return true;
    }
}
