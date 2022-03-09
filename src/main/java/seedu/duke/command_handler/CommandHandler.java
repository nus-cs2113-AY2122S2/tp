package seedu.duke.command_handler;

import seedu.duke.projects.Project;
import seedu.duke.projects.ProjectList;
import java.util.Scanner;

public class CommandHandler {

    /** Scanner for user input */
    public Scanner sc;
    /** Command entered by user */
    public String line;
    /** All words of command entered by user */
    public String[] commands;

    /**
     * Constructs new CommandHandler object
     *
     * @return new CommandHandler object
     */
    public CommandHandler() {

    }

    /**
     *
     */
    public void handleUserInput(ProjectList projectList) {

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
        case "exit":
            System.exit(0);
            break;
        default:
            System.out.println("Unknown command");
            break;
        }

    }
}
