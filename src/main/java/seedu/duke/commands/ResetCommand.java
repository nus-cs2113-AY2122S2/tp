package seedu.duke.commands;

import java.util.ArrayList;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.tasks.Module;
import seedu.duke.tasks.ModuleList;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.TaskList;
import seedu.duke.util.StringConstants;

public class ResetCommand extends Command {
    @Override
    public CommandResult execute(ModuleList moduleList) throws ModHappyException {
        removeAll(moduleList);
        return new CommandResult(StringConstants.RESET_MESSAGE);
    }

    /**
     * Remove all tasks and modules from the list.
     *
     * @param moduleList List from which modules and tasks are to be deleted from.
     */
    public void removeAll(ModuleList moduleList) throws ModHappyException {
        removeGeneralTasks(moduleList);
        ArrayList<Module> modules = moduleList.getModuleList();
        modules.clear();
        assert (moduleList.getModuleList().size() == 0);
    }

    /**
     * Remove all general tasks.
     *
     * @param moduleList List from which general tasks can be accessed and deleted.
     */
    public void removeGeneralTasks(ModuleList moduleList) throws ModHappyException {
        Module generalTask = moduleList.getGeneralTasks();
        TaskList generalTaskList = generalTask.getTaskList();
        ArrayList<Task> generalTasks = generalTaskList.getList();
        generalTasks.clear();
        assert (generalTaskList.size() == 0);
    }
}
