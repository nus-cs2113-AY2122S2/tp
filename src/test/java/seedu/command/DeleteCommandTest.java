package seedu.command;

import org.junit.jupiter.api.Test;
import seedu.equipment.DuplicateSerialNumber;
import seedu.equipment.EquipmentManager;
import seedu.equipment.EquipmentType;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DeleteCommandTest {
    DeleteCommand deleteCommand;

    @Test
    void execute_validSerialNumber_success() {
        deleteCommand = new DeleteCommand(new ArrayList<>(
                Arrays.asList("S1404115ASF")
        ));
        deleteCommand.setEquipmentManager(new EquipmentManager());
        EquipmentManager equipmentManager = deleteCommand.equipmentManager;
        try {
            equipmentManager.addEquipment("Speaker B",
                    "S1404115ASF",
                    EquipmentType.valueOf("SPEAKER"),
                    1000,
                    "Loud Technologies",
                    "2022-02-23");
        } catch (DuplicateSerialNumber e) {
            fail();
        }

        int equipmentListSize = equipmentManager.getEquipmentList().size();
        assertEquals(1, equipmentListSize);

        CommandResult actualResult = deleteCommand.execute();
        CommandResult expectedResult = new CommandResult("Equipment successfully deleted: Speaker B, serial number S1404115ASF");

        assertEquals(expectedResult, actualResult);
    }
}