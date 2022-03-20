package seedu.sherpass.task;

import java.time.LocalDate;

public class NonrecurringTask extends Task {

    /**
     * Creates a constructor for the parent class of tasks, 'Task'.
     * Accepts only task description
     *
     * @param description Description of task.
     * @param byDate Date by which task is to be completed
     * @param doOnDate Date user has set to complete the task
     */
    public NonrecurringTask(String description, LocalDate byDate, LocalDate doOnDate) {
        super(description, byDate, doOnDate);
    }
}
