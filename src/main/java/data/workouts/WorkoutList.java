package data.workouts;

import data.exercises.ExerciseList;
import data.exercises.InvalidExerciseException;

import java.util.ArrayList;
import java.util.Scanner;

public class WorkoutList {
    ExerciseList exerciseList;
    ArrayList<Workout> workoutsList = new ArrayList<>();
    public static final int MAX_DISPLAY = 10;

    public WorkoutList(ExerciseList exerciseList) {
        this.exerciseList = exerciseList;
    }

    public Workout createAndAddWorkout(String userArgument) throws ArrayIndexOutOfBoundsException,
            NumberFormatException, InvalidExerciseException, InvalidWorkoutException {
        String userExerciseInput = userArgument.split("/reps")[0].trim();
        String userRepsInputString = userArgument.split("/reps")[1].trim();
        int userRepsInput = Integer.parseInt(userRepsInputString);

        boolean isExerciseValid = exerciseList.checkIfExerciseExists(userExerciseInput);
        boolean isRepsValueValid = checkIfRepsValueIsValid(userRepsInput);

        String className = this.getClass().getSimpleName();
        if (!isExerciseValid) {
            throw new InvalidExerciseException(className, InvalidExerciseException.INVALID_EXERCISE_NAME_ERROR_MSG);
        }

        if (!isRepsValueValid) {
            throw new InvalidWorkoutException(className, InvalidWorkoutException.INVALID_REPS_VALUE);
        }

        Workout newWorkout = new Workout(userExerciseInput, userRepsInput);

        workoutsList.add(newWorkout);

        return newWorkout;
    }

    public boolean checkIfRepsValueIsValid(int userRepsInput) {
        return userRepsInput >= 1;
    }

    /**
     * Prints all workouts stored in workout list. Display 10 workouts at a time and
     * prompt the users if they want to view more workouts.
     */
    public void listWorkout() {
        int index = 0;
        int totalPrints = workoutsList.size();
        String input = "";

        while (totalPrints > MAX_DISPLAY) {
            index = continuousPrinting(index, 10);
            totalPrints -= 10;
            while (!isInputYesOrNo(input)) {
                System.out.println("Do you want to view more workouts? [yes/no]");
                Scanner in = new Scanner(System.in);
                input = in.nextLine();
            }
            if (!input.equals("yes")) {
                return;
            }
        }

        if (totalPrints > 0) {
            index = continuousPrinting(index, totalPrints);
            System.out.println("Showed all items in list");
            return;
        } else {
            System.out.println("The workout list is empty");
        }
    }

    /**
     * Prints workouts stored in workout list.
     */
    public int continuousPrinting(int index, int noOfPrints) {
        System.out.println("Showing workouts " + (index + 1) + "-" + (index + noOfPrints) + " of " + workoutsList.size() + ":");
        for (int i = 0; i < noOfPrints; i++) {
            System.out.println(index + 1 + ". " + workoutsList.get(index));
            index += 1;
        }
        return index;
    }

    /**
     * Check if the user input is "yes" or "no".
     *
     * @param answer input by user.
     * @return true if input equals to "yes", else otherwise.
     */
    public boolean isInputYesOrNo (String answer) {
        if (answer.equals("no") || answer.equals("yes")){
            return true;
        }
        return false;
    }

    public Workout deleteWorkout(String userArgument) throws WorkoutOutOfRangeException,
            NumberFormatException, ArrayIndexOutOfBoundsException {
        int indexToDelete = Integer.parseInt(userArgument.trim());

        String className = this.getClass().getSimpleName();
        boolean isIndexToDeleteValid = checkDeleteIndexIsWithinRange(indexToDelete);
        if (!isIndexToDeleteValid) {
            throw new WorkoutOutOfRangeException(className, WorkoutOutOfRangeException.INDEX_VALUE_OUT_OF_RANGE);
        }

        Workout deletedWorkout = workoutsList.get(indexToDelete - 1);
        workoutsList.remove(indexToDelete - 1);
        return deletedWorkout;
    }

    public boolean checkDeleteIndexIsWithinRange(int indexToDelete) {
        return indexToDelete > 0 && indexToDelete <= workoutsList.size();
    }
}
