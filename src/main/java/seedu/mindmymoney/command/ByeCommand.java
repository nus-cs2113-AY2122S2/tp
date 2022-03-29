package seedu.mindmymoney.command;

/**
 * Represents the Bye command.
 */
public class ByeCommand extends Command {

    /**
     * Indicates whether the program should exit.
     *
     * @return true if the program should exit, false otherwise.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Prints the bye message.
     */
    @Override
    public void executeCommand() {
        System.out.print("Bye, hope to see you again!"
                + System.lineSeparator());
    }
}
