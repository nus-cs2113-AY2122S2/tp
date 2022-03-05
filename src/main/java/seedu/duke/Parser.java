package seedu.duke;

import commands.Command;
import commands.InvalidCommandException;
import commands.WorkoutCommand;

/**
 * This class will parse the input that the user enters into the WerkIt! application into data
 * that can be further processed by other classes in this application.
 * <p>
 * Design of the commands is inspired by the AddressBook-Level2 project
 * Link: https://se-education.org/addressbook-level2/
 */
public class Parser {
    public Command parseUserInput(String userInput) throws ArrayIndexOutOfBoundsException,
            InvalidCommandException {
        // Determine the type of Command subclass to instantiate
        String commandKeyword = userInput.split(" ", 2)[0];

        switch (commandKeyword) {
        case WorkoutCommand.BASE_KEYWORD:
            return createWorkoutCommand(userInput);
        default:
            String className = this.getClass().getSimpleName();
            throw new InvalidCommandException(className, InvalidCommandException.INVALID_COMMAND_ERROR_MSG);
        }
    }

    public WorkoutCommand createWorkoutCommand(String userInput) throws ArrayIndexOutOfBoundsException,
            InvalidCommandException {
        // Determine the action the user has entered
        String actionKeyword = userInput.split(" ", 3)[1];
        String arguments = userInput.split(" ", 3)[2];

        return new WorkoutCommand(userInput, actionKeyword, arguments);
    }
}
