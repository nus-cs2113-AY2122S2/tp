package seedu.duke;

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
            ui.printPrompt();
            String commandWord = ui.readCommand();
            String parameters = ui.readParameters();
            switch (commandWord) {
            case "add patient":
                command.addPatient(storage.patients, parameters);
                break;
            case "delete patient":
                command.deletePatient(storage.patients, parameters);
                break;
            case "view patient":
                command.viewPatient(storage.patients, parameters);
                break;
            case "add doctor":
                command.addDoctor(storage.doctors, parameters);
                break;
            case "delete doctor":
                command.deleteDoctor(storage.doctors, parameters);
                break;
            case "view doctor":
                command.viewDoctor(storage.doctors, parameters);
                break;
            case "add medicine":
                command.addMedicine(storage.medicines, parameters);
                break;
            case "delete medicine":
                command.deleteMedicine(storage.medicines, parameters);
                break;
            case "view medicine":
                command.viewMedicine(storage.medicines, parameters);
                break;
            case "add appointment":
                command.addAppointment(storage.appointments, storage.patients, parameters);
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
            storage.saveData();
        }
    }
}
