package seedu.sherpass.task;

import seedu.sherpass.util.Ui;

import java.util.ArrayList;

import static seedu.sherpass.constant.Message.ERROR_SYSTEM_FAULT_MESSAGE;

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
        tasks = new ArrayList<Task>();
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
     * @param taskDate        Date of the task
     * @param taskType        Task type, i.e. todo, deadline or event.
     */
    public void addTask(String taskDescription, String taskDate, String taskType) {
        Task newTask;
        switch (taskType) {
        case "todo":
            newTask = new Todo(taskDescription);
            break;
        case "deadline":
            newTask = new Deadline(taskDescription, taskDate);
            break;
        case "event":
            newTask = new Event(taskDescription, taskDate);
            break;
        default:
            System.out.println(ERROR_SYSTEM_FAULT_MESSAGE);
            return;
        }

        tasks.add(newTask);
        System.out.println("Got it. I've added this task:\n  " + newTask
                + "\nNow you have " + tasks.size() + " task(s) in the list.");
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
            System.out.println(printIndex + ". " + task);
            printIndex++;
        }
        ui.showLine();
        System.out.println("A total of " + (printIndex - 1) + " item(s) have been found!");
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
     * Returns the index of a task given its task description.
     *
     * @param taskDescriptionToSearch Description of the task to search for.
     * @return Index of a task, which corresponds to its placement in the task array.
     */
    public int findIndexToReplace(String taskDescriptionToSearch) {
        int index = -1;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription()
                    .equalsIgnoreCase(taskDescriptionToSearch.trim())) {
                index = i;
                break;
            }
        }
        return index;
    }


    /**
     * Replaces the current date of the task with a new date input.
     *
     * @param taskIndexToReplace Index of a task to search for the task.
     * @param newTaskDate        New date input to replace the old task date.
     * @param ui                 Ui for printing partition lines and messages.
     */
    public void replaceTaskDate(int taskIndexToReplace, String newTaskDate, Ui ui) {
        tasks.get(taskIndexToReplace).resetInput(newTaskDate);
        ui.showLine();
        ui.showToUser("Done! I've updated this task:\n  " + tasks.get(taskIndexToReplace));
    }


    /**
     * Returns a boolean value denoting the existence of a task
     * within the task array.
     *
     * @param deleteIndex Index of a task. Corresponds to its placement in task array.
     * @return Returns true if task exists in task array. False otherwise.
     */
    public boolean isTaskExist(int deleteIndex) {
        return tasks.get(deleteIndex) != null;
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
     * Finds and prints tasks whose description matches the search keyword.
     *
     * @param findTaskByKeyword A keyword relating to the task description.
     * @param ui                Ui for printing messages.
     */
    public void findByTaskDescription(String findTaskByKeyword, Ui ui) {
        int printIndex = 1;
        ui.showToUser("Here are the matching tasks in your list:");
        for (Task task : tasks) {
            if (task.getDescription().contains(findTaskByKeyword)) {
                String listIndex = String.valueOf(printIndex);
                ui.showToUser(listIndex + ". " + task);
                printIndex++;
            }
        }
        if (printIndex == 1) {
            ui.showLine();
            ui.showToUser("Sorry! There are no tasks that match your description!");
        }
    }

    /**
     * Finds and prints tasks whose date and/or time matches the search keyword.
     *
     * @param findTaskByKeyword A keyword relating to the task date and/or time.
     * @param ui                Ui for printing messages.
     */
    public void findByTaskDate(String findTaskByKeyword, Ui ui) {
        int printIndex = 1;
        ui.showToUser("Here are the matching tasks in your list:");
        for (Task task : tasks) {
            if (!task.getType().equals("T") && task.getDate().contains(findTaskByKeyword)) {
                String listIndex = String.valueOf(printIndex);
                ui.showToUser(listIndex + ". " + task);
                printIndex++;
            }
        }
        if (printIndex == 1) {
            ui.showLine();
            ui.showToUser("Sorry! There are no tasks that match your description!");
        }
    }

    /**
     * Finds and prints tasks whose task type matches the search keyword.
     *
     * @param taskType Task type, i.e. todo, deadline or event.
     * @param ui       Ui for printing messages.
     */
    public void findByTaskType(String taskType, Ui ui) {
        int printIndex = 1;
        ui.showToUser("Here are the matching tasks in your list:");
        for (Task task : tasks) {
            if (task.getType().equals(taskType)) {
                String listIndex = String.valueOf(printIndex);
                ui.showToUser(listIndex + ". " + task);
                printIndex++;
            }
        }
        if (printIndex == 1) {
            ui.showLine();
            ui.showToUser("Sorry! There are no tasks that match your description!");
        }
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
