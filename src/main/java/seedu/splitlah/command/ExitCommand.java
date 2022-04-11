package seedu.splitlah.command;

import seedu.splitlah.data.Manager;

/**
 * Represents a command that prints the farewell message.
 * 
 * @author Roy
 */
public class ExitCommand extends Command {

    /**
     * Runs the command to print a farewell message.
     *
     * @param manager A Manager object that manages the TextUI, Profile and Storage object.
     */
    @Override
    public void run(Manager manager) {
        manager.getUi().printFarewell();
    }
}
