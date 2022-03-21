package seedu.sherpass.util;

import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

import static java.util.stream.Collectors.toList;

public class Reminder {
    private Ui ui;
    private ArrayList<Task> tasks;
    private LocalDateTime currentDate = LocalDateTime.now();

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
                .filter((t) -> isEqualDate(t.getByDate(), currentDate))
                .filter((t) -> !t.isDone())
                .collect(toList());

        if (filteredDailyTasks.isEmpty()) {
            ui.showToUser("Your schedule is empty today.");
        } else {
            System.out.println("Schedule for today:");
            for (Task task : filteredDailyTasks) {
                System.out.println(task.toString());
            }
        }
        ui.showLine();
    }

    /**
     * Prints tasks that is due in the current week
     * with reference to user local machine date.
     */
    public void showWeeklyTask() {
        LocalDateTime nextWeekDate = currentDate.plusDays(7);

        ArrayList<Task> filteredThisWeekTasks = (ArrayList<Task>) tasks.stream()
                .filter((t) -> isBeforeDate(t.getByDate(), nextWeekDate))
                .filter((t) -> !t.isDone())
                .sorted(Comparator.comparing(Task::getByDate))
                .collect(toList());

        if (filteredThisWeekTasks.isEmpty()) {
            ui.showToUser("You do not have any pending task for the week.");
        } else {
            System.out.println("Tasks to be done within the week:");
            for (Task task : filteredThisWeekTasks) {
                System.out.println(task.toString());
            }
        }
        ui.showLine();
    }

    private boolean isEqualDate(LocalDateTime currentDate, LocalDateTime compareDate) {
        if (currentDate == null) {
            return false;
        }
        return currentDate.toLocalDate().isEqual(compareDate.toLocalDate());
    }

    private boolean isBeforeDate(LocalDateTime currentDate, LocalDateTime compareDate) {
        if (currentDate == null) {
            return false;
        }
        return currentDate.toLocalDate().isBefore(compareDate.toLocalDate());
    }
}
