package cpp.projects.commandhandler;

import cpp.Constants;
import cpp.exceptions.IllegalCommandException;
import cpp.exceptions.NegativeIndexException;
import cpp.projects.ProjectList;
import cpp.response.Response;

import java.util.Arrays;

public class CommandHandler {

    /**
     * Constructs new CommandHandler object.
     *
     */
    public CommandHandler() {

    }

    /**
     * Handles all non-exit commands given to program by user.
     *
     * @param projectList ProjectList for commands to work with
     * @param userInput Command entered by user
     */
    public void handleUserInput(ProjectList projectList, String userInput) throws IllegalCommandException {
        String[] commands = userInput.split(" ");
        String projectName;

        switch (commands[0].toLowerCase()) {
        case "addproject": //add a project into list
            projectName = getProjectName(commands);
            projectList.addProject(projectName);
            break;
        case "deleteproject": //delete a project based on its name
            projectName = getProjectName(commands);
            projectList.deleteProject(projectName);
            break;
        case "listprojects":
        case "listproject": //view all project(s) by name
            listProjects(projectList);
            break;
        case "todo":
            toDo(projectList, commands);
            break;
        case "mark":
            mark(projectList, commands);
            break;
        case "adddeadline":
            addDeadline(projectList, commands);
            break;
        case "view":
            view(projectList, commands);
            break;
        case "help":
            Response.printHelp();
            break;
        default:
            Response.printDefault();
            break;
        }

    }

    private String getProjectName(String[] userInput) {
        assert (userInput.length >= Constants.TWO_ARGUMENTS) : "You must provide the name for the project!";
        // get the splited version of project name
        String[] splitedName = Arrays.copyOfRange(userInput, 1, userInput.length);
        String projectName = String.join(" ", splitedName);
        return projectName;
    }

    private void listProjects(ProjectList projectList) {
        assert (projectList != null) : "Cannot print projects.";
        projectList.printProject();
    }

    private void toDo(ProjectList projectList, String[] commands) throws IllegalCommandException {
        assert (projectList != null && commands != null) : "Cannot add todo to a project.";
        if (commands.length < Constants.THREE_ARGUMENTS) {
            throw new IllegalCommandException(Constants.MESSAGE_INVALID_TODO_COMMAND_FORMAT);
        }
        String todoString = parseTodoString(commands);
        projectList.addTodoToProject(commands[1], todoString);
    }

    private void mark(ProjectList projectList, String[] commands) throws IllegalCommandException {
        assert (projectList != null && commands != null) : "Cannot mark a todo.";
        if (commands.length < Constants.THREE_ARGUMENTS) {
            throw new IllegalCommandException(Constants.MESSAGE_INVALID_MARK_COMMAND_FORMAT);
        }
        try {
            projectList.markTodoAsDone(commands[1], commands[2]);
        } catch (NegativeIndexException e) {
            System.out.println("The target index is a negative number. Please enter a positive integer.");
        }
    }

    private void addDeadline(ProjectList projectList, String[] commands) throws IllegalCommandException {
        assert (projectList != null && commands != null) : "Cannot add a deadline to a project.";
        if (commands.length < Constants.THREE_ARGUMENTS) {
            throw new IllegalCommandException(Constants.MESSAGE_INVALID_COMMAND_FORMAT);
        }
        projectList.addDeadline(commands[1], commands[2]);
    }

    private void view(ProjectList projectList, String[] commands) throws IllegalCommandException {
        assert (projectList != null && commands != null) : "Cannot view this project.";
        if (commands.length < Constants.TWO_ARGUMENTS) {
            throw new IllegalCommandException(Constants.MESSAGE_INVALID_COMMAND_FORMAT);
        }
        projectList.view(commands[1]);
    }

    private String parseTodoString(String[] strings) {
        String todoString = "";
        if (strings.length == Constants.THREE_ARGUMENTS) {
            todoString = strings[2];
        } else {
            for (int i = 2; i < strings.length; i++) {
                todoString = todoString + " " + strings[i];
            }
        }
        return todoString;
    }
}
