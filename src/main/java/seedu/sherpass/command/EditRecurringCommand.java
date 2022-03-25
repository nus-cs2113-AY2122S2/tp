package seedu.sherpass.command;

import seedu.sherpass.enums.Frequency;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.task.TaskLogic;
import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import java.time.LocalDateTime;

public class EditRecurringCommand extends Command {
    private int index;
    private String taskDescription;
    private LocalDateTime doOnStartDateTime;
    private LocalDateTime doOnEndDateTime;

    public EditRecurringCommand() {

    }

    public void setIndex(int index) {
        this.index = index;
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

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String error = TaskLogic.checkValidEditArgument(taskDescription, doOnStartDateTime, doOnEndDateTime,
                taskList, index);
        if (!error.isBlank()) {
            ui.showToUser(error);
            return;
        }

        Frequency repeatFrequency = taskList.getTask(index).getRepeatFrequency();
        int oldIdentifier = taskList.getTask(index).getIdentifier();
        int newIdentifier = taskList.generateIdentifier();
        StringBuilder editedTaskString = new StringBuilder();

        for (int i = index; i < taskList.getSize(); i++) {
            Task t = taskList.getTask(i);
            if (t.getIdentifier() == oldIdentifier) {
                if (!taskDescription.isBlank()) {
                    t.setTaskDescription(taskDescription);
                }
                if (doOnStartDateTime != null) {
                    t.setDoOnStartDateTime(TaskLogic.incrementDate(doOnStartDateTime, repeatFrequency));
                    t.setDoOnEndDateTime(TaskLogic.incrementDate(doOnEndDateTime, repeatFrequency));
                }
                t.setIdentifier(newIdentifier);
                editedTaskString.append(t);
                editedTaskString.append("\n ");
            }
        }
        ui.printEditTaskMessage(editedTaskString.toString());
        storage.writeSaveData(taskList);
    }
}
