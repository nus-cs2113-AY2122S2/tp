package seedu.splitlah.command;

import seedu.splitlah.data.Manager;

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
