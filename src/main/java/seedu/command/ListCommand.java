package seedu.command;

import seedu.equipment.Equipment;
import seedu.equipment.EquipmentType;

import java.util.ArrayList;

/**
 * Subclass of Command. Handles listing of all equipment or listing of equipment of specified type.
 */
public class ListCommand extends Command {
    private final ArrayList<String> commandStrings;
    public static final String COMMAND_WORD = "list";

    /**
     * constructor for ListCommand with NO specified type. Initialises successMessage and usageReminder from Command
     */
    public ListCommand() {
        this.commandStrings = null;
        successMessage = "TOTAL QUANTITY OF EQUIPMENT: %1$d" + System.lineSeparator();
        usageReminder = COMMAND_WORD + ": print a list of all equipment in the inventory. "
                + "Parameters: NIL"
                + "Example: "
                + "list" + System.lineSeparator()
                + COMMAND_WORD + " t/Type: print a list of all equipment in the inventory of the specified type"
                + "Parameters: t/Type"
                + "Example: "
                + "list Microphone";
    }

    /**
     * constructor for ListCommand with specified type. Initialises successMessage and usageReminder from Command.
     *
     * @param commandStrings parsed user input which contains details of equipment to be viewed
     */
    public ListCommand(ArrayList<String> commandStrings) {
        this.commandStrings = commandStrings;
        successMessage = "TOTAL QUANTITY OF %1$s: %2$d" + System.lineSeparator();
        usageReminder = COMMAND_WORD + ": print a list of all equipment in the inventory. "
                + "Parameters: NIL"
                + "Example: "
                + "list" + System.lineSeparator()
                + COMMAND_WORD + " t/Type: print a list of all equipment in the inventory of the specified type"
                + "Parameters: t/Type"
                + "Example: "
                + "list Microphone";
    }

    /**
     * List all equipment or list equipment of specified type.
     *
     * @return CommandResult with message from execution of this command
     */
    public CommandResult execute() {
        int listSize;
        ArrayList<Equipment> equipmentArrayList;
        if (commandStrings == null) {
            equipmentArrayList = equipmentManager.listEquipment();
            listSize = equipmentArrayList.size();
            return new CommandResult(String.format(successMessage, listSize), equipmentArrayList);
        }

        EquipmentType typeToList = EquipmentType.valueOf(commandStrings.get(0));
        equipmentArrayList = equipmentManager.listEquipment(typeToList);
        listSize = equipmentArrayList.size();
        return new CommandResult(String.format(successMessage, typeToList, listSize), equipmentArrayList);
    }
}
