package seedu.duke.commands;

import seedu.duke.exceptions.NoSuchModuleException;
import seedu.duke.exceptions.NoSuchTaskException;
import seedu.duke.tasks.Module;
import seedu.duke.tasks.ModuleList;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.TaskList;

import java.util.Objects;

public class EditCommand extends Command {

    private static final String EDIT_MODULE_SUCCESS = "The description of %s has been changed.";
    private static final String EDIT_TASK_SUCCESS = "The %s of %s has been changed.";
    private static final String TASK_DESCRIPTION = "description";
    private static final String ESTIMATED_WORKING_TIME = "estimated working time";

    private String moduleCode = "";
    private int taskNumber = -1;
    private String changedString;
    private String taskParameter;
    private String result = "";

    public String getModuleCode() {
        return moduleCode;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public EditCommand(String moduleCode, String description) {
        this.moduleCode = moduleCode;
        this.changedString = description;
    }

    public EditCommand(String moduleCode, int taskNumber, String description, String workingTime) {
        this.moduleCode = moduleCode;
        this.taskNumber = taskNumber;
        if (!Objects.isNull(description)) {
            this.taskParameter = TASK_DESCRIPTION;
            this.changedString = description;
        } else {
            this.taskParameter = ESTIMATED_WORKING_TIME;
            this.changedString = workingTime;
        }
    }

    @Override
    public CommandResult execute(ModuleList moduleList) throws NoSuchTaskException, NoSuchModuleException {
        if (taskNumber < 0) {
            editModuleDescription(moduleList);
        } else if (!Objects.isNull(moduleCode)) {
            editTask(moduleList);
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
    public void editModuleDescription(ModuleList moduleList) throws NoSuchModuleException {
        Module targetModule = moduleList.getModule(moduleCode);
        targetModule.setModuleDescription(changedString);
        result = String.format(EDIT_MODULE_SUCCESS, targetModule.getModuleCode());
    }

    /**
     * Deletes given task from generalTasks in moduleList.
     *
     * @param moduleList List from which the task is to be deleted from.
     */
    private void editTask(ModuleList moduleList) throws NoSuchTaskException {
        Module targetModule = moduleList.getGeneralTasks();
        TaskList taskList = targetModule.getTaskList();
        int taskIndex = taskNumber - 1;
        Task targetTask = taskList.getTask(taskIndex);
        if (taskParameter.equals(TASK_DESCRIPTION)) {
            targetTask.setTaskDescription(changedString);
        } else {
            targetTask.setWorkingTime(changedString);
        }
        result = String.format(EDIT_TASK_SUCCESS, taskParameter, targetTask.getTaskName());
    }

    // TODO: Implement this after module and task has been linked
    public void deleteTaskFromModule() {

    }
}
