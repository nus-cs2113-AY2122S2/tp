package seedu.duke;

import seedu.duke.assets.MedicineList;
import seedu.duke.assets.PatientList;
import seedu.duke.assets.DoctorList;
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
    private DoctorList doctorList = new DoctorList();
    private boolean isTerminated = false;

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
                command.deleteDoctor(doctorList, parameters);
                break;
            case "view doctor":
                command.viewDoctor(doctorList, parameters);
                break;
            case "add medicine":
                command.addMedicine(medicineList, parameters);
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
