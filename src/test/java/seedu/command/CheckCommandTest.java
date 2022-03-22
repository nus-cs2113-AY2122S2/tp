package seedu.command;

import org.junit.jupiter.api.Test;
import seedu.equipment.DuplicateSerialNumberException;
import seedu.equipment.Equipment;
import seedu.equipment.EquipmentManager;
import seedu.equipment.EquipmentType;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class CheckCommandTest {
    CheckCommand checkCommand;

    @Test
    void execute_validEquipmentName_success() {
        checkCommand = new CheckCommand(new ArrayList<>(
                Arrays.asList("Speaker B")
        ));
        checkCommand.setEquipmentManager(new EquipmentManager());
        EquipmentManager equipmentManager = checkCommand.equipmentManager;
        try {
            equipmentManager.addEquipment("Speaker B",
                    "S1404115ASF",
                    EquipmentType.valueOf("SPEAKER"),
                    1000,
                    "Loud Technologies",
                    "2022-02-23");
        } catch (DuplicateSerialNumberException e) {
            fail();
        }

        ArrayList<Equipment> listOfEquipments = new ArrayList<>();
        for (Equipment equipment : equipmentManager.getEquipmentList().values()) {
            if (equipment.getItemName().equals("Speaker B")) {
                listOfEquipments.add(equipment);
            }
        }

        CommandResult actualResult = checkCommand.execute();
        CommandResult expectedResult = new CommandResult(
                "Here are the equipment matching to 'Speaker B':" + System.lineSeparator(),
                listOfEquipments);

        assertEquals(expectedResult, actualResult);
    }
}