package seedu.duke.commands;

import seedu.duke.exceptions.ModHappyException;

public class ExitCommand extends Command {

    private static final String EXIT_COMMAND_WORD = "exit";
    private static final String READY_EXIT = "I am ready to exit *_*";

    public ExitCommand(String arguments) {
        commandName = EXIT_COMMAND_WORD;
    }

    @Override
    public CommandResult execute() throws ModHappyException {
        // This will be replaced by some pre-end process later(e.g. Ask whether to save the modification)
        CommandResult result = new CommandResult(READY_EXIT);
        return result;
    }
}
