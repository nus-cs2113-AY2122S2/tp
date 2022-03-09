package seedu.duke.command_handler;

import seedu.duke.projects.Project;
import seedu.duke.projects.ProjectList;

import java.util.ArrayList;
import java.util.Scanner;

public class CommandHandler {

    Scanner sc;

    public String line;

    public String[] commands;

    /**
     *
     */
    public void handleUserInput(ProjectList projectList) {
        while (true) {
            Scanner in = new Scanner(System.in);
            line = sc.nextLine();
            commands = line.split(" ");

            switch (commands[0].toLowerCase()) {
            case "addproject": //end the program
                projectList.addProject(commands[1]);
                break;
            case "deleteproject": //list out all tasks
                projectList.deleteProject(commands[1]);
                break;
            case "exit":
                System.exit(0);

            }
        }
    }
}
