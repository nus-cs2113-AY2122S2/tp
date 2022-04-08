package seedu.sherpass.task;

import seedu.sherpass.enums.Frequency;

import java.time.LocalDateTime;

import static seedu.sherpass.constant.DateAndTimeFormat.outputDateOnlyFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.outputWithTimeFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.outputWithoutTimeFormat;
import static seedu.sherpass.constant.Message.EMPTY_STRING;

public class Task {
    protected String description;
    protected int identifier;
    protected boolean isDone;
    protected LocalDateTime byDateTime;
    protected LocalDateTime doOnStartDateTime;
    protected LocalDateTime doOnEndDateTime;

    protected int index;

    /**
     * Creates a constructor for the parent class of tasks, 'Task'.
     * Accepts only task description
     *
     * @param identifier  Identity number of a repeated task.
     * @param description Description of task.
     */
    public Task(int identifier, String description, LocalDateTime byDateTime,
                LocalDateTime doOnStartDateTime, LocalDateTime doOnEndDateTime) {
        this.identifier = identifier;
        this.description = description;
        this.byDateTime = byDateTime;
        this.doOnStartDateTime = doOnStartDateTime;
        this.doOnEndDateTime = doOnEndDateTime;
        this.isDone = false;
        this.index = 0;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Returns the time range for when the task is taking place.
     * Format of time is in 24 hours.
     *
     * @return Returns a range of time when the task occurs.
     */
    public String getTimePeriod() {
        return doOnStartDateTime.toLocalTime().toString()
                + " - " + doOnEndDateTime.toLocalTime().toString();
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
    public LocalDateTime getByDateTime() {
        return byDateTime;
    }

    public LocalDateTime getDoOnStartDateTime() {
        return doOnStartDateTime;
    }

    /**
     * Returns the by date in String format.
     *
     * @return Returns if byDate contains a parsed date. Otherwise, returns a blank string (no whitespace).
     */
    public String getByDateString() {
        if (byDateTime != null) {
            return byDateTime.format(outputWithoutTimeFormat);
        }
        return EMPTY_STRING;
    }

    /**
     * Returns the do on date in String format.
     *
     * @return Returns if doOnDate contains a parsed date. Otherwise, returns a blank string (no whitespace).
     */
    public String getDoOnDateString(boolean isDateOnly) {
        if (doOnStartDateTime != null) {
            return (isDateOnly) ? doOnStartDateTime.format(outputDateOnlyFormat)
                    : doOnStartDateTime.format(outputWithTimeFormat);
        }
        return EMPTY_STRING;
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
        String result = index + ". [" + this.getStatusIcon() + "] " + this.getDescription();
        if (this.doOnStartDateTime != null) {
            result += " (to do on: " + getDoOnDateString(false) + " - "
                    + doOnEndDateTime.toLocalTime().toString() + ")";
        }
        if (this.byDateTime != null) {
            result += " (by: " + getByDateString() + ")";
        }
        return result;
    }

    public String printTask() {
        String result = this.getDescription() + " (to do on: " + getDoOnDateString(false) + " - "
                + doOnEndDateTime.toLocalTime().toString() + ")";
        if (this.byDateTime != null) {
            result += " (by: " + getByDateString() + ")";
        }
        return result;
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

    /**
     * Edits the task.
     *
     * @param identifier      The new identifier of the task
     * @param taskDescription The new task description of the task
     * @param startDateOffset The offset of the new start date
     * @param endDateOffset   The offset of the new end date
     * @param byDateOffset    The offset of the new by date
     */
    public void editTask(int identifier, String taskDescription,
            long startDateOffset, long endDateOffset, long byDateOffset) {
        this.identifier = identifier;
        if (!taskDescription.isBlank()) {
            description = taskDescription;
        }
        if (startDateOffset != 0) {
            doOnStartDateTime = doOnStartDateTime.plusSeconds(startDateOffset);
        }
        if (endDateOffset != 0) {
            doOnEndDateTime = doOnEndDateTime.plusSeconds(endDateOffset);
        }
        if (byDateOffset != 0) {
            byDateTime = byDateTime.plusSeconds(byDateOffset);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Task)) {
            return false;
        }

        Task t = (Task) o;
        return description.equals(t.getDescription())
                && doOnStartDateTime.equals(t.getDoOnStartDateTime())
                && doOnEndDateTime.equals(t.getDoOnEndDateTime())
                && identifier == (t.getIdentifier())
                && byDateTime.equals(t.getByDateTime());
    }

    /**
     * Returns a new task with the same identifier and description.
     * The dates incremented according to the frequency.
     *
     * @param frequency The frequency of recurrence
     * @return The next occurrence of the task
     */
    public Task prepareNextTask(Frequency frequency) {
        LocalDateTime newStartDate = incrementDate(doOnStartDateTime,
                frequency);
        LocalDateTime newEndDate = incrementDate(doOnEndDateTime,
                frequency);
        LocalDateTime newByDate = byDateTime;
        if (newByDate != null) {
            newByDate = incrementDate(newByDate, frequency);
        }
        return new Task(identifier, description, newByDate,
                newStartDate, newEndDate);
    }

    /**
     * Returns the incremented date according to the frequency.
     *
     * @param currentDate The current date to be incremented
     * @param frequency   The frequency of recurrence.
     * @return The incremented date according to the frequency
     */
    private LocalDateTime incrementDate(LocalDateTime currentDate, Frequency frequency) {
        if (frequency == Frequency.SINGLE) {
            return currentDate;
        } else if (frequency == Frequency.DAILY) {
            return currentDate.plusDays(1);
        } else if (frequency == Frequency.WEEKLY) {
            return currentDate.plusWeeks(1);
        }
        return currentDate.plusMonths(1);
    }

    public Task clone() {
        return new Task(this.identifier, this.description, this.byDateTime,
                this.doOnStartDateTime, this.doOnEndDateTime);
    }
}
