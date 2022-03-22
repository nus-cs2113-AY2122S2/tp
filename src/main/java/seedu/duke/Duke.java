package seedu.duke;

import seedu.command.Command;
import seedu.command.CommandResult;
import seedu.equipment.DuplicateSerialNumberException;
import seedu.equipment.EquipmentManager;
import seedu.parser.Parser;
import seedu.ui.TextUi;
import seedu.storage.Storage;

import java.util.Scanner;

public class Duke {
    private TextUi ui;
    private static EquipmentManager equipmentInventory = new EquipmentManager();
    private static Storage storage = new Storage();

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();

        duke.start();
        storage.loadData(equipmentInventory);
        duke.runCommandLoop();
        storage.saveData(equipmentInventory);
    }

    /**
     * Initialises the required objects and loads up the data from storage and show welcome message to user.
     */
    private void start() {
        ui = new TextUi();
        ui.showWelcomeMessage();
    }

    private void runCommandLoop() {
        Scanner in = new Scanner(System.in);
        Parser parser = new Parser();
        String userCommand;
        Command command;
        CommandResult result;
        do {
            userCommand = in.nextLine();
            command = parser.parseCommand(userCommand);
            result = executeCommand(command);
            ui.showResultToUser(result);
        } while (!userCommand.equals("bye"));
    }

    private CommandResult executeCommand(Command command) {
        command.setEquipmentManager(equipmentInventory);
        CommandResult result = command.execute();

        return result;
    }
}
