package seedu.sherpass.command;

import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.exception.TimeClashException;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;

import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static seedu.sherpass.constant.Message.ERROR_INVALID_INDEX_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_SCHEDULE_CLASH_MESSAGE;

public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = "Edit: Edit a task in the task list.\n"
            + "Usage: edit TASK_NUMBER [TASK_DESCRIPTION] [/do DATE /start START_TIME /end END_TIME]"
            + " [/by DEADLINE]\n\n"
            + "DATE & DEADLINE format: d/M/yyyy\n"
            + "START_TIME & END_TIME format: HH:mm";

    private int editIndex;
    private String taskDescription;
    private LocalDateTime doOnStartDateTime;
    private LocalDateTime doOnEndDateTime;
    private LocalDateTime byDate;
    private boolean isRepeating;

    public EditCommand(int editIndex, String taskDescription,
                       LocalDate doOnDate, LocalTime startTime, LocalTime endTime) {
        this.editIndex = editIndex;
        this.taskDescription = taskDescription;
        this.doOnStartDateTime = (doOnDate == null ? null : LocalDateTime.of(doOnDate, startTime));
        this.doOnEndDateTime = (doOnDate == null ? null : LocalDateTime.of(doOnDate, endTime));
    }

    public void setRepeating(boolean repeating) {
        isRepeating = repeating;
    }

    public void setByDate(LocalDateTime byDate) {
        this.byDate = byDate;
    }

    /**
     * Executes the editing of a task or multiple tasks.
     *
     * @param taskList Array representation of tasks.
     * @param ui       User Interface.
     * @param storage  Overwrites the save file data.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task taskToEdit = taskList.getTask(editIndex);
            String repeated = "";

            if (isRepeating) {
                taskList.editRepeatedTasks(editIndex, taskDescription,
                        doOnStartDateTime, doOnEndDateTime, byDate);
                repeated = " repeated";
            } else {
                taskList.editSingleTaskContent(editIndex, taskDescription,
                        doOnStartDateTime, doOnEndDateTime, byDate);
            }
            ui.showToUser("Okay! I've edited this" + repeated + " task as such:\n\t" + taskToEdit);
            storage.writeSaveData(taskList);
        } catch (TimeClashException exception) {
            ui.showToUser(ERROR_SCHEDULE_CLASH_MESSAGE);
            ui.showLine();
            ui.showToUser("Clashing task: " + exception.getMessage());
        } catch (IndexOutOfBoundsException exception) {
            ui.showToUser(ERROR_INVALID_INDEX_MESSAGE);
        } catch (InvalidInputException exception) {
            ui.showToUser(exception.getMessage());
        }
    }
}
