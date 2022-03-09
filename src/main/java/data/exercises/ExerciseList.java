package data.exercises;

import werkIt.UI;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static textcolors.TextColor.COLOR_YELLOW;

public class ExerciseList {
    private static final String EXERCISE_FILE_PATH = "exercises/Exercises.txt";
    private static final File EXERCISE_FILE = new File(EXERCISE_FILE_PATH);
    private final ArrayList<String> exercises = new ArrayList<>();
    private final UI ui = new UI();
    private int numberOfExercises = 0;

    /**
     * Reads the exercises from local file and store them into an ArrayList named exercises.
     * @throws IOException when the local file is missing.
     */
    public void loadExercises() throws IOException {
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
    public void printExerciseList() {
        System.out.println("There are " + getNumberOfExercises() + " exercises available.");
        System.out.println("Here is the list of exercises: ");
        ui.printLine();
        for (int i = 0; i < numberOfExercises; i++) {
            ui.printColorText(COLOR_YELLOW, (i + 1) + ". " + exercises.get(i));
        }
        ui.printLine();
        System.out.println("End of exercise list.");
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
