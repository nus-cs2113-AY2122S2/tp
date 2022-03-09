package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;

import seedu.duke.command_handler.CommandHandler;
import seedu.duke.projects.Project;
import seedu.duke.projects.ProjectList;

public class Duke {

    private static ProjectList projectList = new ProjectList();
    private static CommandHandler commandHandler = new CommandHandler();

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {

        System.out.println("Welcome to CSProjPlanner\n");

        while (true) {
            commandHandler.handleUserInput(projectList);
        }

    }
}
