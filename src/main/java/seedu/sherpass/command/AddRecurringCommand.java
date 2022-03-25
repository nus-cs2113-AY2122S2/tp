package seedu.sherpass.command;

import seedu.sherpass.enums.Frequency;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.task.TaskLogic;
import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AddRecurringCommand extends Command {
    private String taskDescription;
    private LocalDateTime doOnStartDateTime;
    private LocalDateTime doOnEndDateTime;
    private Frequency frequency;

    public AddRecurringCommand() {

    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setDoOnStartDateTime(LocalDateTime doOnStartDateTime) {
        this.doOnStartDateTime = doOnStartDateTime;
    }

    public void setDoOnEndDateTime(LocalDateTime doOnEndDateTime) {
        this.doOnEndDateTime = doOnEndDateTime;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String error = TaskLogic.checkValidAddArgument(taskDescription, doOnStartDateTime, doOnEndDateTime, frequency);
        if (!error.isBlank()) {
            ui.showToUser(error);
            return;
        }

        int identifier = taskList.generateIdentifier();
        ArrayList<Task> newTasks = TaskLogic.prepareTasks(identifier, taskDescription,
                doOnStartDateTime, doOnEndDateTime, frequency);
        StringBuilder addedTaskString = new StringBuilder();
        for (Task t : newTasks) {
            taskList.addTask(t);
            addedTaskString.append(t);
            addedTaskString.append("\n ");
        }
        ui.showToUser("Got it. I've added " + newTasks.size() + " tasks:\n " + addedTaskString.toString().trim()
                + "\nNow you have " + taskList.getSize() + " task(s) in the list.");
        storage.writeSaveData(taskList);
    }
}
