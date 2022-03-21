package seedu.sherpass.command;

import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import java.time.LocalDateTime;

public class EditRecurringCommand extends Command {
    private int index;
    private String taskDescription;
    private LocalDateTime doOnDate;
    private boolean hasDoOnTime;

    public static final String COMMAND_WORD = "editrecurring";
    public static final String MESSAGE_USAGE = "editrecurring: Edit a recurring task in the task list.\n"
            + "A recurring task contains the index of the task to be edited.\n"
            + "You can edit the task description and the date of the task."
            + "\n\nTo execute the command,\nenter 'editrecurring INDEX [TASK_DESCRIPTION] [/do DATE]'.\n"
            + "E.g. editrecurring 1 weekly revision /do 21/3/2022 09:00\n\n"
            + "All task dates must be given in the format: d/M/yyyy [HH:mm] , where\n"
            + "year is in 4 digits, month and day in one or two digits."
            + "\nTime can also be optionally specified.";


    public EditRecurringCommand() {

    }

    public void setIndex(int index) {
        this.index = index - 1;
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
        if (index > taskList.getTasks().size() || index < 0) {
            ui.showToUser("Invalid index!");
            return;
        }
        if (!taskDescription.isBlank()) {
            taskList.getTasks().get(index).setTaskDescription(taskDescription);
        }
        if (doOnDate != null) {
            taskList.getTasks().get(index).setDoOnDate(doOnDate);
            taskList.getTasks().get(index).setHasDoOnTime(hasDoOnTime);
        }
        ui.printEditTaskMessage(taskList.getTasks().get(index).toString());
        storage.writeSaveData(taskList);
    }
}
