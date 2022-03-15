package seedu.command;

import seedu.Pair;

import java.util.ArrayList;

public class UpdateCommand extends Command {
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
    public UpdateCommand() {
        successMessage = "Equipment successfully updated: %1$s for serial number %2$s";
        usageReminder = COMMAND_WORD + COMMAND_DESCRIPTION;
    }

    public CommandResult execute() {
        ArrayList<Pair<String, String>> updatePairs = generateUpdatePairs();
        successMessage = equipmentManager.updateEquipment(serialNumber, updatePairs);

        return new CommandResult(successMessage);
    }

    public void setSerialNumber(String serialNumber) {
        this.updateName = serialNumber;
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
            pairs.add(new Pair<String, String>("itemName", updateName));
        }
        if (type != null) {
            pairs.add(new Pair<String, String>("type", type));
        }
        if (cost != null) {
            pairs.add(new Pair<String, String>("cost", cost));
        }
        if (purchaseDate != null) {
            pairs.add(new Pair<String, String>("purchasedDate", purchaseDate));
        }
        if (purchaseFrom != null) {
            pairs.add(new Pair<String, String>("purchasedFrom", purchaseFrom));
        }

        return pairs;
    }
}
