package seedu.command;

import seedu.equipment.Equipment;
import seedu.equipment.EquipmentType;

import java.util.ArrayList;

/**
 * Subclass of Command. Handles checking of details of a specified equipment from equipmentInventory.
 */
public class CheckCommand extends Command {
    private final ArrayList<String> COMMAND_STRINGS;
    public static final String COMMAND_WORD = "check";
    public static final String COMMAND_DESCRIPTION = ": Gives details of the equipment with the specified name. "
            + "Parameters: n/ITEM_NAME\n"
            + "Example: "
            + "check n/MixerC";

    /**
     * constructor for CheckCommand. Initialises successMessage and usageReminder from Command
     * @param commandStrings parsed user input which contains details of equipment to be viewed
     */
    public CheckCommand(ArrayList<String> commandStrings){
        COMMAND_STRINGS = commandStrings;
        successMessage = "Here are the equipment matching to '%1$s':" + System.lineSeparator();
        usageReminder = COMMAND_WORD + COMMAND_DESCRIPTION;
    }

    /**
     * Gives details of equipment specified by name
     * @return CommandResult with message from execution of this command
     */
    public CommandResult execute(){
        String name, sn, purchasedFrom, purchasedDate;
        EquipmentType type;
        double cost;

        ArrayList<Equipment> equipment = equipmentInventory.checkEquipment(COMMAND_STRINGS.get(0));

        return new CommandResult(String.format(successMessage, COMMAND_STRINGS.get(0)), equipment);
    }
}
