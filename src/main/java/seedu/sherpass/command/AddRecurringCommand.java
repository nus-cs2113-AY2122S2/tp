package seedu.sherpass.command;

import seedu.sherpass.enums.Frequency;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.task.TaskLogic;
import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import java.time.LocalDateTime;

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
        Task newTask = new Task(identifier, taskDescription, null,
                doOnStartDateTime, doOnEndDateTime, frequency);
        LocalDateTime endDate = TaskLogic.getEndDateForRecurrence(doOnStartDateTime, frequency);

        int count = 0;
        StringBuilder addedTaskString = new StringBuilder();
        do {
            ++count;
            taskList.addTask(newTask);
            addedTaskString.append(newTask);
            addedTaskString.append("\n ");
            doOnStartDateTime = TaskLogic.incrementDate(doOnStartDateTime, frequency);
            doOnEndDateTime = TaskLogic.incrementDate(doOnEndDateTime, frequency);
            newTask = new Task(identifier, taskDescription, null, doOnStartDateTime, doOnEndDateTime, frequency);
        } while (newTask.getDoOnStartDateTime().isBefore(endDate));

        ui.showToUser("Got it. I've added " + count + " tasks:\n " + addedTaskString
                + "\nNow you have " + taskList.getTasks().size() + " task(s) in the list.");
        storage.writeSaveData(taskList);
    }
}
