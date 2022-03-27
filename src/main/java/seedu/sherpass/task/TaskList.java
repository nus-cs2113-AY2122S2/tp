package seedu.sherpass.task;

import seedu.sherpass.enums.Frequency;
import seedu.sherpass.util.Ui;
import seedu.sherpass.util.parser.TaskParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;


import static seedu.sherpass.constant.DateAndTimeFormat.outputDateOnlyFormat;
import static seedu.sherpass.constant.Index.FIRST_REPEATED_TASK_INDEX;
import static seedu.sherpass.constant.Message.EMPTY_STRING;
import static seedu.sherpass.constant.Message.ERROR_SCHEDULE_CLASH_MESSAGE;


public class TaskList {
    private ArrayList<Task> tasks;
    private HashSet<Integer> identifierList;

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
        if (frequency == Frequency.DAILY) {
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
        if (frequency == Frequency.DAILY) {
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
                newStartDate, newEndDate, currentTask.getRepeatFrequency(), 0);
    }


    private boolean hasDateTimeClash(ArrayList<Task> targetList,
                                     LocalDateTime doOnStartDateTime,
                                     LocalDateTime doOnEndDateTime) {
        for (Task task : targetList) {
            boolean isOnSameDay = (task.getDoOnDateString(true)
                    .equals(doOnStartDateTime.format(outputDateOnlyFormat)));
            boolean hasTimeClash = (doOnStartDateTime.isEqual(task.getDoOnStartDateTime())
                    || (doOnEndDateTime.isAfter(task.getDoOnStartDateTime())
                    && doOnStartDateTime.isBefore(task.getDoOnStartDateTime()))
                    || (doOnStartDateTime.isBefore(task.getDoOnEndDateTime())
                    && doOnEndDateTime.isAfter(task.getDoOnEndDateTime()))
                    || (doOnStartDateTime.isAfter(task.getDoOnStartDateTime())
                    && doOnEndDateTime.isBefore(task.getDoOnEndDateTime())))
                    || (task.getDoOnStartDateTime().isAfter(doOnStartDateTime)
                    && task.getDoOnEndDateTime().isBefore(doOnEndDateTime));
            if (isOnSameDay && hasTimeClash) {
                return true;
            }
        }
        return false;
    }


    private void updateIndex() {
        int i = 1;
        for (Task task : tasks) {
            task.setIndex(i);
            i++;
        }
    }

    /**
     * Adds a new task to the current array of tasks.
     *
     * @param newTask  The new task to be added to the array.
     * @param isRepeat Checks if task repeats.
     * @param ui       User Interface.
     */
    public void addTask(Task newTask, boolean isRepeat, Ui ui) {
        String repeatKeyWord = EMPTY_STRING;
        if (hasDateTimeClash(tasks, newTask.getDoOnStartDateTime(), newTask.getDoOnEndDateTime())) {
            ui.showToUser(ERROR_SCHEDULE_CLASH_MESSAGE);
            return;
        }
        if (!isRepeat) {
            tasks.add(newTask);
        } else {
            repeatKeyWord = "repeated";
            identifierList.add(newTask.getIdentifier());
            LocalDateTime lastRecurrenceDate = getEndDateForRecurrence(newTask.getDoOnStartDateTime(),
                    newTask.getRepeatFrequency());
            ArrayList<Task> taskListToAdd = new ArrayList<>();
            Task tempTask = newTask;
            do {
                if (hasDateTimeClash(tasks, tempTask.getDoOnStartDateTime(), tempTask.getDoOnEndDateTime())) {
                    ui.showToUser(ERROR_SCHEDULE_CLASH_MESSAGE);
                    return;
                }
                taskListToAdd.add(tempTask);
                tempTask = prepareNextTask(tempTask);
            } while (tempTask.getDoOnStartDateTime().isBefore(lastRecurrenceDate));
            tasks.addAll(taskListToAdd);
        }
        tasks.sort(new TaskDateComparator());
        updateIndex();
        ui.showToUser("Got it! I've added this " + repeatKeyWord + " task:\n   "
                + newTask + "\n"
                + "Now you have " + tasks.size() + " task(s) in your schedule!");
    }


