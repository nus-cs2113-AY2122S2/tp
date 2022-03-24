package seedu.duke;

import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.HalpmiException;
import seedu.duke.exception.NotFoundException;
import seedu.duke.helper.command.Command;
import seedu.duke.helper.Parser;
import seedu.duke.helper.Storage;
import seedu.duke.helper.UI;
import seedu.duke.status.Status;

/**
 * Manager class contains the main loop that runs until application is terminated.
 */
public class Manager {
    UI ui = new UI();
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
            Status status = null;
            try {
                status = executeCommand(commandWord,parameters);
            } catch (HalpmiException e) {
                e.printStackTrace();
            } catch (NotFoundException e) {
                e.printStackTrace();
            } catch (DuplicateEntryException e) {
                e.printStackTrace();
            }
            ui.print(status);
            storage.saveData();
        }
    }

    private Status executeCommand(String commandWord, String parameters) throws HalpmiException, NotFoundException, DuplicateEntryException {
        Command command;
        Status status = null;
        switch (commandWord) {
        case "add patient":
            command = Parser.parseAddPatient(parameters);
            status = command.execute(storage.patients);
            break;
        case "delete patient":
            command = Parser.parseDeletePatient(parameters);
            status = command.execute(storage.patients);
            break;
        case "view patient":
            command = Parser.parseViewPatient(parameters);
            status = command.execute(storage.patients);
            break;
        case "add doctor":
            command = Parser.parseAddDoctor(parameters);
            status = command.execute(storage.doctors);
            break;
        case "delete doctor":
            command = Parser.parseDeleteDoctor(parameters);
            status = command.execute(storage.doctors);
            break;
        case "view doctor":
            command = Parser.parseViewDoctor(parameters);
            status = command.execute(storage.doctors);
            break;
        case "add medicine":
            command = Parser.parseAddMedicine(parameters);
            status = command.execute(storage.medicines);
            break;
        case "delete medicine":
            command = Parser.parseDeleteMedicine(parameters);
            status = command.execute(storage.medicines);
            break;
        case "view medicine":
            command = Parser.parseViewMedicine(parameters);
            status = command.execute(storage.medicines);
            break;
        case "edit medicine":
            //command.editMedicine(storage.medicines,parameters);
            break;
        case "add appointment":
            //command.addAppointment(storage.appointments, storage.patients, storage.doctors, parameters);
            break;
        case "find appointment":
            //command.findAppointment(storage.appointments, parameters);
            break;
        case "help":
            status = Status.PRINT_HELP;
            break;
        case "bye":
            status = Status.END_APP;
            isTerminated = true;
            break;
        default:
            System.out.println(commandWord);
            break;
        }
        return status;
    }
}
