package seedu.command;

public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String COMMAND_DESCRIPTION = ": Exits the application. "
            + System.lineSeparator()
            + "Parameters: NIL" + System.lineSeparator()
            + "Example: "
            + "bye";
    public static final String EXIT_MESSAGE = "Bye, See you again!";

    /**
     * Exits the application.
     * @return CommandResult with exit message.
     */
    public CommandResult execute() {
        return new CommandResult(EXIT_MESSAGE);
    }
}
