package seedu.duke.task;

public class Event extends Task {
    protected String at;

    /**
     * Creates a constructor for the task 'event'.
     * Event tasks accept both task description and date.
     *
     * @param description Task Description.
     * @param at Task Date.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns task date.
     *
     * @return Task date.
     */
    @Override
    public String getDate() {
        return at;
    }


    /**
     * Returns string version of task type.
     *
     * @return "E" for event.
     */
    @Override
    public String getType() {
        return "E";
    }

    /**
     * Overrides existing task date with new date input.
     *
     * @param date New task date input.
     */
    @Override
    public void resetInput(String date) {
        at = date;
    }

    /**
     * Returns a string version of the task content.
     * Content includes mark status, task type， description and date.
     * For ease of printing the task.
     *
     * @return Task content.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + at + ")";
    }
}
