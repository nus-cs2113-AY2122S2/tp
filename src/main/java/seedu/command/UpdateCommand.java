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
        successMessage = "Equipment successfully updated for serial number %1$s,"
                + System.lineSeparator()
                + "Updated details are: %2$s";
        usageReminder = COMMAND_WORD + COMMAND_DESCRIPTION;
    }

    public CommandResult execute() {
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

    public String generateUpdateString(){
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
}
