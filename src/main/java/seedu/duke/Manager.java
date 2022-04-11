package seedu.duke;

import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.HalpmiException;
import seedu.duke.exception.UserInputErrorException;
import seedu.duke.exception.NotFoundException;
import seedu.duke.helper.Parser;
import seedu.duke.helper.Storage;
import seedu.duke.helper.UI;
import seedu.duke.helper.command.Command;
import seedu.duke.status.Status;

/**
 * Manager class contains the main loop that runs until application is terminated.
 */
public class Manager {
    UI ui = new UI();
    private boolean isTerminated = false;
    private final Storage storage = new Storage();

    /**
     * Main application loop that holds switch case statement.
     */
    public void runLoop() {
        ui.printLogo();
        ui.printGreeting();

        while (!isTerminated) {
            UI.printPrompt();
            String commandWord = ui.readCommand();
            String parameters = ui.readParameters();
            Status status = null;
            try {
                status = executeCommand(commandWord, parameters);
                ui.print(status);
            } catch (HalpmiException e) {
                UI.printParagraph(e.toString());
            }
            storage.saveData();
        }
    }

    private Status executeAddCommand(String commandWord, String parameters) throws HalpmiException, NotFoundException,
            DuplicateEntryException {

        Command command;
        Status status = null;
        switch (commandWord) {
        case "add patient":
            command = Parser.parseAddPatient(parameters);
            status = command.execute(storage.patients);
            break;
        case "add doctor":
            command = Parser.parseAddDoctor(parameters);
            status = command.execute(storage.doctors);
            break;
        case "add medicine":
            command = Parser.parseAddMedicine(parameters);
            status = command.execute(storage.medicines);
            break;
        case "add appointment":
            Command checkIfPatientExists = Parser.checkIfPatientExists(parameters);
            checkIfPatientExists.execute(storage.patients);

            Command checkIfDoctorExists = Parser.checkIfDoctorExists(parameters);
            checkIfDoctorExists.execute(storage.doctors);

            command = Parser.parseAddAppointment(parameters);
            command.execute(storage.appointments);

            command.execute(storage.patients);
            status = command.execute(storage.doctors);
            break;
        default:
            throw new UserInputErrorException("Invalid Command given!");
        }

        return status;
    }

    private Status executeDeleteCommand(String commandWord, String parameters) throws HalpmiException,
            NotFoundException, DuplicateEntryException {

        Command command;
        Status status = null;
        switch (commandWord) {
        case "delete patient":
            command = Parser.parseDeletePatient(parameters);
            status = command.execute(storage.patients);
            break;
        case "delete doctor":
            command = Parser.parseDeleteDoctor(parameters);
            status = command.execute(storage.doctors);
            break;
        case "delete medicine":
            command = Parser.parseDeleteMedicine(parameters);
            status = command.execute(storage.medicines);
            break;
        case "delete appointment":
            command = Parser.parseDeleteAppointment(parameters);
            command.execute(storage.appointments);
            command.execute(storage.patients);
            status = command.execute(storage.doctors);
            break;
        default:
            throw new UserInputErrorException("Invalid Command given!");
        }
        return status;
    }

    private Status executeViewCommand(String commandWord, String parameters) throws HalpmiException, NotFoundException,
            DuplicateEntryException {

        Command command;
        Status status = null;
        switch (commandWord) {
        case "view patient":
            command = Parser.parseViewPatient(parameters);
            status = command.execute(storage.patients);
            break;
        case "view doctor":
            command = Parser.parseViewDoctor(parameters);
            status = command.execute(storage.doctors);
            break;
        case "view medicine":
            command = Parser.parseViewMedicine(parameters);
            status = command.execute(storage.medicines);
            break;
        case "view appointment":
            command = Parser.parseViewAppointment(parameters);
            status = command.execute(storage.appointments);
            break;
        default:
            throw new UserInputErrorException("Invalid Command given!");
        }
        return status;
    }

