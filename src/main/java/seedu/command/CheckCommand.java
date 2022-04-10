package seedu.command;

import seedu.Pair;
import seedu.equipment.Equipment;
import seedu.equipment.EquipmentType;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Subclass of Command. Handles checking of details of a specified equipment from equipmentInventory.
 */
public class CheckCommand extends Command {
    private final ArrayList<String> commandStrings;
    public static final String COMMAND_WORD = "check";
    public static final String COMMAND_DESCRIPTION = ": Check the details of the equipments that matches the specified"
            + " parameter. "
            + System.lineSeparator()
            + "Parameters: parameter/`PARAMETER_VALUE`" + System.lineSeparator()
            + "Example: "
            + "check n/`MixerC` or check s/`SM57-1` or check t/`MICROPHONE`";

    /**
     * constructor for CheckCommand. Initialises successMessage and usageReminder from Command
     *
     * @param commandStrings parsed user input which contains details of equipment to be viewed
     */
    public CheckCommand(ArrayList<String> commandStrings) {
        this.commandStrings = commandStrings;
        successMessage = "Here are the equipment matching to '%1$s':" + System.lineSeparator();
        usageReminder = COMMAND_WORD + COMMAND_DESCRIPTION;
    }

    /**
     * Gives details of equipment specified by name.
     *
     * @return CommandResult with message from execution of this command
     */
    public CommandResult execute() {
        ArrayList<Equipment> equipment;
        try {
            Pair<String, ?> checkPair = generateCheckPair();
            equipment = equipmentManager.checkEquipment(checkPair);
        } catch (DateTimeParseException e) {
            return new CommandResult(INVALID_DATE_MESSAGE);
        } catch (NumberFormatException e) {
            return new CommandResult(INCORRECT_COST_FORMAT);
        } catch (IllegalArgumentException e) {
            return new CommandResult(INCORRECT_ENUM_TYPE);
        }

        return new CommandResult(String.format(successMessage, commandStrings.get(0)), equipment);
    }

    /**
     * Generates a Pair containing the attribute for the check to be based on.
     * Pair is a class that was implemented to match each attribute to its value.
     * By using pair, we are able to specify the attribute to be checked.
     * @return Pair of value to be checked and its matching attribute.
     * @throws NumberFormatException if cost is invalid
     * @throws IllegalArgumentException if EquipmentType is invalid
     * @throws DateTimeParseException if date is not in YYYY-MM-DD format
     *
     */
    public Pair<String, ?> generateCheckPair() throws AssertionError, NumberFormatException, IllegalArgumentException,
        DateTimeParseException {
        Pair<String, ?> pair = null;
      
        String s = commandStrings.get(0);
        int delimiterPos = s.indexOf('/');
        // the case where delimiterPos = -1 is impossible as
        // ARGUMENT_FORMAT and ARGUMENT_TRAILING_FORMAT regex requires a '/'
        assert delimiterPos != -1 : "Each args will need to include minimally a '/' to split arg and value upon";
        String argType = s.substring(0, delimiterPos);
        String argValue = s.substring(delimiterPos + 1);
        switch (argType) {
        case "n":
            pair = new Pair<>("itemName", argValue);
            break;
        case "pd":
            pair = new Pair<>("purchasedDate", LocalDate.parse(argValue));
            break;
        case "t":
            pair = new Pair<>("type", EquipmentType.valueOf(argValue.toUpperCase(Locale.ROOT)));
            break;
        case "pf":
            pair = new Pair<>("purchasedFrom", argValue);
            break;
        case "c":
            pair = new Pair<>("cost", Double.valueOf(argValue));
            break;
        case "s":
            pair = new Pair<>("serialNumber", argValue);
            break;
        default:
            System.out.println("`" + argValue + "` not accepted for type " + argType + ": Unrecognised Tag");
        }

        return pair;
    }
}
