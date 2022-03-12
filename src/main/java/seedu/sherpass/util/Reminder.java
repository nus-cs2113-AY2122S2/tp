package seedu.sherpass.util;

import seedu.sherpass.constant.DateAndTimeFormat;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Comparator;

import static java.util.stream.Collectors.toList;

public class Reminder {
    private Ui ui;
    private ArrayList<Task> tasks;
    private LocalDate currentDate = LocalDate.now();

    /**
     * Create a constructor for the class Reminder.
     *
     * @param taskList Representation of an array of tasks.
     */
    public Reminder(TaskList taskList, Ui ui) {
        tasks = taskList.getTasks();
        this.ui = ui;
    }

    /**
     * Prints reminders to the user that
     * the task is due soon or
     * tasks that need to be done on the day.
     */
    public void showReminders() {
        showDailyTask();
        showWeeklyTask();
    }

    /**
     * Prints tasks that need to be done today
     * with reference to user local machine date.
     */
    public void showDailyTask() {
        ArrayList<Task> filteredDailyTasks = (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getDate().equals(currentDate))
                .sorted(Comparator.comparing(Task::getDate))
                .collect(toList());

        if (filteredDailyTasks.size() != 0) {
            System.out.println("Tasks to do today:");
            for (Task task : filteredDailyTasks) {
                System.out.println(task.toString());
            }
            ui.showLine();
        }
    }

    /**
     * Prints tasks that is due in the current week
     * with reference to user local machine date.
     */

    public void showWeeklyTask() {
        LocalDate nextWeekDate = currentDate.plusDays(7);

        ArrayList<Task> filteredThisWeekTasks = (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getDate().compareTo(nextWeekDate.format(DateAndTimeFormat.noTimeFormat)) < 0)
                .sorted(Comparator.comparing(Task::getDate))
                .collect(toList());

        if (filteredThisWeekTasks.size() != 0) {
            System.out.println("Tasks to be done within the week:");
            for (Task task : filteredThisWeekTasks) {
                System.out.println(task.toString());
            }
            ui.showLine();
        }
    }
}
