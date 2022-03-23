package commands;

/**
 * Shows help instructions.
 */
public class HelpCommand extends Command {
    /** Keyword to trigger help command. */
    public static final String COMMAND_WORD = "help";

    /** Help message for help command. */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    /**
     * Executes the command and returns the result.
     *
     * @return Result of the command
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(
                AddCommand.MESSAGE_USAGE + "\n\n"
                + DeleteCommand.MESSAGE_USAGE + "\n\n"
                + ListCommand.MESSAGE_USAGE + "\n\n"
                + HelpCommand.MESSAGE_USAGE + "\n\n"
                + ExitCommand.MESSAGE_USAGE + "\n\n"
                + LimitCommand.MESSAGE_USAGE
        );
    }
}
