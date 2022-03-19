package seedu.splitlah.command;

import java.lang.AssertionError;
import seedu.splitlah.data.Manager;
import seedu.splitlah.ui.Message;

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
        assert manager != null : Message.ASSERT_INVALIDCOMMAND_MANAGER_DOES_NOT_EXIST;
        manager.getUi().printlnMessage("Invalid command");
        manager.getUi().printlnMessage(errorMessage);
    }
}
