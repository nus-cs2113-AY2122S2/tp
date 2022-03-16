package seedu.duke.commands;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.storage.ModuleListStorage;
import seedu.duke.storage.TaskListStorage;
import seedu.duke.tasks.ModuleList;
import seedu.duke.tasks.TaskList;
import seedu.duke.util.StringConstants;

public class SaveCommand extends Command {

    @Override
    public CommandResult execute(ModuleList moduleList) throws ModHappyException {
        try {
            // Master Task List
            TaskListStorage taskListStorage = new TaskListStorage();
            TaskList taskList = moduleList.getGeneralTasks().getTaskList();
            taskListStorage.jsonWriter(taskList.getTaskList(), StringConstants.TASK_PATH);
            ModuleListStorage moduleListStorage = new ModuleListStorage();
            moduleListStorage.jsonWriter(moduleList.getModuleList(), StringConstants.MODULE_PATH);
        } catch (ModHappyException e) {
            throw e;
        }
        return new CommandResult(StringConstants.SAVED_SUCCESSFULLY);
    }
}
