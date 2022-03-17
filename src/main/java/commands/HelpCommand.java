package commands;


/**
 * Shows help instructions.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        return new CommandResult(
                AddCommand.MESSAGE_USAGE + System.lineSeparator()
                + DeleteCommand.MESSAGE_USAGE + System.lineSeparator()
                + ListCommand.MESSAGE_USAGE + System.lineSeparator()
                + HelpCommand.MESSAGE_USAGE + System.lineSeparator()
                + ExitCommand.MESSAGE_USAGE
        );
    }
}
