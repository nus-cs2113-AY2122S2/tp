package seedu.duke.commands;

import java.util.Objects;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.NoSuchModuleException;
import seedu.duke.tasks.Module;
import seedu.duke.tasks.ModuleList;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.TaskList;
import seedu.duke.util.StringConstants;
import seedu.duke.util.NumberConstants;

public class EditCommand extends Command {

    private static final String EDIT_MODULE_SUCCESS = StringConstants.EDIT_MODULE_SUCCESS;
    private static final String EDIT_TASK_SUCCESS = StringConstants.EDIT_TASK_SUCCESS;
    private static final String EDIT_TASK_WITH_MODULE_SUCCESS = StringConstants.EDIT_TASK_WITH_MODULE_SUCCESS;
    private static final String TASK_DESCRIPTION = StringConstants.TASK_DESCRIPTION_STR;
    private static final String ESTIMATED_WORKING_TIME = StringConstants.ESTIMATED_WORKING_TIME_STR;
    private static final String TASK_NAME = StringConstants.TASK_NAME_STR;

    private String moduleCode;
    private String taskModule;
    private int taskIndex = NumberConstants.INVALID_TASK_INDEX;
    private String taskParameter;
    private String result = "";
    private boolean isGeneralTask = false;
    private final String changedParameter;

    public int getTaskIndex() {
        return taskIndex;
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

    public EditCommand(String taskModule, int taskIndex, String description, String workingTime, String taskName) {
        this.taskModule = taskModule;
        this.taskIndex = taskIndex;
        if (!Objects.isNull(description)) {
            this.taskParameter = TASK_DESCRIPTION;
            this.changedParameter = description;
            assert Objects.isNull(workingTime);
            assert Objects.isNull(taskName);
        } else if (!Objects.isNull(workingTime)) {
            this.taskParameter = ESTIMATED_WORKING_TIME;
            this.changedParameter = workingTime;
            assert Objects.isNull(taskName);
        } else {
            this.taskParameter = TASK_NAME;
            this.changedParameter = taskName;
        }
    }

    /**
     * Gets the module that the target task belongs to, or General Tasks if it does not belong to any module.
     * @param moduleList List of modules from which the target task belongs to, or General Tasks if it does not
     *                   belong to any module.
     * @return the module the target task belongs to, or General Tasks if it does not belong to any module.
     * @throws NoSuchModuleException if the target module does not exist
     */
    private Module getTargetModule(ModuleList moduleList) throws NoSuchModuleException {
        Module targetModule;
        if (Objects.isNull(taskModule)) {
            isGeneralTask = true;
            targetModule = moduleList.getGeneralTasks();
        } else {
            targetModule = moduleList.getModule(taskModule);
            if (Objects.isNull(targetModule)) {
                throw new NoSuchModuleException();
            }
        }
        return targetModule;
    }

    @Override
    public CommandResult execute(ModuleList moduleList) throws ModHappyException {
        if (taskIndex < 0) {
            editModuleDescription(moduleList);
        } else {
            Module targetModule = getTargetModule(moduleList);
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
        Task targetTask = taskList.getTask(taskIndex);
        String targetTaskName = targetTask.getTaskName();
        if (taskParameter.equals(TASK_DESCRIPTION)) {
            targetTask.setTaskDescription(changedParameter);
        } else if (taskParameter.equals(ESTIMATED_WORKING_TIME)) {
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
