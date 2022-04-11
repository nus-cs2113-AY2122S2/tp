package seedu.duke;

import seedu.duke.controllers.MainController;

public class Duke {
    /**
     * Main entry-point for application.
     */
    public static void main(String[] args) {
        MainController main = new MainController();
        main.takeControl();
    }
}
