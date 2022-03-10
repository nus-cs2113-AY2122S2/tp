package seedu.duke;

import seedu.duke.helper.UI;

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
            case "add patient":
                break;
            case "del patient":
                break;
            case "view patient":
                break;
            case "add doctor":
                break;
            case "del doctor":
                break;
            case "view doctor":
                break;
            case "add medicine":
                break;
            case "del medicine":
                break;
            case "view medicine":
                break;
            case "help":
                ui.printHelp();
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
