package seedu.duke;

import seedu.command.Command;
import seedu.command.CommandResult;
import seedu.equipment.EquipmentManager;
import seedu.parser.Parser;
import seedu.ui.TextUi;

import java.util.Scanner;

public class Duke {
    private EquipmentManager equipmentInventory = new EquipmentManager();
    private TextUi ui;

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();

        duke.start();
        duke.runCommandLoop();
    }

    /**
     * Initialises the required objects and loads up the data from storage and show welcome message to user
     */
    private void start() {
        ui = new TextUi();
        ui.showWelcomeMessage();
    }

    private void runCommandLoop() {
        Scanner in = new Scanner(System.in);
        String userCommand;
        Command command;
        CommandResult result;
        do {
            userCommand = in.nextLine();
            command = new Parser().parseCommand(userCommand);
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
