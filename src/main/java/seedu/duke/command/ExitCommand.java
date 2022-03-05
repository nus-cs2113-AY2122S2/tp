package seedu.duke.command;

import seedu.duke.data.Manager;

/**
 * Represents a command that exits the application.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_TEXT = "exit";

    private static final String COMMAND_FORMAT = "Syntax: exit";

    /**
     * Prints the farewell message.
     *
     * @param manager A manager object that gets the UI object to print output.
     */
    @Override
    public void run(Manager manager) {
        manager.getUi().printFarewell();
    }
}
