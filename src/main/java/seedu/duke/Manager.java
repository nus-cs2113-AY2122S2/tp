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
            case "delete patient":
                break;
            case "view patient":
                break;
            case "add doctor":
                break;
            case "delete doctor":
                break;
            case "view doctor":
                break;
            case "add medicine":
                break;
            case "delete medicine":
                break;
            case "view medicine":
                break;
            case "help":
                ui.printHelp();
                break;
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