    private void editSingleTaskContent(Task taskToEdit, String taskDescription,
                                       LocalDateTime currentDoOnStartDateTime,
                                       LocalDateTime currentDoOnEndDateTime,
                                       LocalDateTime byDate, Ui ui) {
        if (!taskDescription.isBlank()) {
            taskToEdit.setTaskDescription(taskDescription);
        }
        tasks.remove(taskToEdit);
        if (byDate != null) {
            taskToEdit.setByDate(byDate);
        }
        taskToEdit.setDoOnStartDateTime(currentDoOnStartDateTime);
        taskToEdit.setDoOnEndDateTime(currentDoOnEndDateTime);
        if (hasDateTimeClash(tasks, currentDoOnStartDateTime, currentDoOnEndDateTime)) {
            ui.showToUser(ERROR_SCHEDULE_CLASH_MESSAGE);
            return;
        }
        tasks.add(taskToEdit);
        ui.showToUser("Okay! I've edited this task as such:\n"
                + "\t" + taskToEdit);
    }

    private ArrayList<Task> createCopy() {
        ArrayList<Task> copyArray = new ArrayList<>();
        for (Task t : tasks) {
            copyArray.add(new Task(t));
        }
        return copyArray;
    }

    private void deleteCurrentDateTimeTasks(ArrayList<Task> copyList, int identifier) {
        copyList.removeIf(t -> t.getIdentifier() == identifier);
    }

    private ArrayList<Task> filterByIdentifier(int identifier,
                                               String taskDescription,
                                               LocalDateTime currentDoOnDateStartTime,
                                               LocalDateTime currentDoOnDateEndTime) {
        ArrayList<Task> newDateTimeArray = new ArrayList<>();
        for (Task task : tasks) {
            if (identifier == task.getIdentifier()) {
                Task copyTask = new Task(task);
                if (!taskDescription.isBlank()) {
                    task.setTaskDescription(taskDescription);
                }
                copyTask.setDoOnStartDateTime(currentDoOnDateStartTime);
                copyTask.setDoOnEndDateTime(currentDoOnDateEndTime);
                newDateTimeArray.add(copyTask);
                currentDoOnDateStartTime = incrementDate(currentDoOnDateStartTime, task.getRepeatFrequency());
                currentDoOnDateEndTime = incrementDate(currentDoOnDateEndTime, task.getRepeatFrequency());
            }
        }
        return newDateTimeArray;
    }

    private boolean hasRepeatedTasksTimetableClash(ArrayList<Task> copyList,
                                                   ArrayList<Task> filteredList) {
        for (Task filteredTask : filteredList) {
            if (hasDateTimeClash(copyList, filteredTask.getDoOnStartDateTime(),
                    filteredTask.getDoOnEndDateTime())) {
                return true;
            }
        }
        return false;
    }

    private void editRepeatedTasks(Task taskToEdit, String taskDescription,
                                   LocalDateTime currentDoOnDateStartTime,
                                   LocalDateTime currentDoOnDateEndTime,
                                   Ui ui) {
        int identifier = taskToEdit.getIdentifier();

        ArrayList<Task> copyList = createCopy();
        deleteCurrentDateTimeTasks(copyList, taskToEdit.getIdentifier());

        ArrayList<Task> newDateTimeArray = filterByIdentifier(identifier,
                taskDescription, currentDoOnDateStartTime, currentDoOnDateEndTime);

        if (hasRepeatedTasksTimetableClash(copyList, newDateTimeArray)) {
            ui.showToUser(ERROR_SCHEDULE_CLASH_MESSAGE);
            return;
        }

        Task tempTask = new Task(newDateTimeArray.get(FIRST_REPEATED_TASK_INDEX));
        copyList.addAll(newDateTimeArray);
        tasks = copyList;
        ui.showToUser("Okay! I've edited this repeated task as such:\n"
                + "\t" + tempTask);
    }


