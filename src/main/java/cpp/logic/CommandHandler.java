package cpp.logic;

import cpp.ui.Constants;
import cpp.exceptions.IllegalCommandException;
import cpp.logic.commands.Command;
import cpp.logic.commands.ListProjectCommand;

import cpp.logic.parser.AddProjectCommandParser;
import cpp.logic.parser.ProjectDeadlineCommandParser;
import cpp.logic.parser.AddTodoCommandParser;
import cpp.logic.parser.TodoDeadlineCommandParser;
import cpp.logic.parser.DeleteProjectCommandParser;
import cpp.logic.parser.MarkCommandParser;
import cpp.logic.parser.ViewProjectCommandParser;
import cpp.logic.parser.AddLanguageCommandParser;
import cpp.logic.parser.ListLanguageCommandParser;
import cpp.logic.parser.ChangeGitHubLinkCommandParser;
import cpp.logic.parser.OpenGitCommandParser;

import cpp.model.ProjectList;
import cpp.ui.Response;

public class CommandHandler {
    /**
     * Handles all non-exit commands given to program by user.
     *
     * @param projectList ProjectList for commands to work with
     * @param userInput Command entered by user
     */
    public String handleUserInput(ProjectList projectList, String userInput) throws IllegalCommandException {
        String[] commands = userInput.split(" ");
        if (commands.length == 0) {
            throw new IllegalCommandException(Constants.NO_COMMAND_ENTERED);
        }
        String executeResult = "Default Result";

        switch (commands[0].toLowerCase()) {
        case "addproject": //add a project into list
            executeResult = executeCommand(projectList, new AddProjectCommandParser().parse(commands));
            break;
        case "deleteproject": //delete a project based on its name
            executeResult = executeCommand(projectList, new DeleteProjectCommandParser().parse(commands));
            break;
        case "listprojects": //Fallthrough
        case "listproject": //view all project(s) by name
            executeResult = executeCommand(projectList, new ListProjectCommand());
            break;
        case "todo":
            executeResult = executeCommand(projectList, new AddTodoCommandParser().parse(commands));
            break;
        case "mark":
            executeResult = executeCommand(projectList, new MarkCommandParser().parse(commands));
            break;
        case "projdeadline":
            executeResult = executeCommand(projectList, new ProjectDeadlineCommandParser().parse(commands));
            break;
        case "tododeadline":
            executeResult = executeCommand(projectList, new TodoDeadlineCommandParser().parse(commands));
            break;
        case "changegit":
            executeResult = executeCommand(projectList, new ChangeGitHubLinkCommandParser().parse(commands));
            break;
        case "opengit":
            executeResult = executeCommand(projectList, new OpenGitCommandParser().parse(commands));
            break;
        case "view":
            executeResult = executeCommand(projectList, new ViewProjectCommandParser().parse(commands));
            break;
        case "addlanguage":
            executeResult = executeCommand(projectList, new AddLanguageCommandParser().parse(commands));
            break;
        case "listlanguages":
            executeResult = executeCommand(projectList, new ListLanguageCommandParser().parse(commands));
            break;
        case "help":
            Response.printHelp();
            break;
        default:
            throw new IllegalCommandException(Constants.UNKNOWN_COMMAND);
        }
        return executeResult;
    }

    /**
     * Executes command and return the execution result as String.
     * @return execution result
     */

    private String executeCommand(ProjectList projectList, Command command) {
        assert (command != null) : "The command should not be null.";
        return command.execute(projectList);
    }

}
