package seedu.sherpass.task;

import java.time.LocalDate;

import static seedu.sherpass.constant.DateAndTimeFormat.outputFormat;

public class Task {

    protected String description;
    protected boolean isDone;
    protected LocalDate byDate;
    protected LocalDate doOnDate;
    // The index of the task with respect to being within
    // the ArrayList<Task> tasks in TaskList class
    // To add to constructor
    private final int placeholderIndex = 1;
    protected int index = placeholderIndex;

    // Time period of the task when user blocks out the pocket of time
    // in the timetable. Format has been hardcoded to be XXXX - XXXX,
    // e.g. 1400 - 1500. To add to constructor
    private final String placeholderTime = "0900 - 1100";
    protected String timePeriod = placeholderTime;

    /**
     * Creates a constructor for the parent class of tasks, 'Task'.
     * Accepts only task description
     *
     * @param description Description of task.
     */
    public Task(String description, LocalDate byDate, LocalDate doOnDate) {
        this.description = description;
        this.isDone = false;
        this.byDate = byDate;
        this.doOnDate = doOnDate;
    }

    /**
     * Returns the index of a task with respect to its placement
     * in the main task list.
     * @return Index of a task
     */
    public int getIndex() {
        return index;
    }

    /**
     * Returns the time when the task is taking place.
     * Format of time is in 24 hours.
     *
     * @return Returns a range of time when the task occurs.
     */
    public String getTimePeriod() {
        return timePeriod;
    }

    /**
     * Returns task description.
     *
     * @return task description.
     */
    public String getDescription() {
        return description;
    }


    /**
     * Returns mark status of task.
     *
     * @return Returns true if task has been marked. False otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns String version of mark status.
     *
     * @return X if task has been marked. White space otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }


    /**
     * Marks task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks task as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns task date. Parent class is created
     * as a template for child classes to perform its own
     * respective functions, i.e. each task date returned is
     * different for each task type.
     *
     * @return White space.
     */
    public LocalDate getByDate() {
        return byDate;
    }

    public LocalDate getDoOnDate() {
        return doOnDate;
    }

    /**
     * Returns the by date in String format.
     *
     * @return Returns if byDate contains a parsed date.
     *         Otherwise, returns a blank string (no whitespace).
     */
    public String getByDateString() {
        if (byDate != null) {
            return byDate.format(outputFormat);
        }
        return "";
    }

    /**
     * Returns the do on date in String format.
     *
     * @return Returns if doOnDate contains a parsed date.
     *         Otherwise, returns a blank string (no whitespace).
     */
    public String getDoOnDateString() {
        if (doOnDate != null) {
            return doOnDate.format(outputFormat);
        }
        return "";
    }

    /**
     * Returns a string version of the task content.
     * Content includes mark status, task type and description.
     * For ease of printing the task.
     *
     * @return Task content.
     */
    @Override
    public String toString() {
        String result = "[" + this.getStatusIcon() + "] " + this.getDescription();
        if (this.byDate != null) {
            result += " (by: " + getByDateString() + ")";
        }
        if (this.doOnDate != null) {
            result += " (reminder on: " + getDoOnDateString() + ")";
        }
        return result;
    }

    public void setTaskDescription(String taskDescription) {
        this.description = taskDescription;
    }

    public void setByDate(LocalDate byDate) {
        this.byDate = byDate;
    }

    public void setDoOnDate(LocalDate doOnDate) {
        this.doOnDate = doOnDate;
    }
}
