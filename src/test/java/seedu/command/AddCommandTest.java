package seedu.command;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.equipment.DuplicateSerialNumberException;
import seedu.equipment.Equipment;
import seedu.equipment.EquipmentManager;
import seedu.equipment.EquipmentType;
import seedu.parser.IncompleteCommandException;
import seedu.parser.MissingAttributeException;
import seedu.parser.Parser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;


class AddCommandTest {
    AddCommand addCommand;
    ArrayList<String> userInput = new ArrayList<>(
            Arrays.asList(
                    "n/Speaker B",
                    "s/S1404115ASF",
                    "t/SPEAKER",
                    "c/1000",
                    "pf/Loud Technologies",
                    "pd/2022-02-23")
    );

    @Test
    void execute_duplicateSerialNumber_exceptionThrown() throws DuplicateSerialNumberException,
            MissingAttributeException {
        addCommand = new AddCommand(userInput);
        addCommand.setEquipmentManager(new EquipmentManager());
        EquipmentManager equipmentManager = addCommand.equipmentManager;
        equipmentManager.addEquipment("Speaker B",
                "S1404115ASF",
                EquipmentType.valueOf("SPEAKER"),
                1000,
                "Loud Technologies",
                LocalDate.parse("2022-02-23"));

        CommandResult expectedResult =
                new CommandResult("There is already an item with this serial number: S1404115ASF");
        CommandResult actualResult = addCommand.execute();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void execute_incorrectCostFormat_exceptionThrown() {
        assertThrows(NumberFormatException.class, () -> new AddCommand(new ArrayList<>(
                Arrays.asList("n/Speaker B",
                        "s/S1404115ASF",
                        "t/SPEAKER",
                        "c/$1000",
                        "pf/Loud Technologies",
                        "pd/2022-02-23")
        )));
    }

    @Test
    void execute_incorrectEnumType_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> new AddCommand(new ArrayList<>(
                Arrays.asList("n/Speaker B",
                        "s/S1404115ASF",
                        "t/SPEAKERS",
                        "c/1000",
                        "pf/Loud Technologies",
                        "pd/2022-02-23")
        )));
    }

    @Test
    void addEquipment_validArrayListString_success() throws DuplicateSerialNumberException, MissingAttributeException {
        addCommand = new AddCommand(userInput);
        addCommand.setEquipmentManager(new EquipmentManager());
        EquipmentManager equipmentManager = addCommand.equipmentManager;
        int equipmentListOriginalSize = equipmentManager.getEquipmentList().size();
        Equipment expectedEquipment = new Equipment("Speaker B",
                "S1404115ASF",
                EquipmentType.valueOf("SPEAKER"),
                1000,
                "Loud Technologies",
                LocalDate.parse("2022-02-23"));

        addCommand.addEquipment(userInput);
        Equipment actualEquipment = equipmentManager.listEquipment().get(equipmentListOriginalSize);

        assertEquals(equipmentListOriginalSize + 1, equipmentManager.getEquipmentList().size());
        assertEquals(expectedEquipment, actualEquipment);
    }

    @Test
    void checkAttributes_allAttributesSet_true() throws MissingAttributeException {
        addCommand = new AddCommand(userInput);
        boolean actualResult = addCommand.checkAttributes();

        assertTrue(actualResult);
    }

    @Test
    void checkAttributes_oneOrMoreNulls_false_exceptionThrown() {
        assertThrows(MissingAttributeException.class, () -> new AddCommand(new ArrayList<>(
                Arrays.asList("n/Speaker B", "s/S1404115ASF", "t/SPEAKER", "pf/Loud Technologies")
        )));
    }
}