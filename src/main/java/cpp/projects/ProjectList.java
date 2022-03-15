package cpp.projects;

import cpp.Constants;

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
        System.out.println(Constants.SEPARATOR);
        if (projectList.size() == 0) { //no project in the list, print another message
            System.out.println("You have not added any projects yet!");
            System.out.println(Constants.SEPARATOR);
            return;
        }
        int count = 1;
        System.out.println("Here are all current project(s) in your list:");
        for (Project project: projectList) {
            System.out.println("[" + count + "] " + project.getTitle() + " (" + project.getDeadline() + ")");
            count++;
        }
        System.out.println(Constants.SEPARATOR);
    }

    /**
     * Add a todo to one project in projectList.
     * @param indexString Index of the project
     */

    public void addTodoToProject(String indexString, String todoString) {
        int index;
        try {
            index = Integer.parseInt(indexString);
        } catch (NumberFormatException e) {
            System.out.println(Constants.SEPARATOR);
            System.out.println("The project index is not an integer!");
            System.out.println(Constants.SEPARATOR);
            return;
        }

        Project targetProject = new Project();
        targetProject = projectList.get(index - 1);

        targetProject.addTodo(todoString);
        System.out.println("Todos have been added to project " + targetProject.getTitle());
    }

    /**
     * Marks a todo in a project as done.
     * @param indexStringProj Index of the project.
     * @param indexStringTodo Index of the todo.
     */

    public void markTodoAsDone(String indexStringProj, String indexStringTodo) {
        int indexProj;
        int indexTodo;
        try {
            indexProj = Integer.parseInt(indexStringProj);
            indexTodo = Integer.parseInt(indexStringTodo);
        } catch (NumberFormatException e) {
            System.out.println(Constants.SEPARATOR);
            System.out.println("The input is not an integer!");
            System.out.println(Constants.SEPARATOR);
            return;
        }
        Project targetProject = projectList.get(indexProj - 1);
        try {
            targetProject.markTodoAsDone(indexTodo);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The index is out or range.");
        }

    }

    /**
     * Adds a deadline to a specified Project.
     *
     * @param title Name of the project
     * @param deadline Deadline for the project
     */
    public void addDeadline(String title, String deadline) {
        System.out.println(Constants.SEPARATOR);
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
        System.out.println(Constants.SEPARATOR);
    }

    /**
     * Displays all details of a specified Project.
     *
     * @param title Name of the project
     */
    public void view(String title) {
        System.out.println(Constants.SEPARATOR);
        boolean isFound = false;
        for (Project project : projectList) {
            if (project.getTitle().equals(title)) {
                project.print();
                isFound = true;
            }
        }
        if (!isFound) {
            System.out.println("Sorry! There was no project with that name.");
        }
        System.out.println(Constants.SEPARATOR);
    }
}
