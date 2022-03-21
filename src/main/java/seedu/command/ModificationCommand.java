package seedu.command;

import java.util.ArrayList;

/**
 * Abstract class acting as parent class to AddCommand and UpdateCommand which have use for the same prepareModification method.
 */
public abstract class ModificationCommand extends Command {
    protected final ArrayList<String> commandStrings;
    protected String serialNumber;
    protected String equipmentName = null;
    protected String purchasedDate = null;
    protected String type = null;
    protected String purchasedFrom = null;
    protected String cost = null;

    public ModificationCommand(ArrayList<String> commandStrings) {
        this.commandStrings = commandStrings;
    }

    public abstract CommandResult execute();

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public void setPurchasedDate(String purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPurchasedFrom(String purchasedFrom) {
        this.purchasedFrom = purchasedFrom;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    /**
     * Set up UpdateCommand with arguments required to update a given item
     * <p>
     * Should multiple arguments specifying the same argument parameter (e.g. 'c/1000' and 'c/2000') be given,
     * the previous arguments passed in will be overwritten by the most recent parameter ('c/2000' in example).
     */
    protected void prepareModification() throws AssertionError {
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
                setType(argValue);
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
}
