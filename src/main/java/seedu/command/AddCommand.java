package seedu.command;

import seedu.equipment.Equipment;
import seedu.equipment.DuplicateSerialNumberException;
import seedu.equipment.EquipmentType;
import seedu.parser.MissingAttributeException;

import java.util.ArrayList;

/**
 * Subclass of Command. Handles adding of equipment to equipmentInventory.
 */
public class AddCommand extends ModificationCommand {
    public static final String COMMAND_WORD = "add";
    public static final String COMMAND_DESCRIPTION = ": Adds a Equipment to the equipmentInventory. "
            + System.lineSeparator()
            + "Parameters: n/`ITEM NAME` s/`SERIAL NUMBER` t/`TYPE` c/`COST` pf/`PURCHASED FROM` pd/`PURCHASED DATE`"
            + System.lineSeparator()
            + "Example: "
            + "add n/`SpeakerB` s/`S1404115ASF` t/`Speaker` c/`1000` pf/`Loud_Technologies` pd/`2022-02-23`";
    public static final String DUPLICATE_ITEM_ERROR = "There is already an item with this serial number: %1$s";
    public static final String ATTRIBUTE_NOT_SET_ERROR = "Unable to add. "
            + "One or more than one of the attributes of Equipment is not specified.";
    private static final int EXPECTED_NUMBER_OF_TAGS = 6;

    /**
     * constructor for AddCommand. Initialises successMessage and usageReminder from Command
     *
     * @param commandStrings parsed user input which contains details of equipment to be added
     */
    public AddCommand(ArrayList<String> commandStrings) throws MissingAttributeException {
        super(commandStrings);
        successMessage = "Equipment successfully added: %1$s, serial number %2$s";
        usageReminder = COMMAND_WORD + COMMAND_DESCRIPTION;

        if (commandStrings.size() < EXPECTED_NUMBER_OF_TAGS) {
            throw new MissingAttributeException(ATTRIBUTE_NOT_SET_ERROR);
        } else {
            prepareModification();
        }

    }

    /**
     * Adds equipment with details given in COMMAND_STRINGS.
     *
     * @return CommandResult with message from execution of this command
     */
    public CommandResult execute() {
        if (!checkAttributes()) {
            return new CommandResult(ATTRIBUTE_NOT_SET_ERROR
                    + System.lineSeparator()
                    + usageReminder);
        }

        try {
            addEquipment(commandStrings);
        } catch (DuplicateSerialNumberException e) {
            return new CommandResult(String.format(DUPLICATE_ITEM_ERROR, serialNumber));
        } catch (NullPointerException | NumberFormatException e) {
            return new CommandResult(INCORRECT_COST_FORMAT);
        } catch (IllegalArgumentException e) {
            return new CommandResult(INCORRECT_ENUM_TYPE);
        }

        return new CommandResult(String.format(successMessage, equipmentName, serialNumber));
    }

    /**
     * Uses information from the ArrayList to create a new Equipment and add it to the inventory.
     *
     * @param userInput ArrayList of String which contains the individual attributes of the equipment to be added
     */
    public void addEquipment(ArrayList<String> userInput) throws DuplicateSerialNumberException {

        Equipment newEquipment = new Equipment(equipmentName, serialNumber, equipmentType, cost, purchasedFrom,
                purchasedDate);
        equipmentManager.addEquipment(newEquipment);
    }

    /**
     * Checks if all the attributes of Equipment to be added have been set. There should be no null values.
     * @return boolean to indicate whether all attributes are set.
     */
    public boolean checkAttributes() {
        if (equipmentName == null || serialNumber == null || equipmentType == null || cost == null
                || purchasedFrom == null || purchasedDate == null) {
            return false;
        }
        return true;
    }
}
