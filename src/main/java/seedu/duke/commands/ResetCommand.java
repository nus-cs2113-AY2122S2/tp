package seedu.duke.commands;

import seedu.duke.data.ModuleList;
import seedu.duke.util.Configuration;
import seedu.duke.util.StringConstants;

public class ResetCommand extends Command {

    /**
     * Resets the program.
     */
    @Override
    public CommandResult execute(ModuleList moduleList, Configuration configuration)  {
        moduleList.reset();
        assert (moduleList.getModuleList().size() == 0);
        assert (moduleList.getGeneralTasks().getTaskList().getSize() == 0);
        return new CommandResult(StringConstants.RESET_MESSAGE);
    }
}
