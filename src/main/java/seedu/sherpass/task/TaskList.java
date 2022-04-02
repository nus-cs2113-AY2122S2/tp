package seedu.sherpass.task;

import seedu.sherpass.enums.Frequency;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.exception.TimeClashException;
import seedu.sherpass.util.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.Collectors;

import static seedu.sherpass.constant.Message.ERROR_START_AFTER_END_TIME_MESSAGE;

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
        refreshIdentifier();
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

    /**
     * Checks if there is any date and time clashes
     * for a given array.
     *
     * @param taskList Array representation of tasks.
     * @param taskToCheck New Task to be checked for clash.
     * @throws TimeClashException If there is a date and time clash, i.e.
     *                            taskToCheck has the same date and clashing of time periods
     *                            with tasks in taskList
     */
    public void checkDateTimeClash(ArrayList<Task> taskList, Task taskToCheck)
            throws TimeClashException {
        for (Task task : taskList) {
            if (isOnSameDay(task.getDoOnStartDateTime(), taskToCheck.getDoOnStartDateTime())
                    && hasTimeClash(task, taskToCheck.getDoOnStartDateTime(), taskToCheck.getDoOnEndDateTime())) {
                throw new TimeClashException(task);
            }
        }
    }

    /**
     * Adds a new task to the current array of tasks.
     *
     * @param newTask The new task to be added to the array.
     */
    public void addTask(Task newTask) throws TimeClashException, InvalidInputException {
        if (newTask.getDoOnStartDateTime().isAfter(newTask.getDoOnEndDateTime())) {
            throw new InvalidInputException(ERROR_START_AFTER_END_TIME_MESSAGE);
        }
        identifierList.add(newTask.getIdentifier());
        LocalDateTime lastRecurrenceDate = getEndDateForRecurrence(newTask.getDoOnStartDateTime(),
                newTask.getRepeatFrequency());
        ArrayList<Task> taskListToAdd = new ArrayList<>();
        do {
            checkDateTimeClash(tasks, newTask);
            taskListToAdd.add(newTask);
            newTask = prepareNextTask(newTask);
        } while (newTask.getDoOnStartDateTime().isBefore(lastRecurrenceDate));
        tasks.addAll(taskListToAdd);
        updateIndex();
    }

    public Task updateTask(Task taskToUpdate, String taskDescription,
                           long startDifferenceInSeconds,
                           long endDifferenceInSeconds,
                           LocalDateTime byDate) {
        if (!taskDescription.isBlank()) {
            taskToUpdate.setTaskDescription(taskDescription);
        }
        if (byDate != null) {
            taskToUpdate.setByDate(byDate);
        }
        if (startDifferenceInSeconds != 0) {
            taskToUpdate.setDoOnStartDateTime(taskToUpdate
                    .getDoOnStartDateTime()
                    .plusSeconds(startDifferenceInSeconds));
        }
        if (endDifferenceInSeconds != 0) {
            taskToUpdate.setDoOnEndDateTime(taskToUpdate
                    .getDoOnEndDateTime()
                    .plusSeconds(endDifferenceInSeconds));
        }
        return taskToUpdate;
    }

    public void editSingleTaskContent(int editIndex, String taskDescription,
                                      long startDifferenceInSeconds,
                                      long endDifferenceInSeconds,
                                      LocalDateTime byDate) throws TimeClashException {
        Task taskToEdit = tasks.get(editIndex);
        ArrayList<Task> editedList = new ArrayList<>(tasks);
        editedList.remove(editIndex);

        int newIdentifier = generateIdentifier();
        Task updatedTask = updateTask(taskToEdit, taskDescription,
                startDifferenceInSeconds, endDifferenceInSeconds, byDate);
        updatedTask.setIdentifier(newIdentifier);
        updatedTask.setRepeatFrequency(Frequency.SINGLE);

        checkDateTimeClash(editedList, updatedTask);
        tasks.remove(editIndex);
        tasks.add(updatedTask);
        updateIndex();
    }

    public void editRepeatedTasks(int editIndex, String taskDescription,
                                  long startDifferenceInSeconds,
                                  long endDifferenceInSeconds) throws TimeClashException {
        ArrayList<Task> affectedTasks = getAffectedTasks(editIndex);
        ArrayList<Task> editedList = new ArrayList<>(tasks);
        editedList.removeAll(affectedTasks);

        int newIdentifier = generateIdentifier();
        for (Task t : affectedTasks) {
            Task newTask = updateTask(t, taskDescription,
                    startDifferenceInSeconds, endDifferenceInSeconds, null);
            newTask.setIdentifier(newIdentifier);
            checkDateTimeClash(editedList, newTask);
            editedList.add(newTask);
        }

        tasks = editedList;
        updateIndex();
    }

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
     * Prints all available tasks in the task list.
     *
     * @param ui Ui class for printing of messages.
     */
    public void printAllTasks(Ui ui) {
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            ui.showToUser(task.toString());
        }
        ui.showLine();
        ui.showToUser("You have " + tasks.size() + " task(s) in your list.");
    }


    /**
     * Returns a boolean value denoting the task status, i.e.
     * whether the task has been marked or not.
     *
     * @param markIndex Index of a task to check for its mark status.
     * @return Returns true if task has been marked. False otherwise.
     */
    public boolean isTaskDone(int markIndex) {
        return tasks.get(markIndex).isDone();
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
        } else if (taskToBeRemoved.getRepeatFrequency() != Frequency.SINGLE) {
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

    private void refreshIdentifier() {
        for (Task t : tasks) {
            identifierList.add(t.getIdentifier());
        }
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
     * Return a filtered ArrayList of task according to the date specified.
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
     * Prints tasks that are yet to be completed, i.e. marked as done.
     * Printed tasks applies to non-recurring tasks.
     *
     * @param ui User interface
     */
    public void printPendingTasks(Ui ui) {
        for (Task task : tasks) {
            if (!task.isDone()) {
                ui.showToUser(task.toString());
            }
        }
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
