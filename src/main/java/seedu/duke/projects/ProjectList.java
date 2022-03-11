package seedu.duke.projects;

import java.util.ArrayList;

public class ProjectList {
    private static ArrayList<Project> projectList;

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
     * prints all projects in the projectList.
     */
    public void printProject() {
        int count = 1;
        System.out.println("____________________________________________________________");
        System.out.println("Here are all current project(s) in your list: ");
        for (Project project: projectList) {
            System.out.println("[" + count + "] " + project.getTitle());
            count++;
        }
        System.out.println("____________________________________________________________");
    }
}
