package seedu.duke.projects;

public class Project {
    private String title;
    private String deadline;

    /**
     * Constructs a Project object.
     *
     * @param title The name of the Project object
     */
    public Project(String title) {
        this.title = title;
    }

    /**
     * Gets the Project title
     *
     * @return The Project title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets a deadline to the Project
     *
     * @param deadline Deadline of the project
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    /**
     * Gets the deadline of the Project
     *
     * @return The deadline of the Project
     */
    public String getDeadline() {
        if (deadline == null) {
            return "No deadline specified";
        }
        return deadline;
    }
}
