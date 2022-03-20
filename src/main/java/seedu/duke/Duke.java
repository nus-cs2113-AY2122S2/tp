package seedu.duke;

import seedu.command.Command;
import seedu.command.CommandResult;
import seedu.equipment.DuplicateSerialNumber;
import seedu.equipment.EquipmentManager;
import seedu.parser.Parser;
import seedu.storage.Storage;

import java.util.Scanner;

public class Duke {
    private static EquipmentManager equipmentInventory = new EquipmentManager();
    private static Storage storage = new Storage();

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = "                  _                            _                                               \n" +
                "                  (_)                          | |                                              \n" +
                "   ___  __ _ _   _ _ _ __  _ __ ___   ___ _ __ | |_ _ __ ___   __ _ _ __   __ _  __ _  ___ _ __ \n" +
                "  / _ \\/ _` | | | | | '_ \\| '_ ` _ \\ / _ \\ '_ \\| __| '_ ` _ \\ / _` | '_ \\ / _` |/ _` |/ _ \\ '__|\n" +
                " |  __/ (_| | |_| | | |_) | | | | | |  __/ | | | |_| | | | | | (_| | | | | (_| | (_| |  __/ |   \n" +
                "  \\___|\\__, |\\__,_|_| .__/|_| |_| |_|\\___|_| |_|\\__|_| |_| |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_|   \n" +
                "          | |       | |                                                          __/ |          \n" +
                "          |_|       |_|                                                         |___/";
        System.out.println("Hello from\n" + logo);
        Duke duke = new Duke();
        try {
            storage.loadData(equipmentInventory);
        } catch (DuplicateSerialNumber e) {
            // Do ?
        }
        System.out.println("What do you want to do?");
        duke.runCommandLoop();
        storage.saveData(equipmentInventory);
    }

    private void runCommandLoop() {
        Scanner in = new Scanner(System.in);
        String userCommand = in.nextLine();
        Command command;
        CommandResult result;
        while (!userCommand.equals("bye")) {
            command = new Parser().parseCommand(userCommand);
            result = executeCommand(command);
            System.out.println(result.getResultToShow());
            if  (result.getRelevantEquipment() != null) {
                System.out.println(result.getRelevantEquipment());
            }

            userCommand = in.nextLine();
        }
    }

    private CommandResult executeCommand(Command command) {
        command.setEquipmentManager(equipmentInventory);
        CommandResult result = command.execute();
        return result;
    }
}
