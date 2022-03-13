package seedu.duke;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    private void run() {
        boolean shouldExitProgram = false;
        Ui ui = new Ui();
        String userInput;
        while (!shouldExitProgram) {
            userInput = ui.readUserInput();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
