package cpp.logic;

import cpp.Constants;
import cpp.exceptions.IllegalCommandException;
import cpp.exceptions.NegativeIndexException;
import cpp.logic.commands.Command;
import cpp.logic.parser.AddProjectCommandParser;
import cpp.logic.parser.AddTodoCommandParser;
import cpp.logic.parser.MarkCommandParser;
import cpp.model.ProjectList;
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
        String executeResult;

        switch (commands[0].toLowerCase()) {
        case "addproject": //add a project into list
            projectName = getProjectName(commands);
            projectList.addProject(projectName);
            break;
        case "deleteproject": //delete a project based on its name
            projectName = getProjectName(commands);
            projectList.deleteProject(projectName);
            break;
        case "listproject": //view all project(s) by name
            listProjects(projectList);
            break;
        case "todo":
            executeResult = executeCommand(projectList, new AddTodoCommandParser().parse(commands));
            break;
        case "mark":
            executeResult = executeCommand(projectList, new MarkCommandParser().parse(commands));
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

        //System.out.println("Execute result: ", executeResult);

    }

    private String executeCommand(ProjectList projectList, Command command) {
        assert (command != null) : "The command should not be null.";
        return command.execute(projectList);
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
