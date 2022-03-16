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
        // Even if there is an error writing to one file, we should still try to write to the other.
        String writeStatus = "";
        try {
            // Master Task List
            TaskListStorage taskListStorage = new TaskListStorage();
            TaskList taskList = moduleList.getGeneralTasks().getTaskList();
            taskListStorage.jsonWriter(taskList.getTaskList(), StringConstants.TASK_PATH);
            writeStatus += StringConstants.TASK_DATA_SAVE_SUCCESS + StringConstants.LS;
        } catch (ModHappyException e) {
            writeStatus += StringConstants.TASK_DATA_SAVE_FAILED + StringConstants.LS;
        }
        try {
            ModuleListStorage moduleListStorage = new ModuleListStorage();
            moduleListStorage.jsonWriter(moduleList.getModuleList(), StringConstants.MODULE_PATH);
            writeStatus += StringConstants.MODULE_DATA_SAVE_SUCCESS + StringConstants.LS;
        } catch (ModHappyException e) {
            writeStatus += StringConstants.MODULE_DATA_SAVE_FAILED + StringConstants.LS;
        }
        return new CommandResult(writeStatus);
    }
}
