package seedu.duke.commands;

import seedu.duke.exceptions.NoSuchTaskException;
import seedu.duke.tasks.Module;
import seedu.duke.tasks.ModuleList;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.TaskList;

public class DeleteCommand extends Command {

    private static final String DELETE_MODULE_SUCCESS = "%s" + " has been deleted.";
    private static final String DELETE_MODULE_FAILED = "Failed to delete the module";
    private static final String DELETE_TASK_SUCCESS = "%s" + " has been deleted.";
    private static final String DELETE_TASK_FAILED = "Failed to delete the task.";

    private String moduleCode = "";
    private int taskNumber = -1;
    private String result = "";

    public DeleteCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public DeleteCommand(String moduleCode, int taskNumber) {
        this.moduleCode = moduleCode;
        this.taskNumber = taskNumber;
    }

    @Override
    public CommandResult execute(ModuleList moduleList) {
        if (taskNumber < 0) {
            deleteModule(moduleList);
        } else if (moduleCode.isBlank()) {
            deleteTask(moduleList);
        } else {
            deleteTaskFromModule();
        }
        return new CommandResult(result);
    }

    /**
     * Deletes given module from moduleList.
     *
     * @param moduleList List from which the module is to be deleted from.
     */
    public void deleteModule(ModuleList moduleList) {
        //TODO add exception when moduleCode does not exist
        moduleList.removeModule(moduleCode);
        result = String.format(DELETE_MODULE_SUCCESS, moduleCode);
    }

    /**
     * Deletes given task from generalTasks in moduleList.
     *
     * @param moduleList List from which the task is to be delete from.
     */
    private void deleteTask(ModuleList moduleList) {
        Module targetModule = moduleList.getGeneralTasks();
        TaskList taskList = targetModule.getTaskList();
        int taskIndex = taskNumber - 1;
        try {
            Task task = taskList.getTask(taskIndex);
            taskList.removeTask(taskIndex);
            result = String.format(DELETE_TASK_SUCCESS, task);
        } catch (IndexOutOfBoundsException | NoSuchTaskException e) {
            result = DELETE_TASK_FAILED;
        }
    }

    // TODO: Implement this after module and task has been linked
    public void deleteTaskFromModule() {

    }
}
