package seedu.duke.commands;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.NoSuchModuleException;
import seedu.duke.tasks.Module;
import seedu.duke.tasks.ModuleList;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.TaskList;
import seedu.duke.util.StringConstants;

import java.util.Objects;

public class EditCommand extends Command {

    private static final String EDIT_MODULE_SUCCESS = StringConstants.EDIT_MODULE_SUCCESS;
    private static final String EDIT_TASK_SUCCESS = StringConstants.EDIT_TASK_SUCCESS;
    private static final String EDIT_TASK_WITH_MODULE_SUCCESS = StringConstants.EDIT_TASK_WITH_MODULE_SUCCESS;
    private static final String TASK_DESCRIPTION = StringConstants.TASK_DESCRIPTION_STR;
    private static final String ESTIMATED_WORKING_TIME = StringConstants.ESTIMATED_WORKING_TIME_STR;
    private static final String TASK_NAME = StringConstants.TASK_NAME_STR;

    private String moduleCode;
    private String taskModule;
    private int taskNumber = -1;
    private String taskParameter;
    private String result = "";
    private boolean isGeneralTask = false;
    final private String changedParameter;

    public int getTaskNumber() {
        return taskNumber;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getTaskModule() {
        return taskModule;
    }

    public EditCommand(String moduleCode, String description) {
        this.moduleCode = moduleCode;
        this.changedParameter = description;
    }

    public EditCommand(String taskModule, int taskNumber, String description, String workingTime, String taskName) {
        this.taskModule = taskModule;
        this.taskNumber = taskNumber;
        if (!Objects.isNull(description)) {
            this.taskParameter = TASK_DESCRIPTION;
            this.changedParameter = description;
        } else if (!Objects.isNull(workingTime)){
            this.taskParameter = ESTIMATED_WORKING_TIME;
            this.changedParameter = workingTime;
        } else {
            this.taskParameter = TASK_NAME;
            this.changedParameter = taskName;
        }
    }

    @Override
    public CommandResult execute(ModuleList moduleList) throws ModHappyException {
        if (taskNumber < 0) {
            editModuleDescription(moduleList);
        } else {
            Module targetModule;
            if (Objects.isNull(taskModule)) {
                targetModule = moduleList.getGeneralTasks();
                isGeneralTask = true;
            } else {
                targetModule = moduleList.getModule(taskModule);
                if (Objects.isNull(targetModule)) {
                    throw new NoSuchModuleException();
                }
            }
            editTaskFromModule(targetModule);
        }
        return new CommandResult(result);
    }

    /**
     * Changes module description of the target module.
     *
     * @param moduleList List from which the module's description is to be edited.
     */
    public void editModuleDescription(ModuleList moduleList) {
        Module targetModule = moduleList.getModule(moduleCode);
        targetModule.setModuleDescription(changedParameter);
        result = String.format(EDIT_MODULE_SUCCESS, targetModule.getModuleCode());
    }

    /**
     * Changes task parameter (either task description or estimated working time) of the target task.
     *
     * @param targetModule The module (or General Tasks) the target task belongs to.
     */
    private void editTaskFromModule(Module targetModule) {
        TaskList taskList = targetModule.getTaskList();
        int taskIndex = taskNumber - 1;
        Task targetTask = taskList.getTask(taskIndex);
        String targetTaskName = targetTask.getTaskName();
        if (taskParameter.equals(TASK_DESCRIPTION)) {
            targetTask.setTaskDescription(changedParameter);
        } else if (taskParameter.equals(ESTIMATED_WORKING_TIME)){
            targetTask.setWorkingTime(changedParameter);
        } else {
            targetTask.setTaskName(changedParameter);
        }
        if (isGeneralTask) {
            result = String.format(EDIT_TASK_SUCCESS, taskParameter, targetTaskName);
        } else {
            result = String.format(EDIT_TASK_WITH_MODULE_SUCCESS, taskParameter,
                    targetTaskName, targetModule.getModuleCode());
        }
    }

}
