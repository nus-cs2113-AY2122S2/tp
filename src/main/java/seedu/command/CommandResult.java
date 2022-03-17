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
    private final String RESULT_TO_SHOW;
    private final ArrayList<Equipment> RELEVANT_EQUIPMENT;

    public CommandResult(String result) {
        RESULT_TO_SHOW = result;
        RELEVANT_EQUIPMENT = null;
    }

    public CommandResult(String result, ArrayList<Equipment> equipmentList) {
        RESULT_TO_SHOW = result;
        RELEVANT_EQUIPMENT = equipmentList;
    }

    public String getResultToShow() {
        return RESULT_TO_SHOW;
    }

    public ArrayList<Equipment> getRelevantEquipment() {
        return RELEVANT_EQUIPMENT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandResult that = (CommandResult) o;
        return RESULT_TO_SHOW.equals(that.RESULT_TO_SHOW) && Objects.equals(RELEVANT_EQUIPMENT, that.RELEVANT_EQUIPMENT);
    }

    @Override
    public int hashCode() {
        return Objects.hash(RESULT_TO_SHOW, RELEVANT_EQUIPMENT);
    }
}