    private Status executeEditCommand(String commandWord, String parameters) throws HalpmiException, NotFoundException,
            DuplicateEntryException {

        Command command;
        Status status = null;
        switch (commandWord) {
        case "edit patient":
            command = Parser.parseEditPatient(parameters);
            status = command.execute(storage.patients);
            break;
        case "edit doctor":
            command = Parser.parseEditDoctor(parameters);
            status = command.execute(storage.doctors);
            break;
        case "edit medicine":
            command = Parser.parseEditMedicine(parameters);
            status = command.execute(storage.medicines);
            break;
        default:
            throw new UserInputErrorException("Invalid Command given!");
        }
        return status;
    }

    private Status executeFindCommand(String commandWord, String parameters) throws HalpmiException, NotFoundException,
            DuplicateEntryException {

        Command command;
        Status status = null;
        switch (commandWord) {
        case "find doctor":
            command = Parser.parseFindDoctor(parameters);
            status = command.execute(storage.doctors);
            break;
        case "find patient":
            command = Parser.parseFindPatient(parameters);
            status = command.execute(storage.patients);
            break;
        case "find medicine":
            command = Parser.parseFindMedicine(parameters);
            status = command.execute(storage.medicines);
            break;
        case "find appointment":
            command = Parser.parseFindAppointment(parameters);
            status = command.execute(storage.appointments);
            break;
        default:
            throw new UserInputErrorException("Invalid Command given!");
        }
        return status;
    }

    private Status executeOtherCommands(String commandWord, String parameters) throws HalpmiException,
            NotFoundException, DuplicateEntryException {

        Command command;
        Status status = null;
        switch (commandWord) {
        case "update medicines":
            command = Parser.parseUpdateMedicineStock(parameters);
            status = command.execute(storage.medicines);
            break;
        case "clear old medicines":
            command = Parser.parseClearExpiredMedicine(parameters);
            status = command.execute(storage.medicines);
            break;
        case "dispense medicine":
            command = Parser.parseDispenseMedicine(parameters);

            Command helperCommand = Parser.parseCheckForPatientAppointment(parameters);
            helperCommand.execute(storage.appointments);
            helperCommand = Parser.parseCheckMedicineStock(parameters);
            helperCommand.execute(storage.medicines);

            command.execute(storage.appointments);
            status = command.execute(storage.medicines);
            break;
        default:
            throw new UserInputErrorException("Invalid Command given!");
        }
        return status;
    }

    /*
     * Method that initialises the relevant Command object and executes the execute method.
     * Upon execution, returns either a success message or throws exception.
     * @ param commandWord a String that holds the initial command given by User
     * @ param parameters a String that holds all the additional parameters given following the Command
     * @ return Status object - enum that holds various success messages
     * @ throws HalpmiException when the commandWord or parameters is not following correct formatting
     * @ throws DuplicateEntryException when duplicate entries are being added through the Command
     * @ throws NotFoundException when certain data was not found
     */
    private Status executeCommand(String commandWord, String parameters) throws HalpmiException, NotFoundException,
            DuplicateEntryException {
        String firstCommand = commandWord.split(" ")[0].trim();
        Status status = null;
        switch (firstCommand) {
        case "add":
            status = executeAddCommand(commandWord,parameters);
            break;
        case "delete":
            status = executeDeleteCommand(commandWord,parameters);
            break;
        case "view":
            status = executeViewCommand(commandWord,parameters);
            break;
        case "edit":
            status = executeEditCommand(commandWord,parameters);
            break;
        case "find":
            status = executeFindCommand(commandWord,parameters);
            break;
        case "help":
            status = Status.PRINT_HELP;
            break;
        case "bye":
            status = Status.END_APP;
            System.out.println("Terminated");
            isTerminated = true;
            break;
        default:
            status = executeOtherCommands(commandWord,parameters);
        }
        return status;
    }
}
