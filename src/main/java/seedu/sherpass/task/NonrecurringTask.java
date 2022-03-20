package seedu.sherpass.task;

import java.time.LocalDate;

public class NonrecurringTask extends Task {

    /**
     * Creates a constructor for the parent class of tasks, 'Task'.
     * Accepts only task description
     *
     * @param description Description of task.
     * @param byDate
     * @param doOnDate
     */
    public NonrecurringTask(String description, LocalDate byDate, LocalDate doOnDate) {
        super(description, byDate, doOnDate);
    }
}
