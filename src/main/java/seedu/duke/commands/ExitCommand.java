package seedu.duke.commands;

import seedu.duke.tasks.ModuleList;
import seedu.duke.ui.TextUi;
import seedu.duke.util.StringConstants;

public class ExitCommand extends Command {

    public static boolean isExit = false;

    /**
     * Prepares the program for termination.
     */
    @Override
    public CommandResult execute(ModuleList moduleList) {
        // This will be replaced by some pre-end process later(e.g. Ask whether to save the modification)
        CommandResult result = new CommandResult(StringConstants.READY_EXIT);
        isExit = true;
        return result;
    }
}
