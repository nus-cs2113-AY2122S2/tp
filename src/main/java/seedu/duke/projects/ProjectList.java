package seedu.duke.projects;

import java.util.ArrayList;

public class ProjectList {
    private ArrayList<Project> projectList;

    /**
     * Constructs a ProjectList object.
     *
     */
    public ProjectList() {
        projectList = new ArrayList<Project>();
    }

    /**
     * Adds a new Project to projectList.
     *
     * @param title Name of the Project
     */
    public void addProject(String title) {
        projectList.add(new Project(title));
    }

    /**
     * Deletes a Project from projectList.
     *
     * @param title Name of the project
     */
    public void deleteProject(String title) {
        projectList.remove(title);
    }

    /**
     * Adds a deadline to a specified Project
     *
     * @param title Name of the project
     * @param deadline Deadline for the project
     */
    public void addDeadline(String title, String deadline) {
        boolean isAdded = false;
        for (Project project : projectList) {
            if (project.getTitle().equals(title)) {
                project.setDeadline(deadline);
                System.out.println("Deadline added to " + project.getTitle() + ": " + deadline);
                isAdded = true;
            }
        }
        if (!isAdded) {
            System.out.println("Sorry! There was no project with that name.");
        }
    }
}
