package seedu.duke;

import java.util.Scanner;

public class Parser {

    public void run(TaskManager tasks) {
        UserInterface ui = new UserInterface();
        Scanner in = new Scanner(System.in);
        String userInput = "";
        while (!userInput.equals("bye")) {
            userInput = in.nextLine().trim();
            String command = ui.getCommand(userInput);
            switch (command) {
            case "view doctors":
                tasks.viewDoctors(userInput);
                break;
            case "view patients":
                tasks.viewPatients(userInput);
                break;
            case "view medicines":
                tasks.viewMedicines(userInput);
                break;
            case "add doctor":
                tasks.addDoctor(userInput);
                break;
            case "add patient":
                tasks.addPatient(userInput);
                break;
            case "add medicine":
                tasks.addMedicine(userInput);
                break;
            case "delete doctor":
                tasks.deleteDoctor(userInput);
                break;
            case "delete patient":
                tasks.deletePatient(userInput);
                break;
            case "delete medicine":
                tasks.deleteMedicine(userInput);
                break;
            case "delete medicine":
                tasks.deleteMedicine(userInput);
                break;
            case "assign": // Assign patient to specialised doctor
                tasks.assignPatientToDoctor(userInput);
                break;
            case "find":
                tasks.findTasks(userInput);
                break;
            case "":
                ui.printNoCommandMessage();
                break;
            default:
                ui.printWrongInput();
                ui.printHelpMessage();
                break;
            }
        }
        ui.printByeMessage();
    }
}
