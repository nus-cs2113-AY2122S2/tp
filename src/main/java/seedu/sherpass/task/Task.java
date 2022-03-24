package seedu.sherpass.task;

import seedu.sherpass.enums.Frequency;

import java.time.LocalDateTime;
import static seedu.sherpass.constant.DateAndTimeFormat.outputWithTimeFormat;

public class Task {
    protected String description;
    protected int identifier;
    protected boolean isDone;
    protected LocalDateTime byDate;
    protected LocalDateTime doOnStartDateTime;
    protected LocalDateTime doOnEndDateTime;
    protected Frequency repeatFrequency;


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
    public Task(int identifier, String description, LocalDateTime byDate,
                LocalDateTime doOnStartDateTime, LocalDateTime doOnEndDateTime, Frequency repeatFrequency) {
        this.identifier = identifier;
        this.description = description;
        this.byDate = byDate;
        this.doOnStartDateTime = doOnStartDateTime;
        this.doOnEndDateTime = doOnEndDateTime;
        this.isDone = false;
        this.repeatFrequency = repeatFrequency;
    }

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

    public String getDescription() {
        return description;
    }

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

    public void markAsDone() {
        isDone = true;
    }

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
    public LocalDateTime getByDate() {
        return byDate;
    }

    public LocalDateTime getDoOnStartDateTime() {
        return doOnStartDateTime;
    }

    /**
     * Returns the by date in String format.
     *
     * @return Returns if byDate contains a parsed date.
     *         Otherwise, returns a blank string (no whitespace).
     */
    public String getByDateString() {
        if (byDate != null) {
            return byDate.format(outputWithTimeFormat);
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
        if (doOnStartDateTime != null) {
            return doOnStartDateTime.format(outputWithTimeFormat);
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
        if (this.doOnStartDateTime != null) {
            result += " (reminder on: " + getDoOnDateString() + ")";
        }
        return result;
    }

    public void setTaskDescription(String taskDescription) {
        this.description = taskDescription;
    }

    public void setByDate(LocalDateTime byDate) {
        this.byDate = byDate;
    }

    public void setDoOnStartDateTime(LocalDateTime doOnStartDateTime) {
        this.doOnStartDateTime = doOnStartDateTime;
    }

    public void setDoOnEndDateTime(LocalDateTime doOnEndDateTime) {
        this.doOnEndDateTime = doOnEndDateTime;
    }

    public LocalDateTime getDoOnEndDateTime() {
        return doOnEndDateTime;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public Frequency getRepeatFrequency() {
        return repeatFrequency;
    }
}
