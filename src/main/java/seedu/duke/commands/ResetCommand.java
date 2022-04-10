package seedu.duke.commands;

import seedu.duke.data.ModuleList;
import seedu.duke.util.Configuration;
import seedu.duke.util.StringConstants;

//@@author ngys117
public class ResetCommand extends Command {

    /**
     * Resets all modules and tasks.
     * @param moduleList The list of modules
     * @param configuration The configuration settings of the application
     * @return A new {@code CommandResult} with the result string
     */
    @Override
    public CommandResult execute(ModuleList moduleList, Configuration configuration)  {
        moduleList.reset();
        assert (moduleList.getModuleList().size() == 0);
        assert (moduleList.getGeneralTasks().getTaskList().getSize() == 0);
        return new CommandResult(StringConstants.RESET_MESSAGE);
    }
}
