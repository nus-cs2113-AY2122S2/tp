package seedu.duke.commands;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.parsers.DeleteParser;
import seedu.duke.tasks.Task;

public class DeleteCommand extends Command {

    private String moduleCode = "";
    private int taskNumber = -1;
    private String argument;
    private String result = "";

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public DeleteCommand() {
    }

    public DeleteCommand(String argument) {
        this.argument = argument;
    }

    public Command prepareDeleteCommand() {
        DeleteParser deleteParser = new DeleteParser();
        try {
            return deleteParser.parseCommand(argument);
        } catch (ModHappyException e) {
            System.out.println("Error has occurred. Please try again.");
        }
        return null;
    }

    @Override
    public CommandResult execute() {
        if (taskNumber <= 0) {
            deleteModule();
        } else if (moduleCode.isBlank()) {
            deleteTask();
        } else {
            deleteTaskFromModule();
        }
        return new CommandResult(result);
    }

    public void deleteTaskFromModule() {
    }

    public void deleteTask() {
        int taskIndex = taskNumber - 1;
        String taskName = Task.taskList.get(taskIndex);
        Task.taskList.remove(taskIndex);
        result = taskName + " has been removed.";
    }

    public void deleteModule() {
    }

}
