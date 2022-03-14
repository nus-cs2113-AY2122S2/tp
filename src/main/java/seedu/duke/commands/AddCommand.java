package seedu.duke.commands;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.NoSuchModuleException;
import seedu.duke.tasks.Module;
import seedu.duke.tasks.ModuleList;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.TaskList;

import java.util.Objects;


public class AddCommand extends Command {

    private static final String ADD_TASK_MESSAGE = "Hey! I have added this task under %s!" + LS + "%s" + LS
            + "Now you have %d task(s) in your list!" + LS;
    private static final String ADD_MODULE_MESSAGE = "Hey! I have added this module!" + LS + "%s";
    private static final String MODULE_ALREADY_EXISTS = "A module with that name already exists...";

    private final boolean isAddTask;
    private Task newTask = null;
    private String targetModuleName = null;
    private Module newModule = null;

    public AddCommand(String name, String description, String estimatedWorkingTime, String taskModule) {
        newTask = new Task(name, description, estimatedWorkingTime);
        targetModuleName = taskModule;
        isAddTask = true;
    }

    public AddCommand(String moduleName, String moduleDescription) {
        newModule = new Module(moduleName, moduleDescription);
        isAddTask = false;
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
    public CommandResult execute(ModuleList moduleList) throws ModHappyException {
        String res = "";
        if (isAddTask) {
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
            if (!moduleList.isModuleExists(newModule.getModuleCode())) {
                res = String.format(ADD_MODULE_MESSAGE, moduleList.addModule(newModule));
            } else {
                res = MODULE_ALREADY_EXISTS;
            }
        }
        return new CommandResult(res);
    }
}
