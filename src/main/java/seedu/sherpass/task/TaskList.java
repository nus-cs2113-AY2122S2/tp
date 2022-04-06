package seedu.sherpass.task;

import seedu.sherpass.enums.Frequency;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.exception.TimeClashException;
import seedu.sherpass.util.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.Collectors;

import static seedu.sherpass.constant.Message.ERROR_BY_DATE_BEFORE_DO_ON_DATE;
import static seedu.sherpass.constant.Message.ERROR_SCHEDULE_CLASH_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_START_AFTER_END_TIME_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_START_DATE_IN_THE_PAST_MESSAGE;
import static seedu.sherpass.constant.Message.NEWLINE;
import static seedu.sherpass.constant.Message.TAB_INDENT;

public class TaskList {
    private ArrayList<Task> tasks;
    private final HashSet<Integer> identifierList;

    /**
     * Creates a constructor for the class TaskList.
     *
     * @param savedTasks Representation of an array of tasks.
     */
    public TaskList(ArrayList<Task> savedTasks) {
        tasks = savedTasks;
        identifierList = new HashSet<>();
        updateIndex();
    }

    public TaskList() {
        identifierList = new HashSet<>();
        tasks = new ArrayList<>();
    }

    /**
     * Returns the array of tasks in the class TaskList.
     *
     * @return the array of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the incremented date according to the frequency.
     *
     * @param currentDate The current date to be incremented
     * @param frequency   The frequency of recurrence.
     * @return The incremented date according to the frequency
     */
    private static LocalDateTime incrementDate(LocalDateTime currentDate, Frequency frequency) {
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
     * Returns the last recurrence of a recurring task.
     * For tasks recurring daily, the last date is 1 month after the first occurrence.
     * For tasks recurring weekly, the last date is 2 month after the first occurrence.
     * For tasks recurring monthly, the last date is 1 year after the first occurrence.
     *
     * @param startDate The date of the first occurrence
     * @param frequency The frequency of recurrence
     * @return The date of the last recurrence for a recurring task.
     */
    private static LocalDateTime getEndDateForRecurrence(LocalDateTime startDate, Frequency frequency) {
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
     * Returns the next occurrence of a repeating task.
     *
     * @param currentTask The i-th task
     * @return The i+1 task
     */
    private static Task prepareNextTask(Task currentTask, Frequency frequency) {
        LocalDateTime newStartDate = incrementDate(currentTask.getDoOnStartDateTime(),
                frequency);
        LocalDateTime newEndDate = incrementDate(currentTask.getDoOnEndDateTime(),
                frequency);
        LocalDateTime byDate = currentTask.getByDateTime();
        if (byDate != null) {
            byDate = incrementDate(byDate, frequency);
        }
        return new Task(currentTask.getIdentifier(), currentTask.getDescription(), byDate,
                newStartDate, newEndDate);
    }

    private boolean isOnSameDay(LocalDateTime firstDate, LocalDateTime secondDate) {
        return firstDate.toLocalDate().equals(secondDate.toLocalDate());
    }

    private boolean isEndTimeAfterCurrentStartTime(Task currentTask, LocalDateTime doOnStartDateTime,
                                                   LocalDateTime doOnEndDateTime) {
        return doOnEndDateTime.isAfter(currentTask.getDoOnStartDateTime())
                && (doOnStartDateTime.isBefore(currentTask.getDoOnStartDateTime())
                || doOnStartDateTime.equals(currentTask.getDoOnStartDateTime()));
    }

    private boolean isStartTimeBeforeCurrentEndTime(Task currentTask, LocalDateTime doOnStartDateTime,
                                                    LocalDateTime doOnEndDateTime) {
        return doOnStartDateTime.isBefore(currentTask.getDoOnEndDateTime())
                && (doOnEndDateTime.isAfter(currentTask.getDoOnEndDateTime())
                || doOnEndDateTime.equals(currentTask.getDoOnEndDateTime()));
    }

    private boolean isStartAndEndTimeWithinCurrentTime(Task currentTask, LocalDateTime doOnStartDateTime,
                                                       LocalDateTime doOnEndDateTime) {
        return doOnStartDateTime.isAfter(currentTask.getDoOnStartDateTime())
                && doOnEndDateTime.isBefore(currentTask.getDoOnEndDateTime());
    }

    private boolean isStartAndEndTimeContainCurrentTime(Task currentTask, LocalDateTime doOnStartDateTime,
                                                        LocalDateTime doOnEndDateTime) {
        return currentTask.getDoOnStartDateTime().isAfter(doOnStartDateTime)
                && currentTask.getDoOnEndDateTime().isBefore(doOnEndDateTime);
    }

    private boolean isStartAndEndTimeEqualsCurrentTime(Task currentTask, LocalDateTime doOnStartDateTime,
                                                       LocalDateTime doOnEndDateTime) {
        return doOnStartDateTime.isEqual(currentTask.getDoOnStartDateTime())
                && doOnEndDateTime.equals(currentTask.getDoOnEndDateTime());
    }

    private boolean hasTimeClash(Task currentTask, LocalDateTime doOnStartDateTime,
                                 LocalDateTime doOnEndDateTime) {
        return isStartAndEndTimeEqualsCurrentTime(currentTask, doOnStartDateTime, doOnEndDateTime)
                || isEndTimeAfterCurrentStartTime(currentTask, doOnStartDateTime, doOnEndDateTime)
                || isStartTimeBeforeCurrentEndTime(currentTask, doOnStartDateTime, doOnEndDateTime)
                || isStartAndEndTimeWithinCurrentTime(currentTask, doOnStartDateTime, doOnEndDateTime)
                || isStartAndEndTimeContainCurrentTime(currentTask, doOnStartDateTime, doOnEndDateTime);
    }

    private boolean isStartTimeClashWithEndTime(Task taskToCheck) {
        return taskToCheck.getDoOnStartDateTime().isAfter(taskToCheck.getDoOnEndDateTime())
                || taskToCheck.getDoOnStartDateTime().equals(taskToCheck.getDoOnEndDateTime());
    }

    private boolean isByDateBeforeDoOnDate(Task taskToCheck) {
        if (taskToCheck.getByDateTime() == null) {
            return false;
        }
        return taskToCheck.getByDateTime().toLocalDate()
                .isBefore(taskToCheck.getDoOnStartDateTime().toLocalDate());
    }

    /**
     * Checks if there is any date and time clashes
     * for a given array.
     *
     * @param taskList    Array representation of tasks.
     * @param taskToCheck New Task to be checked for clash.
     * @throws TimeClashException If there is a date and time clash, i.e.
     *                            taskToCheck has the same date and clashing of time periods
     *                            with tasks in taskList
     */
    public void checkDateTimeClash(ArrayList<Task> taskList, Task taskToCheck)
            throws TimeClashException, InvalidInputException {
        if (isStartTimeClashWithEndTime(taskToCheck)) {
            throw new InvalidInputException(ERROR_START_AFTER_END_TIME_MESSAGE);
        }
        if (!taskToCheck.isDone() && taskToCheck.getDoOnStartDateTime().isBefore(LocalDateTime.now())) {
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

    /**
     * Adds a new task to the current array of tasks.
     *
     * @param newTask The new task to be added to the array.
     */
    public void addTask(Task newTask, Frequency frequency) throws InvalidInputException, TimeClashException {
        LocalDateTime lastRecurrenceDate = getEndDateForRecurrence(newTask.getDoOnStartDateTime(),
                frequency);
        ArrayList<Task> taskListToAdd = new ArrayList<>();
        do {
            checkDateTimeClash(tasks, newTask);
            taskListToAdd.add(newTask);
            newTask = prepareNextTask(newTask, frequency);
        } while (newTask.getDoOnStartDateTime().isBefore(lastRecurrenceDate));
        tasks.addAll(taskListToAdd);
        updateIndex();
    }

    /**
     * Updates the attribute of a task with the new values.
     *
     * @param taskToUpdate    The task to be edited
     * @param taskDescription The new task description
     * @param startDateOffset The offset between the new and old start date and time of the task
     * @param endDateOffset   The offset between the new and old end date and time of the task
     * @param byDateOffset    The offset between the new and old by date and time of the task
     */
    private void updateTask(Task taskToUpdate, String taskDescription,
                            long startDateOffset,
                            long endDateOffset,
                            long byDateOffset) {
        if (!taskDescription.isBlank()) {
            taskToUpdate.setTaskDescription(taskDescription);
        }
        if (startDateOffset != 0) {
            taskToUpdate.setDoOnStartDateTime(taskToUpdate
                    .getDoOnStartDateTime()
                    .plusSeconds(startDateOffset));
        }
        if (endDateOffset != 0) {
            taskToUpdate.setDoOnEndDateTime(taskToUpdate
                    .getDoOnEndDateTime()
                    .plusSeconds(endDateOffset));
        }
        if (byDateOffset != 0) {
            taskToUpdate.setByDateTime(taskToUpdate
                    .getDoOnStartDateTime()
                    .plusSeconds(byDateOffset));
        }
    }

    private long calculateOffsetOfDate(LocalDateTime oldDateTime, LocalDateTime newDateTime) {
        if (oldDateTime != null && newDateTime != null) {
            return oldDateTime.until(newDateTime, ChronoUnit.SECONDS);
        }
        return 0;
    }

    /**
     * Edits a task in the task list with the updated details.
     *
     * @param editIndex         The task specified by the user
     * @param taskDescription   The new task description
     * @param doOnStartDateTime The new start date and time of the task
     * @param doOnEndDateTime   The new end date and time of the task
     * @param byDateTime        The new by date and time of the task
     * @throws TimeClashException    if the new do date has is clashing with other tasks
     * @throws InvalidInputException if the dates are invalid e.g. start date after end date
     */
    public void editSingleTaskContent(int editIndex, String taskDescription,
                                      LocalDateTime doOnStartDateTime,
                                      LocalDateTime doOnEndDateTime,
                                      LocalDateTime byDateTime) throws TimeClashException, InvalidInputException {

        Task taskToEdit = tasks.get(editIndex);
        ArrayList<Task> editedList = new ArrayList<>(tasks);
        editedList.remove(editIndex);

        long startDateOffset = calculateOffsetOfDate(taskToEdit.getDoOnStartDateTime(), doOnStartDateTime);
        long endDateOffset = calculateOffsetOfDate(taskToEdit.getDoOnEndDateTime(), doOnEndDateTime);

        Task updatedTask = taskToEdit.clone();
        updateTask(updatedTask, taskDescription,
                startDateOffset, endDateOffset, 0);
        updatedTask.setIdentifier(generateIdentifier());

        if (byDateTime != null) {
            updatedTask.setByDateTime(byDateTime);
        }

        checkDateTimeClash(editedList, updatedTask);

        tasks.remove(editIndex);
        tasks.add(updatedTask);
        updateIndex();
    }

    /**
     * Edits a task and its future occurrence with the updated details.
     *
     * @param editIndex         The task specified by the user
     * @param taskDescription   The new task description
     * @param doOnStartDateTime The new start date and time of the task
     * @param doOnEndDateTime   The new end date and time of the task
     * @param byDateTime        The new by date and time of the task
     * @throws TimeClashException    if any one of the occurrence with the new date has a time clash with other tasks
     * @throws InvalidInputException if any of the dates are invalid e.g. start date after end date
     */
    public void editRepeatedTasks(int editIndex, String taskDescription,
                                  LocalDateTime doOnStartDateTime,
                                  LocalDateTime doOnEndDateTime,
                                  LocalDateTime byDateTime) throws TimeClashException, InvalidInputException {
        Task firstTask = getTask(editIndex);
        ArrayList<Task> affectedTasks = getAffectedTasks(editIndex);
        ArrayList<Task> editedList = new ArrayList<>(tasks);
        editedList.removeAll(affectedTasks);

        long startDateOffset = calculateOffsetOfDate(firstTask.getDoOnStartDateTime(), doOnStartDateTime);
        long endDateOffset = calculateOffsetOfDate(firstTask.getDoOnEndDateTime(), doOnEndDateTime);
        long byDateOffset = calculateOffsetOfDate(doOnStartDateTime, byDateTime);

        int newIdentifier = generateIdentifier();
        for (Task t : affectedTasks) {
            Task updatedTask = t.clone();
            updateTask(updatedTask, taskDescription,
                    startDateOffset, endDateOffset, byDateOffset);
            updatedTask.setIdentifier(newIdentifier);
            checkDateTimeClash(editedList, updatedTask);
            editedList.add(updatedTask);
        }

        tasks = editedList;
        updateIndex();
    }

    /**
     * Returns an ArrayList of tasks that are going to be edited.
     *
     * @param index The index of the task specified by the user
     * @return ArrayList of task that will be edited
     */
    public ArrayList<Task> getAffectedTasks(int index) {
        Task taskToEdit = tasks.get(index);
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getIdentifier() == taskToEdit.getIdentifier() && t.getIndex() >= taskToEdit.getIndex()) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * Returns a string listing all tasks in the list.
     *
     */
    public String getAllTasksInString() {
        StringBuilder result = new StringBuilder();
        for (Task task : tasks) {
            result.append("\t");
            result.append(task);
            result.append("\n");
        }
        return result.toString().stripTrailing();
    }

    /**
     * Marks a task given the index of the task.
     * Index corresponds to its placement within the task array.
     *
     * @param markIndex Index of the task to mark as done.
     */
    public void markTask(int markIndex) {
        tasks.get(markIndex).markAsDone();
    }

    /**
     * Unmarks a task given the index of the task.
     * Index corresponds to its placement within the task array.
     *
     * @param markIndex Index of the task to mark as undone.
     */
    public void unmarkTask(int markIndex) {
        tasks.get(markIndex).markAsUndone();
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Deletes a task given its index. Index corresponds to its placement
     * in task array.
     *
     * @param deleteIndex Index of a task to search for.
     */
    public void removeTask(int deleteIndex, boolean isRepeat) {
        Task taskToBeRemoved = tasks.get(deleteIndex);
        if (!isRepeat) {
            tasks.remove(deleteIndex);
        } else {
            int identifier = taskToBeRemoved.getIdentifier();
            tasks.removeIf(task -> task.getIdentifier() == identifier && task.getIndex() >= taskToBeRemoved.getIndex());
        }
        updateIndex();
    }

    /**
     * Deletes all tasks saved within the task array.
     */
    public void deleteAllTasks() {
        tasks.clear();
        identifierList.clear();
    }

    public int generateIdentifier() {
        Random generator = new Random();
        int candidate;
        do {
            candidate = generator.nextInt(65536);
        } while (identifierList.contains(candidate));
        return candidate;
    }

    private void updateIndex() {
        tasks.sort(new TaskDateComparator());
        identifierList.clear();
        int i = 1;
        for (Task task : tasks) {
            task.setIndex(i);
            identifierList.add(task.getIdentifier());
            i++;
        }
    }

    /**
     * Returns a filtered ArrayList of task according to the date specified.
     *
     * @param dateInput The specific date.
     * @return The filtered ArrayList.
     */
    public ArrayList<Task> getFilteredTasksByDate(LocalDate dateInput) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDoOnStartDateTime().toLocalDate().isEqual(dateInput)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    private boolean hasDoOnDate(Task task) {
        return task.getDoOnStartDateTime() != null;
    }

    /**
     * Returns
     * Printed tasks applies to non-recurring tasks.
     *
     */
    public String getPendingTasks() {
        StringBuilder result = new StringBuilder();
        for (Task task : tasks) {
            if (!task.isDone()) {
                result.append(TAB_INDENT);
                result.append(task);
                result.append(NEWLINE);
            }
        }
        return result.toString().stripTrailing();
    }

    public int getPendingTasksCount() {
        int count = 0;
        for (Task task : tasks) {
            if (!task.isDone()) {
                count++;
            }
        }
        return count;
    }

    public ArrayList<Task> getFilteredTasksByMonth(LocalDate firstDayOfMonth) {
        LocalDate firstDayOfNextMonth = firstDayOfMonth.plusMonths(1);
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (hasDoOnDate(task) && task.getDoOnStartDateTime().toLocalDate().isEqual(firstDayOfMonth)) {
                filteredTasks.add(task);
            } else if (hasDoOnDate(task)
                    && (task.getDoOnStartDateTime().toLocalDate().isAfter(firstDayOfMonth)
                    && task.getDoOnStartDateTime().toLocalDate().isBefore(firstDayOfNextMonth))) {
                filteredTasks.add(task);
            }
        }
        return (ArrayList<Task>) filteredTasks.stream()
                .sorted(Comparator.comparing(Task::getDoOnStartDateTime))
                .collect(Collectors.toList());
    }

    public void printTaskList(ArrayList<Task> taskList, Ui ui) {
        for (Task task : taskList) {
            ui.showToUser(task.toString());
        }
    }
}
