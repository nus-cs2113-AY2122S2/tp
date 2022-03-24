package seedu.sherpass.command;

import seedu.sherpass.enums.Frequency;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.CommonLogic;
import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import java.time.LocalDateTime;

import static seedu.sherpass.constant.Message.ERROR_EMPTY_DESCRIPTION_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_FREQUENCY_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_START_AFTER_END_TIME_MESSAGE;

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

    private String isValidArgument() {
        if (taskDescription.isBlank()) {
            return ERROR_EMPTY_DESCRIPTION_MESSAGE;
        } else if (doOnStartDateTime.isAfter(doOnEndDateTime)) {
            return ERROR_START_AFTER_END_TIME_MESSAGE;
        } else if (frequency == null) {
            return ERROR_INVALID_FREQUENCY_MESSAGE;
        }
        return "";
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String error = isValidArgument();
        if (!error.isBlank()) {
            ui.showToUser(error);
            return;
        }
        int identifier = taskList.generateIdentifier();
        Task newTask = new Task(identifier, taskDescription, null,
                doOnStartDateTime, doOnEndDateTime, frequency);
        LocalDateTime endDate = CommonLogic.getEndDateForRecurrence(doOnStartDateTime, frequency);

        int count = 0;
        StringBuilder addedTaskString = new StringBuilder();
        do {
            ++count;
            taskList.addTask(newTask);
            addedTaskString.append(newTask);
            addedTaskString.append("\n ");
            doOnStartDateTime = CommonLogic.incrementDate(doOnStartDateTime, frequency);
            doOnEndDateTime = CommonLogic.incrementDate(doOnEndDateTime, frequency);
            newTask = new Task(identifier, taskDescription, null, doOnStartDateTime, doOnEndDateTime, frequency);
        } while (newTask.getDoOnStartDateTime().isBefore(endDate));

        ui.showToUser("Got it. I've added " + count + " tasks:\n " + addedTaskString
                + "\nNow you have " + taskList.getTasks().size() + " task(s) in the list.");
        storage.writeSaveData(taskList);
    }
}
