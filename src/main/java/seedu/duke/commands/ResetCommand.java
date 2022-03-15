package seedu.duke.commands;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.tasks.Module;
import seedu.duke.tasks.ModuleList;
import seedu.duke.tasks.TaskList;
import seedu.duke.util.StringConstants;

import java.util.ArrayList;

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
        for (Module m: modules) {
            TaskList moduleTaskList = m.getTaskList();
            for (int i = 0; i < moduleTaskList.size(); i += 1) {
                moduleTaskList.removeTask(i);
            }
        }
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
        for (int i = 0; i < generalTaskList.size(); i += 1) {
            generalTaskList.removeTask(i);
        }
        assert (generalTaskList.size() == 0);
    }
}
