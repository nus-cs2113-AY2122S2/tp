package seedu.duke;

import seedu.command.Command;
import seedu.command.CommandResult;
import seedu.equipment.EquipmentManager;
import seedu.parser.Parser;

import java.util.Scanner;

public class Duke {
    private EquipmentManager equipmentInventory = new EquipmentManager();

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What do you want to do?");

        new Duke().runCommandLoop();
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

            userCommand = in.nextLine();
        }
    }

    private CommandResult executeCommand(Command command) {
        command.setEquipmentInventory(equipmentInventory);
        CommandResult result = command.execute();
        return result;
    }
}
