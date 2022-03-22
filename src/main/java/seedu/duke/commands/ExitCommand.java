package seedu.duke.commands;

import seedu.duke.tasks.ModuleList;
import seedu.duke.util.StringConstants;

public class ExitCommand extends Command {

    public static boolean isExit = false;

    /**
     * Prepares the program for termination.
     */
    @Override
    public CommandResult execute(ModuleList moduleList) {
        // TODO: ask the user whether changes should be saved, if unsaved changes were detected.
        CommandResult result = new CommandResult(StringConstants.READY_EXIT);
        isExit = true;
        return result;
    }
}
