package data.exercises;

import textcolors.TextColor;
import werkit.UI;

import java.util.ArrayList;

public class ExerciseList {
    private final ArrayList<String> exercisesList = new ArrayList<>();
    private int numberOfExercises = 0;
    private UI ui = new UI();

    /**
     * Adds a new exercise into the exercises ArrayList and updates
     * the relevant variables on the change.
     *
     * @param exerciseName The name of the exercise to be added to the list.
     */
    public void addExerciseToList(String exerciseName) {
        exercisesList.add(exerciseName);
        incrementNumberOfExercises();
    }

    public ArrayList<String> getExerciseList() {
        return exercisesList;
    }
    /**
     * Prints the list of exercises starting with their index.
     */
    public void printExerciseList() {
        assert getNumberOfExercises() == exercisesList.size();
        System.out.println("There are " + getNumberOfExercises() + " exercises available.");
        System.out.println("Here is the list of exercises: ");
        ui.printLine();
        for (int i = 0; i < numberOfExercises; i++) {
            ui.printColorText(TextColor.COLOR_YELLOW, (i + 1) + ". " + exercisesList.get(i));
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
        assert (0 < index)  && (index <= numberOfExercises);
        return exercisesList.get(index - 1);
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
        if (!exercisesList.contains(exerciseName)) {
            return false;
        }

        return true;
    }

    /**
     * This method provides a default list of default exercises to populate into the exercises.txt file.
     * The method is coded this way in order to solve the following checkstyle issue:
     * 'array initialization' child has incorrect indentation level 16, expected level should be 12.
     *
     * @return An ArrayList of default exercises.
     */
    public static ArrayList<String> getDefaultExerciseList() {
        ArrayList<String> defaultExerciseList = new ArrayList<String>();
        defaultExerciseList.add("push up");
        defaultExerciseList.add("bicep curl");
        defaultExerciseList.add("pull up");
        defaultExerciseList.add("squat");
        defaultExerciseList.add("lunge");
        defaultExerciseList.add("hip thrust");
        defaultExerciseList.add("sit up");
        defaultExerciseList.add("crunch");
        defaultExerciseList.add("russian twist");
        defaultExerciseList.add("running");
        defaultExerciseList.add("swimming");
        defaultExerciseList.add("jumping jack");
        defaultExerciseList.add("burpee");

        return defaultExerciseList;
    }
}
