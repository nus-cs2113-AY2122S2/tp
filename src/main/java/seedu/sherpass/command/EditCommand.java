package seedu.sherpass.command;

import seedu.sherpass.enums.Frequency;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;

import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;
import seedu.sherpass.util.parser.TaskParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static seedu.sherpass.constant.DateAndTimeFormat.inputDateOnlyFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.inputWithTimeFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.timeOnlyFormat;
import static seedu.sherpass.constant.Message.ERROR_INVALID_DELETE_INDEX_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_INDEX_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_START_AFTER_END_TIME_MESSAGE;
import static seedu.sherpass.constant.Message.WHITESPACE;

public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = "Edit: Edit a task in the task list.\n"
            + "Usage: edit TASK_NUMBER [TASK_DESCRIPTION] [/do DATE /start START_TIME /end END_TIME]"
            + " [/by DEADLINE]\n\n"
            + "DATE & DEADLINE format: d/M/yyyy\n"
            + "START_TIME & END_TIME format: HH:mm";


    private int editIndex;
    private String taskDescription;
    private LocalDate doOnDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDateTime byDate;
    private boolean isRepeat;


    /**
     * Accepts an index to edit a task. Checks if index is valid
     * and corresponds to a task in the task list.
     *
     * @param editIndex Index of a task to edit.
     * @param taskList Array representation of tasks.
     * @throws NumberFormatException if index does not correspond to any task in the task list.
     */
    public void setEditIndex(int editIndex, TaskList taskList) throws NumberFormatException {
        if (taskList.isTaskNotExist(editIndex)) {
            throw new NumberFormatException(ERROR_INVALID_INDEX_MESSAGE);
        }
        this.editIndex = editIndex;
    }

    /**
     * Sets the boolean attribute isRepeat to true if user inputs includes
     * "/repeat". False otherwise.
     *
     * @param isRepeat boolean value checking if task to edit involves recurring tasks
     */
    public void setIsRepeat(boolean isRepeat) {
        this.isRepeat = isRepeat;
    }

    /**
     * Accept parsed user input (in proper format) for preparation of editing task.
     *
     * @param taskDescription parsed task description.
     * @param doOnDate parsed doOnStartDateTime.
     * @param startTime parsed startTime.
     * @param endTime parsed endTime.
     * @param byDate parsed byDate
     */
    public void setTaskContent(String taskDescription, LocalDate doOnDate,
                               LocalTime startTime,
                               LocalTime endTime, LocalDateTime byDate) {
        this.taskDescription = taskDescription;
        this.doOnDate = doOnDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.byDate = byDate;
    }

    /**
     * Executes the editing of a task or multiple tasks.
     *
     * @param taskList Array representation of tasks.
     * @param ui User Interface.
     * @param storage Overwrites the save file data.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task taskToEdit = taskList.getTask(editIndex);
        if (byDate != null && taskToEdit.getRepeatFrequency() != Frequency.SINGLE) {
            ui.showToUser("A repeated task does not have a /by DATE");
            return;
        }
        LocalDate currentTaskDate = (doOnDate == null)
                ? taskToEdit.getDoOnStartDateTime().toLocalDate() : doOnDate;
        LocalTime currentStartTime = (startTime == null)
                ? taskToEdit.getDoOnStartDateTime().toLocalTime() : startTime;
        LocalTime currentEndTime = (endTime == null)
                ? taskToEdit.getDoOnEndDateTime().toLocalTime() : endTime;
        if (currentEndTime.isAfter(currentEndTime)) {
            ui.showToUser(ERROR_START_AFTER_END_TIME_MESSAGE);
            return;
        }
        LocalDateTime currentDoOnStartDateTime =
                LocalDateTime.parse(currentTaskDate.format(inputDateOnlyFormat) + WHITESPACE
                        + currentStartTime.format(timeOnlyFormat), inputWithTimeFormat);
        LocalDateTime currentDoOnEndDateTime =
                LocalDateTime.parse(currentTaskDate.format(inputDateOnlyFormat) + WHITESPACE
                        + currentEndTime.format(timeOnlyFormat), inputWithTimeFormat);
        taskList.editTask(ui, taskToEdit, taskDescription, currentDoOnStartDateTime,
                currentDoOnEndDateTime, byDate, isRepeat);
        storage.writeSaveData(taskList);
    }
}
