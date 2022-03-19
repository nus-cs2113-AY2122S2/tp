package seedu.mindmymoney.command;

public class ByeCommand extends Command {
    /**
     * Indicates whether the program should exit.
     *
     * @return Indication on whether the program should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Prints the bye message.
     */
    public void executeCommand() {
        System.out.print("Bye!! Hope to see you again when you have more expenditures to add!!"
                + System.lineSeparator());
    }
}
