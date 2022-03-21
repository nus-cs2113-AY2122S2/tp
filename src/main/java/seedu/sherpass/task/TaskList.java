package seedu.sherpass.task;

import seedu.sherpass.util.Ui;

import java.time.LocalDate;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a constructor for the class TaskList.
     *
     * @param savedTasks Representation of an array of tasks.
     */
    public TaskList(ArrayList<Task> savedTasks) {
        tasks = savedTasks;
    }

    public TaskList() {
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
     * @param taskDescription Description of the task.
     * @param taskByDate      Due date of the task
     * @param taskDoOnDate    Date to work on the task
     */
    public void addTask(String taskDescription, LocalDate taskByDate, LocalDate taskDoOnDate) {
        Task newTask = new Task(taskDescription, taskByDate, taskDoOnDate);
        tasks.add(newTask);
        System.out.println("Got it. I've added this task:\n  " + newTask
                + "\nNow you have " + tasks.size() + " task(s) in the list.");
    }


    /**
     * Prints all available tasks in the task list.
     */
    public void printAllTasks() {
        if (!tasks.isEmpty()) {
            if (tasks.size() > 1) {
                System.out.println("Here are the " + tasks.size() + " tasks in your list:");
            } else {
                System.out.println("Here is the 1 task in your list:");
            }
            int printIndex = 1;
            for (Task task : tasks) {
                System.out.println(printIndex + ". " + task);
                printIndex++;
            }
        } else {
            System.out.println("There are no tasks in your list.");
        }
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
        System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(markIndex));
    }


    /**
     * Unmarks a task given the index of the task.
     * Index corresponds to its placement within the task array.
     *
     * @param markIndex Index of the task to mark as undone.
     */
    public void unmarkTask(int markIndex) {
        tasks.get(markIndex).markAsUndone();
        System.out.println("Ok, I've marked this task as"
                + " not done yet:\n  " + tasks.get(markIndex));
    }


    /**
     * Returns a boolean value to denote if the task has already
     * been added to the task array. References task description
     * when checking against each task.
     *
     * @param taskDescription Description of the task to match when searching for task.
     * @return Returns true if task has been added. False otherwise.
     */
    public boolean isTaskAlreadyAdded(String taskDescription) {
        for (Task task : tasks) {
            if (task.getDescription().equalsIgnoreCase(taskDescription)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a boolean value denoting the existence of a task
     * within the task array.
     *
     * @param taskIndex Index of a task. Corresponds to its placement in task array.
     * @return Returns true if task exists in task array. False otherwise.
     */
    public boolean isTaskExist(int taskIndex) {
        return tasks.get(taskIndex) != null;
    }

    /**
     * Deletes a task given its index. Index corresponds to its placement
     * in task array.
     *
     * @param deleteIndex Index of a task to search for.
     */
    public void removeTask(int deleteIndex) {
        Task taskToBeRemoved = tasks.get(deleteIndex);
        tasks.remove(deleteIndex);
        System.out.println("Okay. I've removed this task:\n  " + taskToBeRemoved
                + "\nNow you have " + tasks.size() + " task(s) in the list.");
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
}
