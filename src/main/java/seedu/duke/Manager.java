package seedu.duke;

import seedu.duke.assets.MedicineList;
import seedu.duke.assets.PatientList;
import seedu.duke.helper.Command;
import seedu.duke.helper.UI;

/**
 * Manager class contains the main loop that runs until application is terminated.
 */
public class Manager {
    UI ui = new UI();
    Command command = new Command();
    PatientList patientList = new PatientList();
    private MedicineList medicineList = new MedicineList();
    private boolean isTerminated = false;

    /**
     * Main application loop that holds switch case statement.
     */
    public void runLoop() {
        ui.printGreeting();
        while (!isTerminated) {
            String commandWord = ui.readCommand();
            String parameters = ui.readParameters();
            switch (commandWord) {
            case "add patient":
                command.addPatient(patientList, parameters);
                break;
            case "delete patient":
                command.deletePatient(patientList, parameters);
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
                command.addMedicine(medicineList, parameters);
                break;
            case "delete medicine":
                break;
            case "view medicine":
                command.viewMedicine(medicineList, parameters);
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
