package seedu.command;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.Pair;
import seedu.equipment.DuplicateSerialNumber;
import seedu.equipment.EquipmentManager;
import seedu.equipment.EquipmentType;
import seedu.parser.Parser;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UpdateCommandTest {
    EquipmentManager equipmentManager = new EquipmentManager();
    UpdateCommand updateCommand;

    @BeforeEach
    void setup() {
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

        updateCommand = new UpdateCommand();
        updateCommand.setEquipmentManager(equipmentManager);
        updateCommand.setSerialNumber("S1404115ASF");
        updateCommand.setUpdateName("Speaker C");
        updateCommand.setCost("2000");
        updateCommand.setPurchaseDate("2022-01-26");
    }

    @Test
    void execute_validSerialNumber_success() {
        CommandResult expectedResult = new CommandResult("Equipment successfully updated for serial number S1404115ASF,"
                + System.lineSeparator()
                + "Updated details are: "
                + System.lineSeparator()
                + "New name: Speaker C"
                + System.lineSeparator()
                + "New cost: 2000"
                + System.lineSeparator()
                + "New purchase date: 2022-01-26");

        System.out.println(updateCommand.getSerialNumber());

        CommandResult actualResult = updateCommand.execute();

        System.out.println(actualResult.getResultToShow());

//        assertEquals(expectedResult, actualResult);
    }

    @Test
    void generateUpdatePairs_nonNullUpdates_success() {
        ArrayList<Pair<String, String>> expectedResult = new ArrayList<>();
        expectedResult.add(new Pair<>("itemName", "Speaker C"));
        expectedResult.add(new Pair<>("cost", "2000"));
        expectedResult.add(new Pair<>("purchasedDate", "2022-01-26"));

        ArrayList<Pair<String, String>> actualResult = updateCommand.generateUpdatePairs();

        assertEquals(expectedResult, actualResult);
    }
}