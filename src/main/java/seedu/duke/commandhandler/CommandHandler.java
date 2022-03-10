package seedu.duke.commandhandler;

import seedu.duke.Messages;
import seedu.duke.exceptions.IllegalCommandException;
import seedu.duke.projects.ProjectList;
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
     * Handles all commands given to program by user.
     *
     * @param projectList ProjectList for commands to work with
     */
    public void handleUserInput(ProjectList projectList) throws IllegalCommandException {

        sc = new Scanner(System.in);
        line = sc.nextLine();
        commands = line.split(" ");

        switch (commands[0].toLowerCase()) {
        case "addproject": //end the program
            projectList.addProject(commands[1]);
            System.out.println(commands[1] + " Added.");
            break;
        case "deleteproject": //list out all tasks
            projectList.deleteProject(commands[1]);
            System.out.println(commands[1] + " Deleted.");
            break;
        case "todo":
            projectList.addTodoToProject(commands[1]);
            break;
        case "mark":
            if (commands.length < 3){
                throw new IllegalCommandException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
            }
            projectList.markTodoAsDone(commands[1], commands[2]);
            break;
        case "exit":
            System.exit(0);
            break;
        default:
            System.out.println("Unknown command");
            break;
        }

    }
}
