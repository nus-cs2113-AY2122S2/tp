package data.workouts;

import commands.WorkoutCommand;
import data.exercises.ExerciseList;
import data.exercises.InvalidExerciseException;
import data.plans.InvalidPlanException;
import storage.LogHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * This class represents an instance of a list of workouts entered by the user.
 * It contains functionality to validate the user's inputs as well as allow the user to
 * create, modify, and delete workouts.
 */
public class WorkoutList {
    private ExerciseList exerciseList;
    private HashMap<String, Workout> workoutsHashMapList = new HashMap<>();
    private ArrayList<String> workoutsDisplayList = new ArrayList<>();
    private static Logger logger = Logger.getLogger(WorkoutList.class.getName());

    /**
     * Constructs an instance of the WorkoutList class.
     *
     * @param exerciseList An instance of the ExerciseList class.
     */
    public WorkoutList(ExerciseList exerciseList) {
        this.exerciseList = exerciseList;
        LogHandler.linkToFileLogger(logger);
    }

    /**
     * Gets the ArrayList of keys of Workout objects.
     *
     * @return An ArrayList of keys of Workout objects.
     */
    public ArrayList<String> getWorkoutsDisplayList() {
        return this.workoutsDisplayList;
    }

    /**
     * Gets the HashMap of Workout objects.
     *
     * @return A HashMap of Workout objects.
     */
    public HashMap<String, Workout> getWorkoutsHashMapList() {
        return this.workoutsHashMapList;
    }

    /**
     * Gets the Workout object based on its key as stored in the workoutsHashMapList.
     *
     * @param workoutKey The key that maps to the desired Workout object.
     * @return The Workout object that is mapped to workoutKey.
     */
    public Workout getWorkoutFromKey(String workoutKey) {
        return getWorkoutsHashMapList().get(workoutKey);
    }

    /**
     * Retrieves the Workout object from the HashMap workoutsHashMapList based on the index number
     * of the object stored in workoutsDisplayList. This index number is the number shown in
     * 'workout /list'.
     *
     * @param indexNum The index number of the workout as shown in 'workout /list'.
     * @return The Workout object that corresponds to the index number.
     */
    public Workout getWorkoutFromIndexNum(int indexNum) {
        int elementNum = indexNum - 1;
        assert (elementNum >= 0);
        String keyValue = getWorkoutsDisplayList().get(elementNum);
        Workout workoutObject = workoutsHashMapList.get(keyValue);
        return workoutObject;
    }

    /**
     * Retrieves the index number of a workout based on its position in the workoutsDisplayList
     * ArrayList.
     *
     * @param workoutName The name of the workout whose index number this method has to find.
     * @return An integer representing the index number the workout is listed in the workoutDisplayList
     *         ArrayList.
     * @throws InvalidPlanException If the given workout name was not found in workoutsDisplayList.
     */
    public int getIndexNumFromWorkoutName(String workoutName) throws InvalidWorkoutException {
        for (int i = 0; i < getWorkoutsDisplayList().size(); i += 1) {
            if (getWorkoutsDisplayList().get(i).equalsIgnoreCase(workoutName)) {
                return (i + 1);
            }
        }

        String className = this.getClass().getSimpleName();
        throw new InvalidWorkoutException(className, InvalidWorkoutException.INVALID_WORKOUT_ERROR_MSG);
    }

