package seedu.sherpass.task;

import seedu.sherpass.enums.Frequency;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.exception.TimeClashException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import static seedu.sherpass.constant.Message.ERROR_BY_DATE_BEFORE_DO_ON_DATE;
import static seedu.sherpass.constant.Message.ERROR_SCHEDULE_CLASH_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_START_AFTER_END_TIME_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_START_DATE_IN_THE_PAST_MESSAGE;

public class TaskUtil {
    /**
     * Returns a new task with the same identifier and description.
     * The dates incremented according to the frequency.
     *
     * @param frequency The frequency of recurrence
     * @return The next occurrence of the task
     */
    public static Task prepareNextTask(Task currentTask, Frequency frequency) {
        LocalDateTime newStartDate = incrementDate(currentTask.getDoOnStartDateTime(),
                frequency);
        LocalDateTime newEndDate = incrementDate(currentTask.getDoOnEndDateTime(),
                frequency);
        LocalDateTime newByDate = currentTask.getByDateTime();
        if (newByDate != null) {
            newByDate = incrementDate(newByDate, frequency);
        }
        return new Task(currentTask.getIdentifier(), currentTask.getDescription(), newByDate,
                newStartDate, newEndDate);
    }

    /**
     * Returns the last recurrence of a recurring task.
     * For tasks recurring daily, the last date is 1 month after the first occurrence.
     * For tasks recurring weekly, the last date is 2 month after the first occurrence.
     * For tasks recurring monthly, the last date is 1 year after the first occurrence.
     *
     * @param startDate The date of the first occurrence
     * @param frequency The frequency of recurrence
     * @return The date of the last recurrence for a recurring task.
     */
    public static LocalDateTime getEndDateForRecurrence(LocalDateTime startDate, Frequency frequency) {
        if (frequency == Frequency.SINGLE) {
            return startDate;
        } else if (frequency == Frequency.DAILY) {
            return startDate.plusMonths(1);
        } else if (frequency == Frequency.WEEKLY) {
            return startDate.plusMonths(2);
        }
        return startDate.plusYears(1);
    }

    /**
     * Returns the incremented date according to the frequency.
     *
     * @param currentDate The current date to be incremented
     * @param frequency   The frequency of recurrence.
     * @return The incremented date according to the frequency
     */
    public static LocalDateTime incrementDate(LocalDateTime currentDate, Frequency frequency) {
        if (frequency == Frequency.SINGLE) {
            return currentDate;
        } else if (frequency == Frequency.DAILY) {
            return currentDate.plusDays(1);
        } else if (frequency == Frequency.WEEKLY) {
            return currentDate.plusWeeks(1);
        }
        return currentDate.plusMonths(1);
    }

    /**
     * Returns the difference between two dates in seconds.
     *
     * @param oldDateTime The original date
     * @param newDateTime The new date
     * @return Amount of time from original date to new date in seconds
     */
    public static long calculateOffsetOfDate(LocalDateTime oldDateTime, LocalDateTime newDateTime) {
        if (oldDateTime != null && newDateTime != null) {
            return oldDateTime.until(newDateTime, ChronoUnit.SECONDS);
        }
        return 0;
    }

    /**
     * Checks if there is any date and time clashes
     * for a given array.
     *
     * @param taskList    Array representation of tasks.
     * @param taskToCheck New Task to be checked for clash.
     * @param isFromFile boolean value checking if task to check for is from a save file.
     * @throws TimeClashException If there is a date and time clash, i.e.
     *                            taskToCheck has the same date and clashing of time periods
     *                            with tasks in taskList
     */
    public static void checkDateTimeClash(ArrayList<Task> taskList, Task taskToCheck,
                                          boolean isFromFile, boolean isEditByOnly)
            throws TimeClashException, InvalidInputException {
        if (isStartTimeClashWithEndTime(taskToCheck)) {
            throw new InvalidInputException(ERROR_START_AFTER_END_TIME_MESSAGE);
        }
        if (!isEditByOnly && !isFromFile && !taskToCheck.isDone()
                && taskToCheck.getDoOnStartDateTime().isBefore(LocalDateTime.now())) {
            throw new InvalidInputException(ERROR_START_DATE_IN_THE_PAST_MESSAGE);
        }
        if (isByDateBeforeDoOnDate(taskToCheck)) {
            throw new InvalidInputException(ERROR_BY_DATE_BEFORE_DO_ON_DATE);
        }
        for (Task task : taskList) {
            if (isOnSameDay(task.getDoOnStartDateTime(), taskToCheck.getDoOnStartDateTime())
                    && hasTimeClash(task, taskToCheck.getDoOnStartDateTime(), taskToCheck.getDoOnEndDateTime())) {
                throw new TimeClashException(ERROR_SCHEDULE_CLASH_MESSAGE
                        + "\n\t-> " + task.printTask() + "\n\t-> " + taskToCheck.printTask());
            }
        }
    }

