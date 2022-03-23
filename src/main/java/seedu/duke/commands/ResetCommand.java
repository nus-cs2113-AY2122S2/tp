package seedu.duke.commands;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.data.ModuleList;
import seedu.duke.util.Configuration;
import seedu.duke.util.StringConstants;

public class ResetCommand extends Command {
    @Override
    public CommandResult execute(ModuleList moduleList, Configuration configuration) throws ModHappyException {
        moduleList.reset();
        assert (moduleList.getModuleList().size() == 0);
        assert (moduleList.getGeneralTasks().getTaskList().size() == 0);
        return new CommandResult(StringConstants.RESET_MESSAGE);
    }
}
