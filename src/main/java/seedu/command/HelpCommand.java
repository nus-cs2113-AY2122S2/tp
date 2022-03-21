package seedu.command;

/**
 * Subclass of Command. Shows information of the available commands to users.
 */
public class HelpCommand extends Command{
    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_DESCRIPTION = ": Shows details of available commands to users. "
            + System.lineSeparator()
            + "Parameters: NIL" + System.lineSeparator()
            + "Example: "
            + "help";

    /**
     * List all available commands and their usage examples.
     * @return CommandResult with description of all available commands for users to use.
     */
    public CommandResult execute() {
        return new CommandResult(AddCommand.COMMAND_WORD + AddCommand.COMMAND_DESCRIPTION
                + System.lineSeparator()
                + System.lineSeparator() + DeleteCommand.COMMAND_WORD + DeleteCommand.COMMAND_DESCRIPTION
                + System.lineSeparator()
                + System.lineSeparator() + UpdateCommand.COMMAND_WORD + UpdateCommand.COMMAND_DESCRIPTION
                + System.lineSeparator()
                + System.lineSeparator() + ListCommand.COMMAND_WORD + ListCommand.COMMAND_DESCRIPTION
                + System.lineSeparator()
                + System.lineSeparator() + ListCommand.COMMAND_WORD + ListCommand.COMMAND_DESCRIPTION_WITH_TYPE
                + System.lineSeparator()
                + System.lineSeparator() + CheckCommand.COMMAND_WORD + CheckCommand.COMMAND_DESCRIPTION
                + System.lineSeparator()
                + System.lineSeparator() + HelpCommand.COMMAND_WORD + HelpCommand.COMMAND_DESCRIPTION);
    }
}
