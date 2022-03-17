package commands;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program." + System.lineSeparator()
            + "Example: " + COMMAND_WORD;

    private static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Spendvelope ...";

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT);
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand; // instanceof returns false if it is null
    }
}
