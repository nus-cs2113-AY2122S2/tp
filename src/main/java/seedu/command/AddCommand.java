package seedu.command;

import seedu.equipment.DuplicateSerialNumber;
import seedu.equipment.EquipmentType;

import java.util.ArrayList;

/**
 * Subclass of Command. Handles adding of equipment to equipmentInventory.
 */
public class AddCommand extends Command {
    private final ArrayList<String> COMMAND_STRINGS;
    public static final String COMMAND_WORD = "add";
    public static final String COMMAND_DESCRIPTION = ": Adds a Equipment to the equipmentInventory. "
            + "Parameters: n/ITEM_NAME s/SERIAL_NUMBER t/TYPE c/COST pf/PURCHASED_FROM pd/PURCHASED_DATE" + System.lineSeparator()
            + "Example: "
            + "add n/SpeakerB s/S1404115ASF t/Speaker c/1000 pf/Loud_Technologies pd/2022-02-23";
    public static final String DUPLICATE_ITEM_ERROR = "There is already an item with this serial number: %1$s";

    /**
     * constructor for AddCommand. Initialises successMessage and usageReminder from Command
     *
     * @param commandStrings parsed user input which contains details of equipment to be added
     */
    public AddCommand(ArrayList<String> commandStrings) {
        this.COMMAND_STRINGS = commandStrings;
        successMessage = "Equipment successfully added: %1$s, serial number %2$s";
        usageReminder = COMMAND_WORD + COMMAND_DESCRIPTION;
    }

    /**
     * Adds equipment with details given in COMMAND_STRINGS
     *
     * @return CommandResult with message from execution of this command
     */
    public CommandResult execute() {
        try {
            addEquipment(COMMAND_STRINGS);
        } catch (DuplicateSerialNumber e) {
            return new CommandResult(String.format(DUPLICATE_ITEM_ERROR, COMMAND_STRINGS.get(1)));
        } catch (NullPointerException | NumberFormatException e) {
            return new CommandResult(INCORRECT_COST_FORMAT);
        } catch (IllegalArgumentException e) {
            return new CommandResult(INCORRECT_ENUM_TYPE);
        }

        return new CommandResult(String.format(successMessage, COMMAND_STRINGS.get(0), COMMAND_STRINGS.get(1)));
    }

    /**
     * Uses information from the ArrayList to create a new Equipment and add it to the inventory.
     *
     * @param userInput ArrayList of String which contains the individual attributes of the equipment to be added
     */
    public void addEquipment(ArrayList<String> userInput) throws DuplicateSerialNumber {
        String equipmentName = userInput.get(0);
        String serialNumber = userInput.get(1);
        EquipmentType type = EquipmentType.valueOf(userInput.get(2));
        double cost = Double.parseDouble(userInput.get(3));
        String purchasedFrom = userInput.get(4);
        String purchasedDate = userInput.get(5);

        equipmentManager.addEquipment(equipmentName, serialNumber, type, cost, purchasedFrom, purchasedDate);
    }
}
