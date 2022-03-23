package seedu.duke.commands;

import java.util.Objects;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.NoSuchModuleException;
import seedu.duke.tasks.Module;
import seedu.duke.tasks.ModuleList;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.TaskList;
import seedu.duke.util.Configuration;
import seedu.duke.util.StringConstants;

public class AddCommand extends Command {
    public enum AddObjectType {
        TASK, MODULE
    }

    private static final String ADD_TASK_MESSAGE = StringConstants.ADD_TASK_MESSAGE_TOP + LS + "%s" + LS
            + StringConstants.ADD_TASK_MESSAGE_BOTTOM + LS;
    private static final String ADD_MODULE_MESSAGE = StringConstants.ADD_MODULE_MESSAGE_TOP + LS + "%s";

    private final AddObjectType typeToAdd;
    private Task newTask = null;
    private String targetModuleName = null;
    private Module newModule = null;

    /**
     * Constructor for use with commands involving adding tasks.
     */
    public AddCommand(AddObjectType type, String taskName, String taskDescription, String estimatedWorkingTime,
                      String taskModule) {
        assert type == AddObjectType.TASK;
        typeToAdd = type;
        newTask = new Task(taskName, taskDescription, estimatedWorkingTime);
        targetModuleName = taskModule;
    }

    /**
     * Constructor for use with commands involving adding modules.
     */
    public AddCommand(AddObjectType type, String moduleCode, String moduleDescription, int modularCredit) {
        assert type == AddObjectType.MODULE;
        typeToAdd = type;
        newModule = new Module(moduleCode, moduleDescription, modularCredit);
    }

    public Task getNewTask() {
        return newTask;
    }

    public String getTargetModuleName() {
        return targetModuleName;
    }

    public Module getNewModule() {
        return newModule;
    }

    /**
     * Adds the specified task or module.
     */
    @Override
    public CommandResult execute(ModuleList moduleList, Configuration configuration) throws ModHappyException {
        String res = "";
        if (typeToAdd == AddObjectType.TASK) {
            Module targetModule = moduleList.getGeneralTasks();
            if (!Objects.isNull(targetModuleName)) {
                targetModule = moduleList.getModule(targetModuleName);
                if (Objects.isNull(targetModule)) {
                    throw new NoSuchModuleException();
                }
            }
            TaskList taskList = targetModule.getTaskList();
            res = String.format(ADD_TASK_MESSAGE, targetModule, taskList.addTask(newTask), taskList.size());
        } else {
            assert typeToAdd == AddObjectType.MODULE;
            if (!moduleList.isModuleExists(newModule.getModuleCode())) {
                res = String.format(ADD_MODULE_MESSAGE, moduleList.addModule(newModule));
            } else {
                res = StringConstants.MODULE_ALREADY_EXISTS;
            }
        }
        return new CommandResult(res);
    }
}
