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

import static seedu.sherpass.constant.Message.EDIT_TASK_RESULT_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_EMPTY_EDIT_CONTENT_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_INDEX_MESSAGE;
import static seedu.sherpass.constant.Message.TAB_INDENT;

public class EditCommand extends Command {

    private int editIndex;
    private String taskDescription;
    private LocalDateTime doOnStartDateTime;
    private LocalDateTime doOnEndDateTime;
    private LocalDateTime byDate;
    private LocalDate doOnDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isRepeating;

    public EditCommand(int editIndex, String taskDescription,
                       LocalDate doOnDate, LocalTime startTime, LocalTime endTime) {
        this.editIndex = editIndex;
        this.taskDescription = taskDescription;
        this.doOnDate = doOnDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void setRepeating(boolean repeating) {
        isRepeating = repeating;
    }

    public void setByDate(LocalDateTime byDate) {
        this.byDate = byDate;
    }

    public void checkValidCommand() throws InvalidInputException {
        boolean isDoDateNull = (doOnDate == null && startTime == null && endTime == null);
        boolean isByDateNull = (byDate == null);
        if (isDoDateNull && isByDateNull && taskDescription.isBlank()) {
            throw new InvalidInputException(ERROR_EMPTY_EDIT_CONTENT_MESSAGE);
        }
    }

    private void setDoOnDateStartEndTime(Task taskToEdit) {
        doOnDate = (doOnDate == null) ? taskToEdit.getDoOnStartDateTime().toLocalDate() : doOnDate;
        startTime = (startTime == null) ? taskToEdit.getDoOnStartDateTime().toLocalTime() : startTime;
        endTime = (endTime == null) ? taskToEdit.getDoOnEndDateTime().toLocalTime() : endTime;
        doOnStartDateTime = LocalDateTime.of(doOnDate, startTime);
        doOnEndDateTime = LocalDateTime.of(doOnDate, endTime);
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
            setDoOnDateStartEndTime(taskToEdit);
            if (isRepeating) {
                taskToEdit = taskList.editRepeatedTasks(editIndex, taskDescription,
                        doOnStartDateTime, doOnEndDateTime, byDate);
            } else {
                taskToEdit = taskList.editSingleTask(editIndex, taskDescription,
                        doOnStartDateTime, doOnEndDateTime, byDate);
            }
            storage.writeSaveData(taskList);
            ui.showToUser(EDIT_TASK_RESULT_MESSAGE);
            ui.showToUser(TAB_INDENT + taskToEdit);
        } catch (TimeClashException | InvalidInputException exception) {
            ui.showError(exception.getMessage());
        } catch (IndexOutOfBoundsException exception) {
            ui.showError(ERROR_INVALID_INDEX_MESSAGE);
        }
    }
}
