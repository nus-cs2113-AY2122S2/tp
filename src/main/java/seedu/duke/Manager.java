package seedu.duke;

import seedu.duke.assets.MedicineList;
import seedu.duke.assets.PatientList;
import seedu.duke.helper.Command;
import seedu.duke.helper.Storage;
import seedu.duke.helper.UI;

/**
 * Manager class contains the main loop that runs until application is terminated.
 */
public class Manager {
    UI ui = new UI();
    Command command = new Command();
    private boolean isTerminated = false;
    private Storage storage = new Storage();

    /**
     * Main application loop that holds switch case statement.
     */
    public void runLoop() {
        ui.printGreeting();
        while (!isTerminated) {
            String commandWord = ui.readCommand();;
            String parameters = ui.readParameters();
            switch (commandWord) {
            case "add patient":
                command.addPatient(storage.patients, parameters);
                break;
            case "delete patient":
                command.deletePatient(storage.patients, parameters);
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
                command.addMedicine(storage.medicines, parameters);
                break;
            case "delete medicine":
                command.deleteMedicine(storage.medicines, parameters);
                break;
            case "view medicine":
                break;
            case "help":
                ui.printHelp();
                break;
            case "bye":
                isTerminated = true;
                storage.saveData();
                ui.printBye();
                break;
            default:
                System.out.println(commandWord);
                break;
            }
        }
    }
}
