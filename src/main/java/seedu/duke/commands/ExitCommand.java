package seedu.duke.commands;

import seedu.duke.tasks.ModuleList;

public class ExitCommand extends Command {
    private static final String READY_EXIT = "I am ready to exit *_*";

    public static boolean isExit = false;

    /**
     * Prepares the program for termination.
     */
    @Override
    public CommandResult execute(ModuleList moduleList) {
        // This will be replaced by some pre-end process later(e.g. Ask whether to save the modification)
        CommandResult result = new CommandResult(READY_EXIT);
        isExit = true;
        return result;
    }
}
