package cpp.projects;

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
        int index = -1;
        int count = 0;

        for (Project project: projectList) {
            if (project.getTitle().equalsIgnoreCase(title)) {
                index = count;
                break;
            }
            count++;
        }
        projectList.remove(index);
    }

    /**
     * prints all projects in the projectList.
     */
    public void printProject() {
        System.out.println("____________________________________________________________");
        if (projectList.size() == 0) { //no project in the list, print another message
            System.out.println("You have not added any projects yet!");
            System.out.println("____________________________________________________________");
            return;
        }
        int count = 1;
        System.out.println("Here are all current project(s) in your list:");
        for (Project project: projectList) {
            System.out.println("[" + count + "] " + project.getTitle());
            count++;
        }
        System.out.println("____________________________________________________________");
    }
}
