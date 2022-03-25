package seedu.sherpass.command;

import seedu.sherpass.task.TaskList;

import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import java.time.LocalDateTime;

public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = "edit: Edit a task in the task list.\n"
            + "Usage: edit TASK_NUMBER [TASK_DESCRIPTION] [/do DATE /start START_TIME /end END_TIME] [/repeat]\n\n"
            + "DATE must be given in the format: d/M/yyyy, where\n"
            + "year is in 4 digits, month and day in one or two digits.\n"
            + "TIME must be given in the format: HH:mm.";


    int taskIndex;
    String taskDescription;
    LocalDateTime byDate;
    LocalDateTime doOnDate;

    public EditCommand(int taskNumber, String taskDescription, LocalDateTime byDate, LocalDateTime doOnDate) {
        this.taskIndex = taskNumber - 1;
        this.taskDescription = taskDescription;
        this.byDate = byDate;
        this.doOnDate = doOnDate;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (!taskDescription.trim().isBlank()) {
            taskList.getTasks().get(taskIndex).setTaskDescription(taskDescription);
        }
        if (byDate != null) {
            taskList.getTasks().get(taskIndex).setByDate(byDate);
        }
        if (doOnDate != null) {
            taskList.getTasks().get(taskIndex).setDoOnStartDateTime(doOnDate);
        }
        ui.printEditTaskMessage(taskList.getTasks().get(taskIndex).toString());
        storage.writeSaveData(taskList);
    }
}
