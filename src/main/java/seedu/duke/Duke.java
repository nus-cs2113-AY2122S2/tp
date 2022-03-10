package seedu.duke;

import seedu.duke.controllers.MainController;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for application.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MainController main = new MainController(scanner);
        main.takeControl();
    }
}
