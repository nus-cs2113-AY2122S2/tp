package werkIt;

import commands.Command;
import commands.ExitCommand;
import commands.InvalidCommandException;
import commands.WorkoutCommand;
import data.exercises.ExerciseList;
import data.workouts.WorkoutList;

import static commands.WorkoutCommand.CREATE_ACTION_KEYWORD;
import static commands.WorkoutCommand.LIST_ACTION_KEYWORD;

/**
 * This class will parse the input that the user enters into the WerkIt! application into data
 * that can be further processed by other classes in this application.
 * <p>
 * Design of the commands is inspired by the AddressBook-Level2 project
 * Link: https://se-education.org/addressbook-level2/
 */
public class Parser {
    private UI ui;
    private ExerciseList exerciseList;
    private WorkoutList workoutList;

    public Parser(UI ui, ExerciseList exerciseList, WorkoutList workoutList) {
        this.ui = ui;
        this.exerciseList = exerciseList;
        this.workoutList = workoutList;
    }

    public UI getUi() {
        return ui;
    }

    public ExerciseList getExerciseList() {
        return exerciseList;
    }

    public WorkoutList getWorkoutList() {
        return workoutList;
    }

    public Command parseUserInput(String userInput) throws ArrayIndexOutOfBoundsException,
            InvalidCommandException {
        // Determine the type of Command subclass to instantiate
        String commandKeyword = userInput.split(" ", 2)[0];

        switch (commandKeyword) {
        case WorkoutCommand.BASE_KEYWORD:
            return createWorkoutCommand(userInput);
        case ExitCommand.BASE_KEYWORD:
            return createExitCommand(userInput);
        default:
            String className = this.getClass().getSimpleName();
            throw new InvalidCommandException(className, InvalidCommandException.INVALID_COMMAND_ERROR_MSG);
        }
    }

    public WorkoutCommand createWorkoutCommand(String userInput) throws ArrayIndexOutOfBoundsException,
            InvalidCommandException {
        // Determine the action the user has entered
        String actionKeyword = userInput.split(" ", 3)[1];
        String arguments = null;
        switch (actionKeyword){
        case CREATE_ACTION_KEYWORD:
            arguments = userInput.split(" ", 3)[2];
            break;
        case LIST_ACTION_KEYWORD:
            break;
        }
        return new WorkoutCommand(userInput, ui, workoutList, actionKeyword, arguments);
    }

    public ExitCommand createExitCommand(String userInput) {
        ExitCommand newCommand = new ExitCommand(userInput);
        return newCommand;
    }
}
