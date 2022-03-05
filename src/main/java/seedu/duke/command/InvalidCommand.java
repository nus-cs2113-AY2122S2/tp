package seedu.duke.command;

import seedu.duke.data.Profile;
import seedu.duke.ui.TextUI;

/**
 * Takes in an error message as a String.
 * Prints "Invalid command" followed by the error message.
 */
public class InvalidCommand extends Command {
    private final String errorMessage;

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void run(Manager manager) {
        manager.getUi().printlnMessage("Invalid command");
        manager.getUi().printlnMessage(errorMessage);
    }
}
