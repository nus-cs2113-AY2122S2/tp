package seedu.command;

import seedu.equipment.EquipmentManager;

import java.util.HashMap;

/**
 * Abstract parent class for all types of commands
 */
// The Command class as well as all of its subclasses was created with reference to
// https://github.com/nus-cs2113-AY2122S2/personbook/tree/master/src/main/java/seedu/personbook/commands
public abstract class Command {
    protected String successMessage;
    public String usageReminder;
    protected EquipmentManager equipmentInventory;
    protected final String INCORRECT_ENUM_TYPE = "Wrong type of equipment. The allowed types are: MICROPHONE, SPEAKER, STAND, CABLE";
    protected final String INCORRECT_COST_FORMAT = "Please enter numbers only for cost and omit symbols";

    public abstract CommandResult execute();

    public String getUsageReminder(){
        return usageReminder;
    }
}
