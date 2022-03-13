package cpp.projects.commandhandler;

import cpp.exceptions.IllegalCommandException;
import cpp.projects.ProjectList;
import cpp.Messages;

import java.util.Scanner;

public class CommandHandler {

    /** Scanner for user input. */
    public Scanner sc;
    /** Command entered by user. */
    public String line;
    /** All words of command entered by user. */
    public String[] commands;

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
     */
    public void handleUserInput(ProjectList projectList, String userInput) throws IllegalCommandException {
        commands = userInput.split(" ");

        switch (commands[0].toLowerCase()) {
        case "addproject": //add a project into list
            projectList.addProject(commands[1]);
            System.out.println("____________________________________________________________");
            System.out.println(commands[1] + " Added.");
            System.out.println("____________________________________________________________");
            break;
        case "deleteproject": //delete a project based on its name
            projectList.deleteProject(commands[1]);
            System.out.println("____________________________________________________________");
            System.out.println(commands[1] + " Deleted.");
            System.out.println("____________________________________________________________");
            break;
        case "listprojects":
        case "listproject": //view all project(s) by name
            projectList.printProject();
            break;
        case "todo":
            if (commands.length < 3) {
                throw new IllegalCommandException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
            }
            String todoString = parseTodoString(commands);
            projectList.addTodoToProject(commands[1], todoString);
            break;
        case "mark":
            if (commands.length < 3) {
                throw new IllegalCommandException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
            }
            projectList.markTodoAsDone(commands[1], commands[2]);
            break;
        default:
            System.out.println("____________________________________________________________");
            System.out.println("Unknown command.");
            System.out.println("____________________________________________________________");
            break;
        }

    }

    public String parseTodoString(String[] strings) {
        String todoString = "";
        if (strings.length == 3) {
            todoString = strings[2];
        } else {
            for (int i = 2; i < strings.length; i++) {
                todoString = todoString + " " + strings[i];
            }
        }
        return todoString;
    }
}
