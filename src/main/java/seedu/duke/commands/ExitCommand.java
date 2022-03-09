package seedu.duke.commands;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.tasks.TaskList;

public class ExitCommand extends Command {

    private static final String EXIT_COMMAND_WORD = "exit";
    private static final String READY_EXIT = "I am ready to exit *_*";

    public static boolean isExit = false;

    public ExitCommand() {
        commandName = EXIT_COMMAND_WORD;
    }

    @Override
    public CommandResult execute(TaskList list) throws ModHappyException {
        // This will be replaced by some pre-end process later(e.g. Ask whether to save the modification)
        CommandResult result = new CommandResult(READY_EXIT);
        isExit = true;
        return result;
    }
}
