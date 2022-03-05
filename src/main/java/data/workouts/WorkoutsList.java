package data.workouts;

import data.exercises.InvalidExerciseException;
import seedu.duke.Parser;

import java.util.ArrayList;

public class WorkoutsList {
    Parser parser = new Parser();

    ArrayList<Workout> workoutsList = new ArrayList<>();

    public Workout createWorkout(String userArgument) throws ArrayIndexOutOfBoundsException,
            InvalidExerciseException, InvalidWorkoutsException {
        String userExerciseInput = userArgument.split("/reps")[0];
        int userRepsInput = Integer.parseInt(userArgument.split("/reps")[1]);

        boolean isExerciseValid = checkIfExerciseExists(userExerciseInput);
        boolean isRepsValueValid = checkIfRepsValueIsValid(userRepsInput);

        String className = this.getClass().getSimpleName();
        if (!isExerciseValid) {
            throw new InvalidExerciseException(className, InvalidExerciseException.INVALID_EXERCISE_NAME_ERROR_MSG);
        }

        if (!isRepsValueValid) {
            throw new InvalidWorkoutsException(className, InvalidWorkoutsException.INVALID_REPS_VALUE);
        }

        Workout newWorkout = new Workout(userExerciseInput, userRepsInput);

        workoutsList.add(newWorkout);

        return newWorkout;
    }

    public boolean checkIfExerciseExists(String userExerciseInput) {
        return false;
    }

    public boolean checkIfRepsValueIsValid(int userRepsInput) {
        return userRepsInput >= 1;
    }
}
