package seedu.command;

import seedu.equipment.EquipmentType;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

/**
 * Parent class to AddCommand and UpdateCommand which have use for the same prepareModification method.
 */
public class ModificationCommand extends Command {
    public static final String IMPLEMENTED_BY_CHILD = "Execute method for Modification should be implemented by "
            + "child classes AddCommand and UpdateCommand";
    public static final String INVALID_TYPE_MESSAGE = "Equipment Type needs to be one of:" + System.lineSeparator()
            + EquipmentType.getAllTypes();
    public static final String INVALID_COST_MESSAGE = "Cost needs to be able to be parsed to a double";
    public static final String INVALID_DATE_MESSAGE = "Date needs to be able to be parsed, make sure you're using "
            + "YYYY-MM-DD format";
    protected final ArrayList<String> commandStrings;
    protected String serialNumber;
    protected String equipmentName = null;
    protected LocalDate purchasedDate = null;
    protected EquipmentType equipmentType = null;
    protected String purchasedFrom = null;
    protected Double cost = null;

    public ModificationCommand(ArrayList<String> commandStrings) {
        this.commandStrings = commandStrings;
    }

    public CommandResult execute() {
        return new CommandResult(IMPLEMENTED_BY_CHILD);
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public void setPurchasedDate(String purchasedDate) throws DateTimeParseException {
        this.purchasedDate = LocalDate.parse(purchasedDate);
    }

    public void setEquipmentType(String equipmentType) throws IllegalArgumentException {
        this.equipmentType = EquipmentType.valueOf(equipmentType.toUpperCase(Locale.ROOT));
    }

    public void setPurchasedFrom(String purchasedFrom) {
        this.purchasedFrom = purchasedFrom;
    }

    public void setCost(String cost) throws NumberFormatException {
        this.cost = Double.valueOf(cost);
    }

    /**
     * Set up ModificationCommand with arguments required to update a given item
     *
     * <p>Should multiple arguments specifying the same argument parameter (e.g. 'c/1000' and 'c/2000') be given,
     * the previous arguments passed in will be overwritten by the most recent parameter ('c/2000' in example).
     *
     * @throws NumberFormatException if cost is invalid
     * @throws IllegalArgumentException if EquipmentType is invalid
     *
     */
    protected void prepareModification() throws AssertionError, NumberFormatException, IllegalArgumentException,
            DateTimeParseException {
        for (String s : commandStrings) {
            int delimiterPos = s.indexOf('/');
            // the case where delimiterPos = -1 is impossible as
            // ARGUMENT_FORMAT and ARGUMENT_TRAILING_FORMAT regex requires a '/'
            assert delimiterPos != -1 : "Each args will need to include minimally a '/' to split arg and value upon";
            String argType = s.substring(0, delimiterPos);
            String argValue = s.substring(delimiterPos + 1);
            switch (argType) {
            case "n":
                setEquipmentName(argValue);
                break;
            case "pd":
                setPurchasedDate(argValue);
                break;
            case "t":
                setEquipmentType(argValue);
                break;
            case "pf":
                setPurchasedFrom(argValue);
                break;
            case "c":
                setCost(argValue);
                break;
            case "s":
                setSerialNumber(argValue);
                break;
            default:
                System.out.println("`" + argValue + "` not accepted for type " + argType + ": Unrecognised Tag");
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ModificationCommand that = (ModificationCommand) o;
        return serialNumber.equals(that.serialNumber)
                && Objects.equals(equipmentName, that.equipmentName)
                && Objects.equals(purchasedDate, that.purchasedDate)
                && Objects.equals(equipmentType, that.equipmentType)
                && Objects.equals(purchasedFrom, that.purchasedFrom)
                && Objects.equals(cost, that.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber, equipmentName, purchasedDate, equipmentType, purchasedFrom, cost);
    }
}
