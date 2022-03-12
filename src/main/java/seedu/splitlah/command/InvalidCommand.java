package seedu.splitlah.command;

import seedu.splitlah.data.Manager;

/**
 * Represents a command that takes in an error message and prints "Invalid command" followed by the error message.
 * 
 * @author Saurav
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
