package seedu.command;

import seedu.equipment.Equipment;

import java.util.ArrayList;

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

    public String getResultToShow(){
        return RESULT_TO_SHOW;
    }

    public ArrayList<Equipment> getRelevantEquipment(){
        return RELEVANT_EQUIPMENT;
    }
}
