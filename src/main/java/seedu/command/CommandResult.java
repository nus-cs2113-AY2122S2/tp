package seedu.command;

import seedu.equipment.Equipment;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
// Created with reference to
// https://github.com/nus-cs2113-AY2122S2/personbook/tree/master/src/main/java/seedu/personbook/commands
public class CommandResult {
    private final String resultToShow;
    private final ArrayList<Equipment> relevantEquipment;

    public CommandResult(String result) {
        resultToShow = result;
        relevantEquipment = null;
    }

    public CommandResult(String result, ArrayList<Equipment> equipmentList) {
        resultToShow = result;
        relevantEquipment = equipmentList;
    }

    public String getResultToShow() {
        return resultToShow;
    }

    public ArrayList<Equipment> getRelevantEquipment() {
        return relevantEquipment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommandResult that = (CommandResult) o;
        return resultToShow.equals(that.resultToShow) && Objects.equals(relevantEquipment, that.relevantEquipment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultToShow, relevantEquipment);
    }
}