    private static boolean isOnSameDay(LocalDateTime firstDate, LocalDateTime secondDate) {
        return firstDate.toLocalDate().equals(secondDate.toLocalDate());
    }

    private static boolean isEndTimeAfterCurrentStartTime(Task currentTask, LocalDateTime doOnStartDateTime,
                                                          LocalDateTime doOnEndDateTime) {
        return doOnEndDateTime.isAfter(currentTask.getDoOnStartDateTime())
                && (doOnStartDateTime.isBefore(currentTask.getDoOnStartDateTime())
                || doOnStartDateTime.equals(currentTask.getDoOnStartDateTime()));
    }

    private static boolean isStartTimeBeforeCurrentEndTime(Task currentTask, LocalDateTime doOnStartDateTime,
                                                           LocalDateTime doOnEndDateTime) {
        return doOnStartDateTime.isBefore(currentTask.getDoOnEndDateTime())
                && (doOnEndDateTime.isAfter(currentTask.getDoOnEndDateTime())
                || doOnEndDateTime.equals(currentTask.getDoOnEndDateTime()));
    }

    private static boolean isStartAndEndTimeWithinCurrentTime(Task currentTask, LocalDateTime doOnStartDateTime,
                                                              LocalDateTime doOnEndDateTime) {
        return doOnStartDateTime.isAfter(currentTask.getDoOnStartDateTime())
                && doOnEndDateTime.isBefore(currentTask.getDoOnEndDateTime());
    }

    private static boolean isStartAndEndTimeContainCurrentTime(Task currentTask, LocalDateTime doOnStartDateTime,
                                                               LocalDateTime doOnEndDateTime) {
        return currentTask.getDoOnStartDateTime().isAfter(doOnStartDateTime)
                && currentTask.getDoOnEndDateTime().isBefore(doOnEndDateTime);
    }

    private static boolean isStartAndEndTimeEqualsCurrentTime(Task currentTask, LocalDateTime doOnStartDateTime,
                                                              LocalDateTime doOnEndDateTime) {
        return doOnStartDateTime.isEqual(currentTask.getDoOnStartDateTime())
                && doOnEndDateTime.equals(currentTask.getDoOnEndDateTime());
    }

    private static boolean hasTimeClash(Task currentTask, LocalDateTime doOnStartDateTime,
                                        LocalDateTime doOnEndDateTime) {
        return isStartAndEndTimeEqualsCurrentTime(currentTask, doOnStartDateTime, doOnEndDateTime)
                || isEndTimeAfterCurrentStartTime(currentTask, doOnStartDateTime, doOnEndDateTime)
                || isStartTimeBeforeCurrentEndTime(currentTask, doOnStartDateTime, doOnEndDateTime)
                || isStartAndEndTimeWithinCurrentTime(currentTask, doOnStartDateTime, doOnEndDateTime)
                || isStartAndEndTimeContainCurrentTime(currentTask, doOnStartDateTime, doOnEndDateTime);
    }

    private static boolean isStartTimeClashWithEndTime(Task taskToCheck) {
        return taskToCheck.getDoOnStartDateTime().isAfter(taskToCheck.getDoOnEndDateTime())
                || taskToCheck.getDoOnStartDateTime().equals(taskToCheck.getDoOnEndDateTime());
    }

    private static boolean isByDateBeforeDoOnDate(Task taskToCheck) {
        if (taskToCheck.getByDateTime() == null) {
            return false;
        }
        return !taskToCheck.getByDateTime().isAfter(taskToCheck.getDoOnEndDateTime());
    }
}
