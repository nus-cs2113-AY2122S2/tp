package data.workouts;

import commands.WorkoutCommand;
import data.exercises.ExerciseList;
import data.exercises.InvalidExerciseException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

public class WorkoutList {
    public static final int MAX_DISPLAY = 10;

    private ExerciseList exerciseList;
    private ArrayList<Workout> workoutsList = new ArrayList<>();
    private static Logger logger = Logger.getLogger(WorkoutList.class.getName());

    public WorkoutList(ExerciseList exerciseList) {
        this.exerciseList = exerciseList;
    }

    public ArrayList<Workout> getWorkoutsList() {
        return this.workoutsList;
    }

    public Workout createAndAddWorkout(String userArgument) throws ArrayIndexOutOfBoundsException,
            NumberFormatException, InvalidExerciseException, InvalidWorkoutException {
        String userExerciseInput = userArgument.split(WorkoutCommand.CREATE_ACTION_REPS_KEYWORD)[0].trim();
        String userRepsInputString = userArgument.split(WorkoutCommand.CREATE_ACTION_REPS_KEYWORD)[1].trim();
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
        logger.entering(getClass().getName(), "listWorkout");
        int index = 0;
        int totalPrints = workoutsList.size();
        String input = "";

        while (totalPrints > MAX_DISPLAY) {
            index = continuousPrinting(index, 10);
            assert (index <= workoutsList.size());
            totalPrints -= 10;
            while (!isInputYesOrNo(input)) {
                System.out.println("Do you want to view more workouts? [yes/no]");
                Scanner in = new Scanner(System.in);
                input = in.nextLine().toLowerCase();
            }
            if (!input.equals("yes")) {
                return;
            }
            input = "";
        }

        if (totalPrints > 0) {
            continuousPrinting(index, totalPrints);
            System.out.println("Showed all items in list");
            return;
        } else {
            assert (workoutsList.size() == 0);
            System.out.println("The workout list is empty");
        }
        logger.exiting(getClass().getName(), "listWorkout");
    }

    /**
     * Prints workouts stored in workout list.
     */
    public int continuousPrinting(int index, int noOfPrints) {
        System.out.println("Showing workouts " + (index + 1) + "-" + (index + noOfPrints)
                + " of " + workoutsList.size() + ":");
        assert (noOfPrints <= workoutsList.size());
        for (int i = 0; i < noOfPrints; i++) {
            System.out.println(index + 1 + ". " + workoutsList.get(index));
            index += 1;
        }
        return index;
    }

    /**
     * Checks if the user input is "yes" or "no".
     *
     * @param answer input by user.
     * @return true if input equals to "yes" or "no", else otherwise.
     */
    public boolean isInputYesOrNo(String answer) {
        if (answer.equals("no") || answer.equals("yes")) {
            return true;
        }
        return false;
    }

    /**
     * This method removes the intended workout in the workout list.
     * The intended workout to delete is determined by the user who
     * will indicate the workout number to delete in the workout list.
     *
     * @param userArgument The argument entered by user, that is, the workout number to delete.
     * @return deletedWorkout, the workout object that is deleted from the workoutsList.
     * @throws WorkoutOutOfRangeException If workout number to delete is out of range.
     * @throws NumberFormatException If workout number could not be parsed into an integer.
     * @throws ArrayIndexOutOfBoundsException For operations which involves index checking.
     */
    public Workout deleteWorkout(String userArgument) throws WorkoutOutOfRangeException,
            NumberFormatException, ArrayIndexOutOfBoundsException {
        logger.entering(getClass().getName(), "deleteWorkout");
        int indexToDelete = Integer.parseInt(userArgument.trim());

        String className = this.getClass().getSimpleName();
        boolean isIndexToDeleteValid = checkIndexIsWithinRange(indexToDelete);
        if (!isIndexToDeleteValid) {
            logger.log(Level.WARNING, "Workout number to delete is out of range!");
            throw new WorkoutOutOfRangeException(className, WorkoutOutOfRangeException.INDEX_VALUE_OUT_OF_RANGE);
        }

        assert (indexToDelete > 0) && (indexToDelete <= workoutsList.size());
        Workout deletedWorkout = workoutsList.get(indexToDelete - 1);
        workoutsList.remove(indexToDelete - 1);
        logger.exiting(getClass().getName(), "deleteWorkout");
        return deletedWorkout;
    }

    /**
     * This method checks whether the index supplied for operations
     * such as delete and update is within the range of the current workout list.
     *
     * @param index The index to check.
     * @return true if index is within range, else false if out of range.
     */
    private boolean checkIndexIsWithinRange(int index) {
        return index > 0 && index <= workoutsList.size();
    }

    public Workout updateWorkout(String userArgument) throws ArrayIndexOutOfBoundsException,
            NumberFormatException, WorkoutOutOfRangeException, InvalidWorkoutException {
        String[] updateDetails = userArgument.split(" ", 2);
        String indexToUpdateString = updateDetails[0].trim();
        String newNumberOfRepsString = updateDetails[1].trim();
        int indexToUpdate = Integer.parseInt(indexToUpdateString);
        int newNumberOfReps = Integer.parseInt(newNumberOfRepsString);

        boolean isIndexToUpdateValid = checkIndexIsWithinRange(indexToUpdate);
        boolean isNewNumberOfRepsValid = checkIfRepsValueIsValid(newNumberOfReps);
        String className = this.getClass().getSimpleName();

        if (!isIndexToUpdateValid) {
            throw new WorkoutOutOfRangeException(className, WorkoutOutOfRangeException.INDEX_VALUE_OUT_OF_RANGE);
        }
        if (!isNewNumberOfRepsValid) {
            throw new InvalidWorkoutException(className, InvalidWorkoutException.INVALID_REPS_VALUE);
        }

        Workout updateWorkout = workoutsList.get(indexToUpdate - 1);
        updateWorkout.setRepetitions(newNumberOfReps);
        return updateWorkout;
    }
}
