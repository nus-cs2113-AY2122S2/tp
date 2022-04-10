package seedu.command;

import seedu.equipment.Equipment;
import seedu.equipment.EquipmentType;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Subclass of Command. Handles listing of all equipment or listing of equipment of specified type.
 */
public class ListCommand extends Command {
    private final ArrayList<String> commandStrings;
    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_DESCRIPTION = ": Prints a list of all equipment in the inventory. "
            + System.lineSeparator()
            + "Parameters: NIL" + System.lineSeparator()
            + "Example: "
            + "list";

    /**
     * constructor for ListCommand with NO specified type. Initialises successMessage and usageReminder from Command
     */
    public ListCommand() {
        this.commandStrings = null;
        successMessage = "TOTAL QUANTITY OF EQUIPMENT: %1$d" + System.lineSeparator();
        usageReminder = COMMAND_WORD + COMMAND_DESCRIPTION;
    }


    /**
     * List all equipment.
     *
     * @return CommandResult with message from execution of this command
     */
    public CommandResult execute() {
        int listSize;
        ArrayList<Equipment> equipmentArrayList;
        equipmentArrayList = equipmentManager.listEquipment();
        listSize = equipmentArrayList.size();

        return new CommandResult(String.format(successMessage, listSize), equipmentArrayList);
    }

    /**
     * Implement equals for JUnit comparisons
     * Source: https://www.geeksforgeeks.org/overriding-equals-method-in-java/
     * @param o Any object
     * @return If two commands are the same
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ListCommand that = (ListCommand) o;
        return Objects.equals(commandStrings, that.commandStrings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandStrings);
    }
}
