package data.workouts;

import commands.WorkoutCommand;
import data.exercises.ExerciseList;
import data.exercises.InvalidExerciseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * This class represents an instance of a list of workouts entered by the user.
 * It contains functionality to validate the user's inputs as well as allow the user to
 * create, modify, and delete workouts.
 */
public class WorkoutList {
    public static final int MAX_DISPLAY = 10;

    private ExerciseList exerciseList;
    private HashMap<String, Workout> workoutsList = new HashMap<>();
    private ArrayList<Workout> workoutsDisplayList = new ArrayList<>();
    private static Logger logger = Logger.getLogger(WorkoutList.class.getName());

    /**
     * Constructs an instance of the WorkoutList class.
     *
     * @param exerciseList An instance of the ExerciseList class.
     */
    public WorkoutList(ExerciseList exerciseList) {
        this.exerciseList = exerciseList;
    }

    /**
     * Gets the list of workouts.
     *
     * @return An ArrayList of Workouts.
     */
    public ArrayList<Workout> getWorkoutsDisplayList() {
        return this.workoutsDisplayList;
    }

    /**
     * Parses the given user argument to identify the details of the new workout to be added.
     * Thereafter, the details will be checked for their validity. If all checks pass, a new
     * Workout object is instantiated before adding it to the ArrayList of workouts.
     *
     * @param userArgument The user's details for the new workout, including exercise name
     *                     and number of repetitions.
     * @return A Workout object that represents the new workout.
     * @throws ArrayIndexOutOfBoundsException If userArgument contains insufficient arguments and parsing fails.
     * @throws NumberFormatException          If the number of repetitions specified in userArgument is an
     *                                        invalid number.
     * @throws InvalidExerciseException       If the exercise name specified in userArgument is invalid.
     * @throws InvalidWorkoutException        If the details specified in userArgument is invalid.
     */
    public Workout createAndAddWorkout(String userArgument) throws ArrayIndexOutOfBoundsException,
            NumberFormatException, InvalidExerciseException, InvalidWorkoutException {
        String userExerciseInput = userArgument.split(WorkoutCommand.CREATE_ACTION_REPS_KEYWORD)[0].trim();
        String userRepsInputString = userArgument.split(WorkoutCommand.CREATE_ACTION_REPS_KEYWORD)[1].trim();
        int userRepsInput = Integer.parseInt(userRepsInputString);

        boolean isExerciseValid = exerciseList.checkIfExerciseExists(userExerciseInput);
        boolean isRepsValueValid = checkIfRepsValueIsValid(userRepsInput);

        String className = this.getClass().getSimpleName();
        if (!isExerciseValid) {
            logger.log(Level.WARNING, "Exercise name is invalid.");
            throw new InvalidExerciseException(className, InvalidExerciseException.INVALID_EXERCISE_NAME_ERROR_MSG);
        }

        if (!isRepsValueValid) {
            logger.log(Level.WARNING, "Repetition value is invalid.");
            throw new InvalidWorkoutException(className, InvalidWorkoutException.INVALID_REPS_VALUE_ERROR_MSG);
        }

        boolean hasSameExerciseNameAndReps = checkForExistingWorkout(userExerciseInput, userRepsInput);

        if (hasSameExerciseNameAndReps) {
            logger.log(Level.WARNING, "Existing workout with identical exercise name and repetition "
                    + "value found in the list of workouts.");
            throw new InvalidWorkoutException(className, InvalidWorkoutException.DUPLICATE_WORKOUT_ERROR_MSG);
        }

        assert (isExerciseValid && isRepsValueValid && !hasSameExerciseNameAndReps);

        Workout newWorkout = new Workout(userExerciseInput, userRepsInput);
        logger.log(Level.INFO, "New workout created.");

        workoutsDisplayList.add(newWorkout);

        return newWorkout;
    }

    /**
     * Checks if the number of repetitions specified is a value that is at least 1.
     * Workouts must be an exercise with a minimum repetition value of 1.
     *
     * @param userRepsInput The repetition value to check.
     * @return True if the repetition value is at least 1. Otherwise, returns false.
     */
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
        int totalPrints = workoutsDisplayList.size();
        String input = "";

