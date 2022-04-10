package seedu.command;

import org.junit.jupiter.api.Test;
import seedu.equipment.DuplicateSerialNumberException;
import seedu.equipment.EquipmentManager;
import seedu.equipment.EquipmentType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ListCommandTest {
    ListCommand listCommand;

    @Test
    void execute_noSpecifiedType_success() {
        listCommand = new ListCommand();
        listCommand.setEquipmentManager(new EquipmentManager());
        EquipmentManager equipmentManager = listCommand.equipmentManager;
        try {
            equipmentManager.addEquipment("Speaker B",
                    "S1404115ASF",
                    EquipmentType.valueOf("SPEAKER"),
                    1000,
                    "Loud Technologies",
                    LocalDate.parse("2022-02-23"));
        } catch (DuplicateSerialNumberException e) {
            fail();
        }

        CommandResult actualResult = listCommand.execute();
        CommandResult expectedResult = new CommandResult("TOTAL QUANTITY OF EQUIPMENT: 1" + System.lineSeparator(),
                equipmentManager.listEquipment());

        assertEquals(expectedResult, actualResult);
    }
}