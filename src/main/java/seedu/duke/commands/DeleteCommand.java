package seedu.duke.commands;

import seedu.duke.exceptions.NoSuchTaskException;
import seedu.duke.tasks.Module;
import seedu.duke.tasks.ModuleList;
import seedu.duke.tasks.Task;
import seedu.duke.tasks.TaskList;

public class DeleteCommand extends Command {

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
        if (taskNumber <= 0) {
            deleteModule(moduleList);
        } else if (moduleCode.isBlank()) {
            deleteTask(moduleList);
        } else {
            deleteTaskFromModule();
        }
        return new CommandResult(result);
    }

    public void deleteModule(ModuleList moduleList) {
        moduleList.removeModule(moduleCode);
        result = moduleCode + " has been deleted.";
    }

    private void deleteTask(ModuleList moduleList) {
        Module targetModule = moduleList.getGeneralTasks();
        TaskList taskList = targetModule.getTaskList();
        int taskIndex = taskNumber - 1;
        try {
            Task task = taskList.getTask(taskIndex);
            taskList.removeTask(taskIndex);
            System.out.println(task + " has been deleted.");
        } catch (NoSuchTaskException e) {
            System.out.println("Failed to delete.");
        }
    }

    // TODO: Implement this after module and task has been linked
    public void deleteTaskFromModule() {

    }
}
