package seedu.command;

import java.util.ArrayList;

/**
 * Subclass of Command. Handles deleting of equipment from equipmentInventory.
 */
public class DeleteCommand extends Command {
    private final ArrayList<String> COMMAND_STRINGS;
    public final String COMMAND_WORD = "delete";

    /**
     * constructor for DeleteCommand. Initialises successMessage and usageReminder from Command
     * @param commandStrings parsed user input which contains details of equipment to be deleted
     */
    public DeleteCommand(ArrayList<String> commandStrings){
        COMMAND_STRINGS = commandStrings;
        successMessage = "Equipment successfully deleted: %1$s, serial number %2$s";
        usageReminder = COMMAND_WORD + ": Deletes the equipment with the specified serial number. "
                + "Parameters: s/SERIAL_NUMBER\n"
                + "Example: "
                + "delete s/SM57-1";
    }

    /**
     * Deletes equipment specified by serial number
     * @return CommandResult with message from execution of this command
     */
    public CommandResult execute(){
        String equipmentName = equipmentInventory.getEquipmentList().get(COMMAND_STRINGS.get(0));
        equipmentInventory.deleteEquipment(COMMAND_STRINGS.get(0));

        return new CommandResult(String.format(successMessage, equipmentName,COMMAND_STRINGS.get(1)));
    }
}
