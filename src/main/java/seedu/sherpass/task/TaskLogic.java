package seedu.sherpass.task;

import seedu.sherpass.enums.Frequency;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static seedu.sherpass.constant.Message.EMPTY_STRING;
import static seedu.sherpass.constant.Message.ERROR_EMPTY_DESCRIPTION_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_EMPTY_TASKLIST_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_DATETIME_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_FREQUENCY_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_INDEX_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_MISSING_EDIT_ARGUMENT_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_START_AFTER_END_TIME_MESSAGE;

public class TaskLogic {
    /**
     * Returns the incremented date according to the frequency.
     *
     * @param currentDate The current date to be incremented
     * @param frequency The frequency of recurrence.
     * @return The incremented date according to the frequency
     */
    public static LocalDateTime incrementDate(LocalDateTime currentDate, Frequency frequency) {
        if (frequency == Frequency.DAILY) {
            return currentDate.plusDays(1);
        } else if (frequency == Frequency.WEEKLY) {
            return currentDate.plusWeeks(1);
        }
        return currentDate.plusMonths(1);
    }

    /**
     * Returns the last recurrence of a recurring task.
     *
     * For tasks recurring daily, the last date is 1 month after the first occurrence.
     * For tasks recurring weekly, the last date is 2 month after the first occurrence.
     * For tasks recurring monthly, the last date is 1 year after the first occurrence.
     *
     * @param startDate The date of the first occurrence
     * @param frequency The frequency of recurrence
     * @return
     */
    public static LocalDateTime getEndDateForRecurrence(LocalDateTime startDate, Frequency frequency) {
        if (frequency == Frequency.DAILY) {
            return startDate.plusMonths(1);
        } else if (frequency == Frequency.WEEKLY) {
            return startDate.plusMonths(2);
        }
        return startDate.plusYears(1);
    }

    /**
     * Returns error message if any parameters are invalid.
     *
     * @param taskDescription The task description of the to-be-added recurring task
     * @param doOnStartDateTime The start date time of the to-be-added recurring task
     * @param doOnEndDateTime The end date time of the to-be-added recurring task
     * @param frequency The frequency of recurrence of the to-be-added recurring task
     * @return Error messages if parameters are invalid, empty string if valid
     */
    public static String checkValidAddArgument(String taskDescription, LocalDateTime doOnStartDateTime,
            LocalDateTime doOnEndDateTime, Frequency frequency) {
        if (taskDescription.isBlank()) {
            return ERROR_EMPTY_DESCRIPTION_MESSAGE;
        } else if (doOnStartDateTime == null || doOnEndDateTime == null) {
            return ERROR_INVALID_DATETIME_MESSAGE;
        } else if (doOnStartDateTime.isAfter(doOnEndDateTime)) {
            return ERROR_START_AFTER_END_TIME_MESSAGE;
        } else if (frequency == null) {
            return ERROR_INVALID_FREQUENCY_MESSAGE;
        }
        return EMPTY_STRING;
    }

    /**
     * Returns error message if any parameters are invalid.
     *
     * @param taskDescription The edited task description
     * @param doOnStartDateTime The edited start date time
     * @param doOnEndDateTime The edited end date time
     * @param taskList The task list containing the recurring tasks
     * @param index The index of the first task to be edited
     * @return Error messages if parameters are invalid, empty string if valid
     */
    public static String checkValidEditArgument(String taskDescription, LocalDateTime doOnStartDateTime,
            LocalDateTime doOnEndDateTime, TaskList taskList, int index) {
        boolean isEditDate = (doOnStartDateTime != null && doOnEndDateTime != null);
        if (taskDescription.isBlank() && !isEditDate) {
            return ERROR_MISSING_EDIT_ARGUMENT_MESSAGE;
        } else if (isEditDate && doOnStartDateTime.isAfter(doOnEndDateTime)) {
            return ERROR_START_AFTER_END_TIME_MESSAGE;
        } else if (taskList.getTasks().size() == 0) {
            return ERROR_EMPTY_TASKLIST_MESSAGE;
        } else if (!taskList.isTaskExist(index)) {
            return ERROR_INVALID_INDEX_MESSAGE;
        }
        return EMPTY_STRING;
    }

    /**
     * Returns error message if any parameters are invalid
     *
     * @param taskList The task list that is going to be modified
     * @param index The index of the task in the task list
     * @return Error messages if parameters are invalid, empty string if valid
     */
    public static String checkValidDeleteArgument(TaskList taskList, int index) {
        if (taskList.getTasks().size() == 0) {
            return ERROR_EMPTY_TASKLIST_MESSAGE;
        } else if (!taskList.isTaskExist(index)) {
            return ERROR_INVALID_INDEX_MESSAGE;
        }
        return EMPTY_STRING;
    }

    /**
     * Returns an arraylist of recurring tasks.
     *
     * @param identifier The identifier to distinguish this recurrence
     * @param taskDescription The description of the recurring tasks
     * @param doOnStartDateTime The start date and time of the first occurrence
     * @param doOnEndDateTime The end date and time of the first occurrence
     * @param frequency How often the task recurs
     * @return Arraylist of Task
     */
    public static ArrayList<Task> prepareTasks(int identifier, String taskDescription, LocalDateTime doOnStartDateTime,
            LocalDateTime doOnEndDateTime, Frequency frequency) {
        ArrayList<Task> newTasks = new ArrayList<>();
        LocalDateTime lastRecurrenceDate = getEndDateForRecurrence(doOnStartDateTime, frequency);
        Task newTask = new Task(identifier, taskDescription, null,
                doOnStartDateTime, doOnEndDateTime, frequency);
        do {
            newTasks.add(newTask);
            newTask = prepareNextTask(newTask);
        } while (newTask.getDoOnStartDateTime().isBefore(lastRecurrenceDate));

        return newTasks;
    }

    /**
     * Returns the next occurrence of a recurring task.
     *
     * @param currentTask The i-th task
     * @return The i+1 task
     */
    private static Task prepareNextTask(Task currentTask) {
        LocalDateTime newStartDate = incrementDate(currentTask.getDoOnStartDateTime(),
                currentTask.getRepeatFrequency());
        LocalDateTime newEndDate = incrementDate(currentTask.getDoOnEndDateTime(),
                currentTask.getRepeatFrequency());
        return new Task(currentTask.getIdentifier(), currentTask.getDescription(), null,
                newStartDate, newEndDate, currentTask.getRepeatFrequency());
    }
}
