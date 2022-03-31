package cpp.logic;

import cpp.logic.parser.*;
import cpp.ui.Constants;
import cpp.exceptions.IllegalCommandException;
import cpp.logic.commands.Command;
import cpp.logic.commands.ListLanguageCommand;
import cpp.logic.commands.ListProjectCommand;
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
        String executeResult = "Default Result";

        switch (commands[0].toLowerCase()) {
        case "addproject": //add a project into list
            executeResult = executeCommand(projectList, new AddProjectCommandParser().parse(commands));
            break;
        case "deleteproject": //delete a project based on its name
            executeResult = executeCommand(projectList, new DeleteProjectCommandParser().parse(commands));
            break;
        case "listprojects":
        case "listproject": //view all project(s) by name
            executeResult = executeCommand(projectList, new ListProjectCommand());
            break;
        case "todo":
            executeResult = executeCommand(projectList, new AddTodoCommandParser().parse(commands));
            break;
        case "mark":
            executeResult = executeCommand(projectList, new MarkCommandParser().parse(commands));
            break;
        case "adddeadline":
            executeResult = executeCommand(projectList, new AddDeadlineCommandParser().parse(commands));
            break;
        case "changegit":
            executeResult = executeCommand(projectList, new ChangeGitHubLinkCommandParser().parse(commands));
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

    private String executeCommand(ProjectList projectList, Command command) {
        assert (command != null) : "The command should not be null.";
        return command.execute(projectList);
    }
}