    /**
     * Updates an existing Workout object in workoutsHashMapList's with a new key. This should be called when
     * a workout's repetition count has been updated. Once the Workout object has been reassigned to the new
     * key, the former key will be removed. The ArrayList workoutsDisplayList will also be updated with the
     * new key.
     *
     * @param existingKey    The existing key that maps to the updated Workout object.
     * @param updatedWorkout The Workout object that has been updated.
     */
    public void updateWorkoutsHashMapList(String existingKey, Workout updatedWorkout) {
        // Update key-value in workoutHashMapList
        String newKey = updatedWorkout.toString();
        getWorkoutsHashMapList().put(newKey, updatedWorkout);
        getWorkoutsHashMapList().remove(existingKey);

        // Update key in workoutsDisplayList
        for (int i = 0; i < getWorkoutsDisplayList().size(); i += 1) {
            String key = getWorkoutsDisplayList().get(i);
            if (key.equals(existingKey)) {
                getWorkoutsDisplayList().set(i, newKey);
                break;
            }
        }

        assert (!getWorkoutsDisplayList().contains(existingKey)) : "Old key should no longer "
                + "exist in workoutsDisplayList.";
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
        String userExerciseInput = userArgument.split(WorkoutCommand.ACTION_KEYWORD_CREATE_REPS)[0].trim();
        String userRepsInputString = userArgument.split(WorkoutCommand.ACTION_KEYWORD_CREATE_REPS)[1].trim();
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

        String newWorkoutKey = newWorkout.toString();
        workoutsHashMapList.put(newWorkoutKey, newWorkout);

        workoutsDisplayList.add(newWorkoutKey);

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
     * Prints all workouts stored in workout list at once.
     */
    public void listAllWorkout() {
        int index = 1;
        if (workoutsDisplayList.size() <= 0) {
            System.out.println("The workout list is empty");
        } else {
            System.out.println("Showing workouts " + (index) + "-" + workoutsDisplayList.size()
                    + " of " + workoutsDisplayList.size() + ":");
            while (index <= workoutsDisplayList.size()) {
                Workout workoutObject = getWorkoutFromIndexNum(index);
                System.out.println(index + ". " + workoutObject.toString());
                index += 1;
            }
            System.out.println("Showed all workouts in list");
        }
    }

    //@@author Musfirahe0556596
    /**
     * This method removes the intended workout in the workout list.
     * The intended workout to delete is determined by the user who
     * will indicate the workout index number to delete in the workout list.
     *
     * @param userArgument The argument entered by user, that is, the workout index number to delete.
     * @throws NumberFormatException If workout index number could not be parsed into an integer.
     * @throws ArrayIndexOutOfBoundsException For operations which involves index checking.
     * @throws InvalidWorkoutException        If workout index number to delete is out of range.
     */
    public Workout deleteWorkout(String userArgument) throws InvalidWorkoutException,
            NumberFormatException, ArrayIndexOutOfBoundsException {
        logger.entering(getClass().getName(), "deleteWorkout");
        int indexToDelete = Integer.parseInt(userArgument.trim());

        String className = this.getClass().getSimpleName();
        boolean isIndexToDeleteValid = checkIndexIsWithinRange(indexToDelete);
        if (!isIndexToDeleteValid) {
            logger.log(Level.WARNING, "Workout number to delete is out of range!");
            throw new InvalidWorkoutException(className, InvalidWorkoutException.INDEX_VALUE_OUT_OF_RANGE);
        }

        assert (indexToDelete > 0) && (indexToDelete <= workoutsDisplayList.size());
        Workout deletedWorkout = getWorkoutFromIndexNum(indexToDelete);
        workoutsDisplayList.remove(indexToDelete - 1);
        String deletedWorkoutKey = deletedWorkout.toString();
        getWorkoutsHashMapList().remove(deletedWorkoutKey);

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
    //@@author

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
     * @throws InvalidWorkoutException        If number of repetition is not valid or workout number to update
     *                                        is out of range.
     */
    public Workout updateWorkout(String userArgument) throws ArrayIndexOutOfBoundsException,
            NumberFormatException, InvalidWorkoutException {
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
            throw new InvalidWorkoutException(className, InvalidWorkoutException.INDEX_VALUE_OUT_OF_RANGE);
        }
        if (!isNewRepsValueValid) {
            logger.log(Level.WARNING, "Repetition value is invalid.");
            throw new InvalidWorkoutException(className, InvalidWorkoutException.INVALID_REPS_VALUE_ERROR_MSG);
        }

        Workout updatedWorkout = getWorkoutFromIndexNum(indexToUpdate);
        String exerciseName = updatedWorkout.getExerciseName();
        boolean isExistingWorkout = checkForExistingWorkout(exerciseName, newRepsValue);

        if (isExistingWorkout) {
            logger.log(Level.WARNING, "\"" + exerciseName + " (" + newRepsValue + ")\" "
                + "has already existed in the list. Cannot be updated.");
            throw new InvalidWorkoutException(className, InvalidWorkoutException.DUPLICATE_WORKOUT_ERROR_MSG);
        }

        assert (isIndexToUpdateValid && isNewRepsValueValid && !isExistingWorkout);
        String oldWorkoutKey = updatedWorkout.toString();
        updatedWorkout.setRepetitions(newRepsValue);
        updateWorkoutsHashMapList(oldWorkoutKey, updatedWorkout);
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
        for (String existingWorkoutKey : getWorkoutsDisplayList()) {
            Workout existingWorkout = getWorkoutsHashMapList().get(existingWorkoutKey);
            boolean hasSameExerciseName = existingWorkout.getExerciseName().equals(exerciseName);
            boolean hasSameRepsCount = (existingWorkout.getRepetitions() == repetitionCount);

            if (hasSameExerciseName && hasSameRepsCount) {
                logger.log(Level.INFO, "Existing workout found in the list.");
                return true;
            }
        }

        return false;
    }

    /**
     * Finds the workout that the user wants to update.
     *
     * @param userArgument The argument entered by user, which includes index of workout to update
     *                     and new number of repetitions.
     * @return targetWorkout The workout object which is going to be updated.
     * @throws InvalidWorkoutException If index of workout is not valid.
     */
    public String getCurrentWorkout(String userArgument) throws InvalidWorkoutException {
        String[] updateDetails = userArgument.split(" ", 2);
        String indexToUpdateString = updateDetails[0].trim();
        int indexToUpdate = Integer.parseInt(indexToUpdateString);

        boolean isIndexToUpdateValid = checkIndexIsWithinRange(indexToUpdate);
        String className = this.getClass().getSimpleName();

        if (!isIndexToUpdateValid) {
            throw new InvalidWorkoutException(className, InvalidWorkoutException.INDEX_VALUE_OUT_OF_RANGE);
        }

        String currentWorkout = workoutsDisplayList.get(indexToUpdate - 1);
        return currentWorkout;
    }
}
