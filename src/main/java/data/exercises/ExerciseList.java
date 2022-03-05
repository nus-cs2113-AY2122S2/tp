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
     * Reads the exercises from local file and store them into an ArrayList named exercises.
     * @throws IOException when the local file is missing.
     */
    protected void loadExercises() throws IOException {
        Scanner scanner = new Scanner(EXERCISE_FILE);
        while (scanner.hasNextLine()) {
            var exercise = scanner.nextLine();
            exercises.add(exercise);
            numberOfExercises += 1;
        }
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
}
