package seedu.sherpass.command;

import seedu.sherpass.task.RecurringTask;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import java.time.LocalDateTime;

public class AddRecurringCommand extends Command {
    private String taskDescription;
    private LocalDateTime doOnDate;
    private boolean hasDoOnTime;

    public static final String COMMAND_WORD = "addrecurring";
    public static final String MESSAGE_USAGE = "addrecurring: Adds a recurring task into the task list.\n"
            + "A recurring task contains a task description and an optional date"
            + "\n\nTo execute the command,\nenter 'addrecurring TASK_DESCRIPTION [/do TASK_DATE].\n"
            + "E.g. addrecurring weekly revision /do 1/6/2022\n\n"
            + "All task dates must be given in the format:\n"
            + "\t\td/M/yyyy [HH:mm] , where\n"
            + "year is in 4 digits, month and day in one or two digits.";

    public AddRecurringCommand() {

    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setDoOnDate(LocalDateTime doOnDate) {
        this.doOnDate = doOnDate;
    }

    public boolean getHasDoOnTime() {
        return hasDoOnTime;
    }

    public void setHasDoOnTime(boolean hasDoOnTime) {
        this.hasDoOnTime = hasDoOnTime;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        RecurringTask newTask = new RecurringTask(taskDescription, doOnDate, hasDoOnTime);
        taskList.addRecurringTask(newTask);
        storage.writeSaveData(taskList);
    }
}
