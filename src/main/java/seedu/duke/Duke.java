package seedu.duke;

import java.util.Scanner;

public class Duke {

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.runLoop();
        System.out.println("Terminated");
    }
}
