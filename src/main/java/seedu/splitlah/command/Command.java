package seedu.splitlah.command;

import seedu.splitlah.data.Manager;

/**
 * Represents a generic command that the user has entered into the application
 * that can be run to produce a change or outcome.
 * 
 * @author Warren
 */
public abstract class Command {
    /**
     * Runs the command with the information parsed from the user input, using the specified
     * Profile and TextUI objects.
     * @param manager A Manager object that manages the TextUI and Profile object.
     */
    public abstract void run(Manager manager);

    /**
     * Checks if command object is an instance of an ExitCommand.
     *
     * @param command A command object to be checked.
     * @return true if it is an ExitCommand, else false.
     */
    public static boolean isExitCommand(Command command) {
        return command instanceof ExitCommand;
    }
}
