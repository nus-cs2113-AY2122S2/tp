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
    public static LocalDateTime incrementDate(LocalDateTime date, Frequency frequency) {
        if (frequency == Frequency.DAILY) {
            return date.plusDays(1);
        } else if (frequency == Frequency.WEEKLY) {
            return date.plusWeeks(1);
        }
        return date.plusMonths(1);
    }

    public static LocalDateTime getEndDateForRecurrence(LocalDateTime date, Frequency frequency) {
        if (frequency == Frequency.DAILY) {
            return date.plusMonths(1);
        } else if (frequency == Frequency.WEEKLY) {
            return date.plusMonths(2);
        }
        return date.plusYears(1);
    }

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

    public static String checkValidDeleteArgument(TaskList taskList, int index) {
        if (taskList.getTasks().size() == 0) {
            return ERROR_EMPTY_TASKLIST_MESSAGE;
        } else if (!taskList.isTaskExist(index)) {
            return ERROR_INVALID_INDEX_MESSAGE;
        }
        return EMPTY_STRING;
    }
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
    private static Task prepareNextTask(Task currentTask) {
        LocalDateTime newStartDate = incrementDate(currentTask.getDoOnStartDateTime(),
                currentTask.getRepeatFrequency());
        LocalDateTime newEndDate = incrementDate(currentTask.getDoOnEndDateTime(),
                currentTask.getRepeatFrequency());
        return new Task(currentTask.getIdentifier(), currentTask.getDescription(), null,
                newStartDate, newEndDate, currentTask.getRepeatFrequency());
    }
}
