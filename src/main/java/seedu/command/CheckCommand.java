package seedu.command;

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
        successMessage = "Name:  %1$s\n"
                + "Type:  %2$s\n"
                + "Cost:  %3$f\n"
                + "S/N:  %4$s\n"
                + "Purchased From:  %5$s\n"
                + "Purchase Date:  %6$s\n";
        usageReminder = COMMAND_WORD + COMMAND_DESCRIPTION;
    }

    /**
     * Gives details of equipment specified by name
     * @return CommandResult with message from execution of this command
     */
    public CommandResult execute(){
        String name, type, sn, purchasedFrom, purchasedDate;
        double cost;

        Equipment equipment = equipmentInventory.checkEquipment(COMMAND_STRINGS.get(0));
        name = equipment.getItemName();
        type = equipment.getType();
        sn = equipment.getSerialNumber();
        purchasedFrom = equipment.getPurchasedFrom();
        purchasedDate = equipment.getPurchasedDate();
        cost = equipment.getCost();

        return new CommandResult(String.format(successMessage, name, type, cost, sn, purchasedFrom, purchasedDate));
    }
}
