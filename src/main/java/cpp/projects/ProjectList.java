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
     * It will check if the project has existed already in the list by using findProjectIndex() method.
     *
     * @param title Name of the Project
     */
    public void addProject(String title) {
        System.out.println(Constants.SEPARATOR);
        int index = findProjectIndex(title);
        if (index == -1) { //this is a new project
            projectList.add(new Project(title));
            System.out.println(title + " added.");
        } else {
            System.out.println("Failed to add " + title + ". You have added this project before!");
        }
        System.out.println(Constants.SEPARATOR);
    }

    /**
     * Deletes a Project from projectList.
     *
     * @param title Name of the project
     */
    public void deleteProject(String title) {
        System.out.println(Constants.SEPARATOR);
        int index = findProjectIndex(title);
        try {
            projectList.remove(index);
            System.out.println(title + " deleted.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is no such project named " + title + ".");
        } finally {
            System.out.println(Constants.SEPARATOR);
        }
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
        targetProject.markTodoAsDone(indexTodo);
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
     * Gets the total number of projects in the list (for test use).
     *
     * @return total number of projects in the list
     */

    public int getProjectNo() {
        return projectList.size();
    }

    private int findProjectIndex(String name) {
        int index = -1;
        int count = 0;

        for (Project project: projectList) {
            if (project.getTitle().equalsIgnoreCase(name)) { //find the index of the project
                index = count;
                break;
            }
            count++;
        }
        return index;
    }
}
