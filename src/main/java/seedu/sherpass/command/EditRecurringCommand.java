package seedu.sherpass.command;

import seedu.sherpass.enums.Frequency;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import java.time.LocalDateTime;

import static seedu.sherpass.constant.Message.ERROR_START_AFTER_END_TIME_MESSAGE;

public class EditRecurringCommand extends Command {
    private int index;
    private String taskDescription;
    private LocalDateTime doOnStartDateTime;
    private LocalDateTime doOnEndDateTime;

    public static final String COMMAND_WORD = "editrecurring";
    public static final String MESSAGE_USAGE = "editrecurring: Edit a recurring task in the task list.\n"
            + "You can edit the task description and the datetime of the task."
            + "\n\nTo execute the command,\nenter 'editrecurring INDEX "
            + "[TASK_DESCRIPTION] [/do DATE /start START_TIME /end END_TIME]'.\n"
            + "E.g. editrecurring 1 weekly revision /do 21/3/2022 /start 09:00 /end 10:00\n\n"
            + "DATE must be given in the format: d/M/yyyy, where\n"
            + "year is in 4 digits, month and day in one or two digits.\n"
            + "TIME must be given in the format: HH:mm.";


    public EditRecurringCommand() {

    }

    public void setIndex(int index) {
        this.index = index - 1;
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

    public String getTaskDescription() {
        return taskDescription;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (index > taskList.getTasks().size() || index < 0) {
            ui.showToUser("Invalid index!");
            return;
        } else if (doOnStartDateTime != null && doOnStartDateTime.isAfter(doOnEndDateTime)) {
            ui.showToUser(ERROR_START_AFTER_END_TIME_MESSAGE);
            return;
        }

        Frequency repeatFrequency = taskList.getTasks().get(index).getRepeatFrequency();
        int oldIdentifier = taskList.getTasks().get(index).getIdentifier();
        int newIdentifier = taskList.generateIdentifier();
        int editCount = 0;
        StringBuilder editedTaskString = new StringBuilder();

        for (int i = index; i < taskList.getTasks().size(); i++) {
            Task t = taskList.getTasks().get(i);
            if (t.getIdentifier() == oldIdentifier) {
                if (!taskDescription.isBlank()) {
                    t.setTaskDescription(taskDescription);
                }
                if (doOnStartDateTime != null) {
                    if (repeatFrequency == Frequency.DAILY) {
                        t.setDoOnStartDateTime(doOnStartDateTime.plusDays(editCount));
                        t.setDoOnEndDateTime(doOnEndDateTime.plusDays(editCount));
                    } else if (repeatFrequency == Frequency.WEEKLY) {
                        t.setDoOnStartDateTime(doOnStartDateTime.plusWeeks(editCount));
                        t.setDoOnEndDateTime(doOnEndDateTime.plusWeeks(editCount));
                    } else {
                        t.setDoOnStartDateTime(doOnStartDateTime.plusMonths(editCount));
                        t.setDoOnEndDateTime(doOnEndDateTime.plusMonths(editCount));
                    }
                }
                ++editCount;
                t.setIdentifier(newIdentifier);
                editedTaskString.append(t);
                editedTaskString.append("\n ");
            }
        }
        ui.printEditTaskMessage(editedTaskString.toString());
        storage.writeSaveData(taskList);
    }
}
