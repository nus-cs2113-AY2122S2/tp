package commands;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {
    /** Keyword to trigger exit command. */
    public static final String COMMAND_WORD = "exit";

    /** Help message for exit command. */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n" + "Example: " + COMMAND_WORD;

    /** Exit message. */
    private static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Spendvelope ...";

    /**
     * Executes the command and returns the result.
     *
     * @return Result of the command
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT);
    }

    /**
     * Checks if a command is an exit command.
     *
     * @param command Command to check
     * @return <code>true</code> if command is an exit command
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand; // instanceof returns false if it is null
    }
}
