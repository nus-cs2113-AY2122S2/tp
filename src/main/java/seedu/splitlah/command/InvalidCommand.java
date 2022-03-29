package seedu.splitlah.command;

import seedu.splitlah.data.Manager;
import seedu.splitlah.ui.Message;

/**
 * Represents a command that takes in an error message and prints "Invalid command" followed by the error message.
 * 
 * @author Saurav
 */
public class InvalidCommand extends Command {

    private final String errorMessage;

    /**
     * Initializes an InvalidCommand object.
     *
     * @param errorMessage A String object that represents the error message.
     */
    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Runs the command to print the error message.
     *
     * @param manager A Manager object that manages the TextUI, Profile and Storage object.
     */
    @Override
    public void run(Manager manager) {
        assert manager != null : Message.ASSERT_INVALIDCOMMAND_MANAGER_DOES_NOT_EXIST;
        manager.getUi().printlnMessage("Invalid command");
        manager.getUi().printlnMessage(errorMessage);
    }
}
