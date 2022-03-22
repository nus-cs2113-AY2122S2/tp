package seedu.command;

import seedu.Pair;

import java.util.ArrayList;
import java.util.Objects;

public class UpdateCommand extends ModificationCommand {
    public static final String UPDATE_FAILURE_MESSAGE = "Equipment was not updated successfully.";
    public static final String COMMAND_WORD = "update";
    public static final String COMMAND_DESCRIPTION = ": Updates the equipment with the specified serial number. "
            + System.lineSeparator()
            + "Parameters: s/SERIAL_NUMBER(required) n/ITEM_NAME t/TYPE c/COST pf/PURCHASED_FROM pd/PURCHASED_DATE"
            + System.lineSeparator()
            + "Example: "
            + "update s/SM57-1 n/SpeakerC c/2510 pd/2022-08-21";


    /**
     * constructor for UpdateCommand. Initialises successMessage and usageReminder from Command
     */
    public UpdateCommand(ArrayList<String> commandStrings) {
        super(commandStrings);
        successMessage = "Equipment successfully updated for serial number %1$s,"
                + System.lineSeparator()
                + "Updated details are: %2$s";
        usageReminder = COMMAND_WORD + COMMAND_DESCRIPTION;

        prepareModification();
    }

    @Override
    public CommandResult execute() {
        if (getSerialNumber() == null) {
            return new CommandResult(MISSING_SERIAL_NUMBER);
        }

        ArrayList<Pair<String, ?>> updatePairs = generateUpdatePairs();
        if (!equipmentManager.updateEquipment(serialNumber, updatePairs)) {
            return new CommandResult(UPDATE_FAILURE_MESSAGE);
        }

        return new CommandResult(String.format(successMessage, serialNumber, generateUpdateString()));
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public ArrayList<Pair<String, ?>> generateUpdatePairs() {
        ArrayList<Pair<String, ?>> pairs = new ArrayList<>();
        if (equipmentName != null) {
            pairs.add(new Pair<>("itemName", equipmentName));
        }
        if (type != null) {
            pairs.add(new Pair<>("type", type));
        }
        if (cost != null) {
            pairs.add(new Pair<>("cost", cost));
        }
        if (purchasedDate != null) {
            pairs.add(new Pair<>("purchasedDate", purchasedDate));
        }
        if (purchasedFrom != null) {
            pairs.add(new Pair<>("purchasedFrom", purchasedFrom));
        }

        return pairs;
    }

    public String generateUpdateString() {
        String updateDetails = "";
        if (equipmentName != null) {
            updateDetails = updateDetails + System.lineSeparator() + "New name: " + equipmentName;
        }
        if (type != null) {
            updateDetails = updateDetails + System.lineSeparator() + "New type: " + type;
        }
        if (cost != null) {
            updateDetails = updateDetails + System.lineSeparator() + "New cost: " + cost;
        }
        if (purchasedDate != null) {
            updateDetails = updateDetails + System.lineSeparator() + "New purchase date: " + purchasedDate;
        }
        if (purchasedFrom != null) {
            updateDetails = updateDetails + System.lineSeparator() + "New purchased from: " + purchasedFrom;
        }

        return updateDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UpdateCommand that = (UpdateCommand) o;
        return serialNumber.equals(that.serialNumber)
                && Objects.equals(equipmentName, that.equipmentName)
                && Objects.equals(purchasedDate, that.purchasedDate)
                && Objects.equals(type, that.type)
                && Objects.equals(purchasedFrom, that.purchasedFrom)
                && Objects.equals(cost, that.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber, equipmentName, purchasedDate, type, purchasedFrom, cost);
    }
}
