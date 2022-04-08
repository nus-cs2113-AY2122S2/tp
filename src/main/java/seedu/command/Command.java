package seedu.command;

import seedu.equipment.EquipmentManager;
import seedu.equipment.EquipmentType;
import seedu.storage.Storage;

/**
 * Abstract parent class for all types of commands.
 */
// The Command class as well as all of its subclasses was created with reference to
// https://github.com/nus-cs2113-AY2122S2/personbook/tree/master/src/main/java/seedu/personbook/commands
public abstract class Command {
    protected String successMessage;
    public String usageReminder;
    protected EquipmentManager equipmentManager;
    protected Storage storage;
    public static final String INCORRECT_ENUM_TYPE = "Equipment Type needs to be one of:" + System.lineSeparator()
            + EquipmentType.getAllTypes();
    public static final String INCORRECT_COST_FORMAT = "Please enter numbers only for cost and omit symbols";
    public static final String INVALID_SERIAL_NUMBER = "No such serial number, please enter an existing "
            + "serial number";
    public static final String INVALID_DATE_MESSAGE = "Date needs to be able to be parsed, make sure you're using "
            + "YYYY-MM-DD format";
    public static final String MISSING_SERIAL_NUMBER = "Serial Number is required to run this command";

    public abstract CommandResult execute();

    public String getUsageReminder() {
        return usageReminder;
    }

    public void setEquipmentManager(EquipmentManager equipmentManager) {
        this.equipmentManager = equipmentManager;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
