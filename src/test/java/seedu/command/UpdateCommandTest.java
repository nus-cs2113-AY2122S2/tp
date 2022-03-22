package seedu.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.Pair;
import seedu.equipment.DuplicateSerialNumberException;
import seedu.equipment.EquipmentManager;
import seedu.equipment.EquipmentType;
import seedu.parser.IncompleteCommandException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
        } catch (DuplicateSerialNumberException e) {
            fail();
        }

        updateCommand = new UpdateCommand(new ArrayList<>(
                Arrays.asList("s/S1404115ASF", "n/Speaker C", "c/2000", "pd/2022-01-26")
        ));
        updateCommand.setEquipmentManager(equipmentManager);
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

        CommandResult actualResult = updateCommand.execute();

        assertEquals(expectedResult, actualResult);
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

//    @Test
//    void prepareModification_missingSlashDelimiter_assertionErrorThrown() throws AssertionError {
//        ArrayList<String> testArrayList = new ArrayList<>(Collections.singleton("thiswillnotwork"));
//        try {
//            updateCommand = new UpdateCommand(testArrayList);
//        } catch (AssertionError error) {
//            assertEquals("Each args will need to include minimally a '/' to split arg and value upon", error.getMessage());
//        }
//    }
//
//    @Test
//    void prepareModification_missingSerialNumber_exceptionThrown() throws IncompleteCommandException {
//        ArrayList<String> testArrayList = new ArrayList<>(Arrays.asList(
//                "n/Speaker B", "t/SPEAKER", "c/1000", "pf/Loud Technologies", "pd/2022-02-23"));
//        updateCommand = new UpdateCommand(testArrayList);
//        CommandResult expectedResult = new CommandResult("Serial Number is required to run this command");
//        assertEquals(expectedResult, updateCommand.execute());
//    }
//
//    @Test
//    void prepareModification_mostRecentArgValueUsed_success() throws IncompleteCommandException {
//        ArrayList<String> testArrayList = new ArrayList<>(Arrays.asList(
//                "s/S1404115ASF", "n/Speaker B", "n/Speaker A"));
//        UpdateCommand expectedCommand = new UpdateCommand(new ArrayList<>(
//                Arrays.asList("s/S1404115ASF", "n/Speaker A")
//        ));
//        UpdateCommand actualCommand = new UpdateCommand(testArrayList);
//        actualCommand.prepareModification();
//        assertEquals(expectedCommand, actualCommand);
//    }
}