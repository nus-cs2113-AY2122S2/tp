package seedu.command;

import seedu.Pair;
import seedu.parser.IncompleteCommandException;

import java.util.ArrayList;
import java.util.Objects;

public class UpdateCommand extends Command {
    private final ArrayList<String> COMMAND_STRINGS;
    public static final String COMMAND_WORD = "update";
    public static final String COMMAND_DESCRIPTION = ": Updates the equipment with the specified serial number. "
            + "Parameters: s/SERIAL_NUMBER" + System.lineSeparator()
            + "Example: "
            + "update s/SM57-1";
    private String serialNumber;

    private String updateName = null, purchaseDate = null, type = null, purchaseFrom = null, cost = null;


    /**
     * constructor for UpdateCommand. Initialises successMessage and usageReminder from Command
     */
    public UpdateCommand(ArrayList<String> commandStrings) {
        COMMAND_STRINGS = commandStrings;
        successMessage = "Equipment successfully updated for serial number %1$s,"
                + System.lineSeparator()
                + "Updated details are: %2$s";
        usageReminder = COMMAND_WORD + COMMAND_DESCRIPTION;

        prepareUpdate();
    }

    public CommandResult execute() {
        if (getSerialNumber() == null)
            return new CommandResult(MISSING_SERIAL_NUMBER);

        ArrayList<Pair<String, String>> updatePairs = generateUpdatePairs();
        equipmentManager.updateEquipment(serialNumber, updatePairs);

        return new CommandResult(String.format(successMessage, serialNumber, generateUpdateString()));
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPurchaseFrom(String purchaseFrom) {
        this.purchaseFrom = purchaseFrom;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public ArrayList<Pair<String, String>> generateUpdatePairs() {
        ArrayList<Pair<String, String>> pairs = new ArrayList<>();
        if (updateName != null) {
            pairs.add(new Pair<>("itemName", updateName));
        }
        if (type != null) {
            pairs.add(new Pair<>("type", type));
        }
        if (cost != null) {
            pairs.add(new Pair<>("cost", cost));
        }
        if (purchaseDate != null) {
            pairs.add(new Pair<>("purchasedDate", purchaseDate));
        }
        if (purchaseFrom != null) {
            pairs.add(new Pair<>("purchasedFrom", purchaseFrom));
        }

        return pairs;
    }

    public String generateUpdateString() {
        String updateDetails = "";
        if (updateName != null) {
            updateDetails = updateDetails + System.lineSeparator() + "New name: " + updateName;
        }
        if (type != null) {
            updateDetails = updateDetails + System.lineSeparator() + "New type: " + type;
        }
        if (cost != null) {
            updateDetails = updateDetails + System.lineSeparator() + "New cost: " + cost;
        }
        if (purchaseDate != null) {
            updateDetails = updateDetails + System.lineSeparator() + "New purchase date: " + purchaseDate;
        }
        if (purchaseFrom != null) {
            updateDetails = updateDetails + System.lineSeparator() + "New purchased from: " + purchaseFrom;
        }

        return updateDetails;
    }

    /**
     * Set up UpdateCommand with arguments required to update a given item
     * <p>
     * Should multiple arguments specifying the same argument parameter (e.g. 'c/1000' and 'c/2000') be given,
     * the previous arguments passed in will be overwritten by the most recent parameter ('c/2000' in example).
     *
     * @return Command object
     */
    protected void prepareUpdate() throws AssertionError {
        for (String s : COMMAND_STRINGS) {
            int delimiterPos = s.indexOf('/');
            // the case where delimiterPos = -1 is impossible as
            // ARGUMENT_FORMAT and ARGUMENT_TRAILING_FORMAT regex requires a '/'
            assert delimiterPos != -1 : "Each args will need to include minimally a '/' to split arg and value upon";
            String argType = s.substring(0, delimiterPos);
            String argValue = s.substring(delimiterPos + 1);
            switch (argType) {
            case "n":
                setUpdateName(argValue);
                break;
            case "pd":
                setPurchaseDate(argValue);
                break;
            case "t":
                setType(argValue);
                break;
            case "pf":
                setPurchaseFrom(argValue);
                break;
            case "c":
                setCost(argValue);
                break;
            case "s":
                setSerialNumber(argValue);
                break;
            default:
                System.out.println("`" + argValue + "` not updated for type " + argType + ": Unrecognised Tag");
            }
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateCommand that = (UpdateCommand) o;
        return serialNumber.equals(that.serialNumber) && Objects.equals(updateName, that.updateName) && Objects.equals(purchaseDate, that.purchaseDate) && Objects.equals(type, that.type) && Objects.equals(purchaseFrom, that.purchaseFrom) && Objects.equals(cost, that.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber, updateName, purchaseDate, type, purchaseFrom, cost);
    }
}