        while (totalPrints > MAX_DISPLAY) {
            index = continuousPrinting(index, 10);
            assert (index <= workoutsDisplayList.size());
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
            System.out.println("Showed all workouts in list");
            return;
        } else {
            assert (workoutsDisplayList.size() == 0);
            System.out.println("The workout list is empty");
        }
        logger.exiting(getClass().getName(), "listWorkout");
    }

    /**
     * Prints workouts stored in workout list.
     */
    public int continuousPrinting(int index, int noOfPrints) {
        System.out.println("Showing workouts " + (index + 1) + "-" + (index + noOfPrints)
                + " of " + workoutsDisplayList.size() + ":");
        assert (noOfPrints <= workoutsDisplayList.size());
        for (int i = 0; i < noOfPrints; i++) {
            System.out.println(index + 1 + ". " + workoutsDisplayList.get(index));
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
        if (answer.equals("")) {
            return false;
        }
        if (answer.equals("no") || answer.equals("yes")) {
            return true;
        }
        logger.log(Level.WARNING, "User did not entered the command that was expected.");
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

        assert (indexToDelete > 0) && (indexToDelete <= workoutsDisplayList.size());
        Workout deletedWorkout = workoutsDisplayList.get(indexToDelete - 1);
        workoutsDisplayList.remove(indexToDelete - 1);
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
        return index > 0 && index <= workoutsDisplayList.size();
    }

    /**
     * Updates the number of repetitions of an existing workout.
     * Modifies the workout by stating the index of the workout and
     * new number of repetitions.
     *
     * @param userArgument The argument entered by user, which includes index of workout to update
     *                     and new number of repetitions.
     * @return updatedWorkout The workout object which has been updated.
     * @throws ArrayIndexOutOfBoundsException For operations which involves index checking.
     * @throws NumberFormatException          If workout index and number of reps could not be parsed into an integer.
     * @throws WorkoutOutOfRangeException     If workout index is out of total number of workout in workoutsList.
     * @throws InvalidWorkoutException        If the value of repetition is not valid.
     */
    public Workout updateWorkout(String userArgument) throws ArrayIndexOutOfBoundsException,
            NumberFormatException, WorkoutOutOfRangeException, InvalidWorkoutException {
        logger.entering(getClass().getName(), "updateWorkout");
        String[] updateDetails = userArgument.split(" ", 2);
        String indexToUpdateString = updateDetails[0].trim();
        String newNumberOfRepsString = updateDetails[1].trim();
        int indexToUpdate = Integer.parseInt(indexToUpdateString);
        int newRepsValue = Integer.parseInt(newNumberOfRepsString);

        boolean isIndexToUpdateValid = checkIndexIsWithinRange(indexToUpdate);
        boolean isNewRepsValueValid = checkIfRepsValueIsValid(newRepsValue);
        String className = this.getClass().getSimpleName();

        if (!isIndexToUpdateValid) {
            logger.log(Level.WARNING, "Workout index to update is out of range!");
            throw new WorkoutOutOfRangeException(className, WorkoutOutOfRangeException.INDEX_VALUE_OUT_OF_RANGE);
        }
        if (!isNewRepsValueValid) {
            logger.log(Level.WARNING, "Repetition value is invalid.");
            throw new InvalidWorkoutException(className, InvalidWorkoutException.INVALID_REPS_VALUE_ERROR_MSG);
        }

        Workout updatedWorkout = workoutsDisplayList.get(indexToUpdate - 1);
        String exerciseName = updatedWorkout.getExerciseName();
        boolean isExistingWorkout = checkForExistingWorkout(exerciseName, newRepsValue);

        if (isExistingWorkout) {
            logger.log(Level.WARNING, "\"" + exerciseName + " (" + newRepsValue + ")\" "
                + "has already existed in the list. Cannot be updated.");
            throw new InvalidWorkoutException(className, InvalidWorkoutException.DUPLICATE_WORKOUT_ERROR_MSG);
        }

        assert (isIndexToUpdateValid && isNewRepsValueValid && !isExistingWorkout);
        updatedWorkout.setRepetitions(newRepsValue);
        logger.exiting(getClass().getName(), "updateWorkout");
        return updatedWorkout;
    }

    /**
     * Checks if the provided workout details already exists in the ArrayList of workouts. A workout
     * is considered to already exist in the list if both the exercise name and repetition count matches
     * an existing workout in the ArrayList.
     *
     * @param exerciseName    The name of the exercise to check.
     * @param repetitionCount The number of repetitions of the exercise to check.
     * @return True if an existing workout with the same exercise name and repetition count exists in the list.
     *         Otherwise, returns false.
     */
    public boolean checkForExistingWorkout(String exerciseName, int repetitionCount) {
        for (Workout existingWorkout : getWorkoutsDisplayList()) {
            boolean hasSameExerciseName = existingWorkout.getExerciseName().equals(exerciseName);
            boolean hasSameRepsCount = (existingWorkout.getRepetitions() == repetitionCount);

            if (hasSameExerciseName && hasSameRepsCount) {
                logger.log(Level.INFO, "Existing workout found in the list.");
                return true;
            }
        }

        return false;
    }
}
