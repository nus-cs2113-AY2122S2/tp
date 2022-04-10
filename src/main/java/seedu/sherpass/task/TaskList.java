package seedu.sherpass.task;

import seedu.sherpass.enums.Frequency;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.exception.TimeClashException;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.Collectors;

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
     * Adds a new task to the current array of tasks.
     *
     * @param newTask The new task to be added to the array.
     */
    public void addTask(Task newTask, Frequency frequency, boolean isFromFile) throws InvalidInputException,
            TimeClashException {
        LocalDateTime lastRecurrenceDate = TaskUtil.getEndDateForRecurrence(newTask.getDoOnStartDateTime(),
                frequency);
        ArrayList<Task> taskListToAdd = new ArrayList<>();
        Task currentTask = newTask;
        do {
            TaskUtil.checkDateTimeClash(tasks, currentTask, isFromFile, false);
            taskListToAdd.add(currentTask);
            currentTask = TaskUtil.prepareNextTask(currentTask, frequency);
        } while (currentTask.getDoOnStartDateTime().isBefore(lastRecurrenceDate));
        tasks.addAll(taskListToAdd);
        updateIndex();
    }

    /**
     * Edits a task in the task list with the updated details.
     *
     * @param editIndex         The task specified by the user
     * @param taskDescription   The new task description
     * @param doOnStartDateTime The new start date and time of the task
     * @param doOnEndDateTime   The new end date and time of the task
     * @param byDateTime        The new by date and time of the task
     * @return The edited task specified by editIndex
     * @throws TimeClashException    if the new do date has is clashing with other tasks
     * @throws InvalidInputException if the dates are invalid e.g. start date after end date
     */
    public Task editSingleTask(int editIndex, String taskDescription,
                               LocalDateTime doOnStartDateTime,
                               LocalDateTime doOnEndDateTime,
                               LocalDateTime byDateTime,
                               boolean isEditByOnly) throws TimeClashException, InvalidInputException {

        Task taskToEdit = tasks.get(editIndex);
        ArrayList<Task> tempList = new ArrayList<>(tasks);
        tempList.remove(editIndex);

        long startDateOffset = TaskUtil.calculateOffsetOfDate(taskToEdit.getDoOnStartDateTime(), doOnStartDateTime);
        long endDateOffset = TaskUtil.calculateOffsetOfDate(taskToEdit.getDoOnEndDateTime(), doOnEndDateTime);
        long byDateOffset = TaskUtil.calculateOffsetOfDate(doOnStartDateTime, byDateTime);

        Task updatedTask = taskToEdit.copy();
        updatedTask.editTask(generateIdentifier(), taskDescription,
                startDateOffset, endDateOffset, byDateOffset);

        TaskUtil.checkDateTimeClash(tempList, updatedTask, false, isEditByOnly);

        tasks.remove(editIndex);
        tasks.add(updatedTask);
        updateIndex();
        return updatedTask;
    }

    /**
     * Edits a task and its future occurrence with the updated details.
     *
     * @param editIndex         The task specified by the user
     * @param taskDescription   The new task description
     * @param doOnStartDateTime The new start date and time of the task
     * @param doOnEndDateTime   The new end date and time of the task
     * @param byDateTime        The new by date and time of the task
     * @return The edited task specified by editIndex
     * @throws TimeClashException    if any one of the occurrence with the new date has a time clash with other tasks
     * @throws InvalidInputException if any of the dates are invalid e.g. start date after end date
     */
    public Task editRepeatedTasks(int editIndex, String taskDescription,
                                  LocalDateTime doOnStartDateTime,
                                  LocalDateTime doOnEndDateTime,
                                  LocalDateTime byDateTime,
                                  boolean isEditByOnly) throws TimeClashException, InvalidInputException {
        Task firstTask = getTask(editIndex);
        ArrayList<Task> affectedTasks = getAffectedTasks(editIndex);
        ArrayList<Task> tempList = new ArrayList<>(tasks);
        ArrayList<Task> editedTasks = new ArrayList<>();
        assert (affectedTasks.size() > 0);
        tempList.removeAll(affectedTasks);

        long startDateOffset = TaskUtil.calculateOffsetOfDate(firstTask.getDoOnStartDateTime(), doOnStartDateTime);
        long endDateOffset = TaskUtil.calculateOffsetOfDate(firstTask.getDoOnEndDateTime(), doOnEndDateTime);
        long byDateOffset = TaskUtil.calculateOffsetOfDate(doOnStartDateTime, byDateTime);

        int newIdentifier = generateIdentifier();
        for (Task t : affectedTasks) {
            Task updatedTask = t.copy();
            updatedTask.editTask(newIdentifier, taskDescription,
                    startDateOffset, endDateOffset, byDateOffset);
            updatedTask.setIdentifier(newIdentifier);
            TaskUtil.checkDateTimeClash(tempList, updatedTask, false, isEditByOnly);
            editedTasks.add(updatedTask);
            tempList.add(updatedTask);
        }

        tasks = tempList;
        updateIndex();
        return editedTasks.get(0);
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
     * Returns an array of tasks that have not been marked as complete.
     *
     */
    public ArrayList<Task> getPendingTasks() {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (!task.isDone()) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
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
    
}
