package seedu.duke;

import seedu.duke.helper.UI;

import java.util.Scanner;

/**
 * Manager class contains the main loop that runs until application is terminated.
 */
public class Manager {
    UI ui = new UI();
    private boolean isTerminated = false;

    /**
     * Main application loop that holds switch case statement.
     */
    public void runLoop() {
        ui.printGreeting();
        while (!isTerminated) {
            String commandWord = ui.readCommand();
            switch (commandWord) {
            case "bye":
                isTerminated = true;
                ui.printBye();
                break;
            default:
                System.out.println(commandWord);
                break;
            }
        }
    }
}