    /**
     * Edit the task content.
     *
     * @param ui                       User interface.
     * @param taskToEdit               The task being edited.
     * @param taskDescription          The new task description for replacement.
     * @param currentDoOnStartDateTime New do on date and start time to replace.
     * @param currentDoOnEndDateTime   New do on date and end time to replace.
     * @param byDate                   New by date to replace.
     */
    public void editTask(Ui ui, Task taskToEdit, String taskDescription,
                         LocalDateTime currentDoOnStartDateTime,
                         LocalDateTime currentDoOnEndDateTime, LocalDateTime byDate) {
        if (TaskParser.isValidFreq(taskToEdit.getRepeatFrequency())) {
            assert (byDate == null);
            editRepeatedTasks(taskToEdit, taskDescription, currentDoOnStartDateTime, currentDoOnEndDateTime, ui);
        } else {
            editSingleTaskContent(taskToEdit, taskDescription,
                    currentDoOnStartDateTime, currentDoOnEndDateTime, byDate, ui);
        }
        tasks.sort(new TaskDateComparator());
        updateIndex();
    }

    /**
     * Prints all available tasks in the task list.
     *
     * @param ui Ui class for printing of messages.
     */
    public void printAllTasks(Ui ui) {
        int printIndex = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            ui.showToUser(printIndex + ". " + task);
            printIndex++;
        }
        ui.showLine();
        ui.showToUser("You have " + (printIndex - 1) + " task(s) in your list.");
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
     * @param ui        User Interface.
     */
    public void markTask(int markIndex, Ui ui) {
        tasks.get(markIndex).markAsDone();
        ui.showToUser("Nice! I've marked this task as done:\n  " + tasks.get(markIndex));
    }


    /**
     * Unmarks a task given the index of the task.
     * Index corresponds to its placement within the task array.
     *
     * @param markIndex Index of the task to mark as undone.
     * @param ui        User Interface.
     */
    public void unmarkTask(int markIndex, Ui ui) {
        tasks.get(markIndex).markAsUndone();
        ui.showToUser("Ok, I've marked this task as" + " not done yet:\n  " + tasks.get(markIndex));
    }

    /**
     * Returns a boolean value denoting the existence of a task
     * within the task array.
     *
     * @param index Index of a task. Corresponds to its placement in task array.
     * @return Returns true if task exists in task array. False otherwise.
     */
    public boolean isTaskNotExist(int index) {
        return index < 0 || index >= tasks.size();
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
     * @param ui          User Interface
     */
    public void removeTask(int deleteIndex, Ui ui) {
        Task taskToBeRemoved = tasks.get(deleteIndex);
        String repeatKeyWord = "";
        if (!TaskParser.isValidFreq(taskToBeRemoved.getRepeatFrequency())) {
            tasks.remove(deleteIndex);
        } else {
            repeatKeyWord = "repeated";
            int identifier = taskToBeRemoved.getIdentifier();
            tasks.removeIf(task -> task.getIdentifier() == identifier);
        }
        updateIndex();
        ui.showToUser("Okay. I've removed this " + repeatKeyWord + " task:\n  "
                + taskToBeRemoved + "\nNow you have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Deletes all tasks saved within the task array.
     *
     * @param ui Ui for printing the completion of the deletion.
     */
    public void deleteAllTasks(Ui ui) {
        while (tasks.size() > 0) {
            tasks.remove(0);
        }
        ui.showLine();
        ui.showToUser("Done! Now you have " + tasks.size() + " task(s) in the list.");
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

    /**
     * Return a filtered ArrayList of task according to the date specified.
     *
     * @param dateInput The specific date.
     * @return The filtered ArrayList.
     */
    public ArrayList<Task> getFilteredTasksByDate(LocalDate dateInput) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (hasDoOnDate(task) && task.getDoOnStartDateTime().toLocalDate().isEqual(dateInput)) {
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
        int printIndex = 1;
        for (Task task : tasks) {
            if (!task.isDone()) {
                ui.showToUser(printIndex + ". " + task);
                printIndex++;
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


}
