package seedu.duke;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    private void run() {
        Ui ui = new Ui();
        String userInput;
        userInput = ui.readUserInput();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
