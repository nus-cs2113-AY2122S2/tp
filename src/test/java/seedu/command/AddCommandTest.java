package seedu.command;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.equipment.DuplicateSerialNumber;
import seedu.equipment.Equipment;
import seedu.equipment.EquipmentManager;
import seedu.equipment.EquipmentType;
import seedu.parser.Parser;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AddCommandTest {
    AddCommand addCommand;
    ArrayList<String> userInput = new ArrayList<>(
            Arrays.asList("Speaker B", "S1404115ASF", "SPEAKER", "1000", "Loud Technologies", "2022-02-23")
    );

//    @BeforeEach
//    void setup() {
//        addCommand = new AddCommand(userInput);
//    }

    @Test
    void execute_duplicateSerialNumber_exceptionThrown() throws DuplicateSerialNumber {
        addCommand.setEquipmentManager(new EquipmentManager());
        EquipmentManager equipmentManager = addCommand.equipmentManager;
        equipmentManager.addEquipment("Speaker B",
                "S1404115ASF",
                EquipmentType.valueOf("SPEAKER"),
                1000,
                "Loud Technologies",
                "2022-02-23");

        CommandResult expectedResult = new CommandResult("There is already an item with this serial number: S1404115ASF");
        CommandResult actualResult = addCommand.execute();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void execute_incorrectCostFormat_exceptionThrown(){
        addCommand = new AddCommand(new ArrayList<>(
                Arrays.asList("Speaker B", "S1404115ASF", "SPEAKER", "$1000", "Loud Technologies", "2022-02-23")
        ));

        CommandResult expectedResult = new CommandResult("Please enter numbers only for cost and omit symbols");
        CommandResult actualResult = addCommand.execute();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void execute_incorrectEnumType_exceptionThrown() {
        addCommand = new AddCommand(new ArrayList<>(
                Arrays.asList("Speaker B", "S1404115ASF", "SPEAKERS", "1000", "Loud Technologies", "2022-02-23")
        ));
        addCommand.setEquipmentManager(new EquipmentManager());
        EquipmentManager equipmentManager = addCommand.equipmentManager;

        CommandResult expectedResult = new CommandResult("Wrong type of equipment. The allowed types are: MICROPHONE, SPEAKER, STAND, CABLE");
        CommandResult actualResult = addCommand.execute();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void addEquipment_validArrayListString_success() throws DuplicateSerialNumber {
        addCommand = new AddCommand(userInput);
        addCommand.setEquipmentManager(new EquipmentManager());
        EquipmentManager equipmentManager = addCommand.equipmentManager;
        int equipmentListOriginalSize = equipmentManager.getEquipmentList().size();
        Equipment expectedEquipment = new Equipment("Speaker B",
                "S1404115ASF",
                EquipmentType.valueOf("SPEAKER"),
                1000,
                "Loud Technologies",
                "2022-02-23");

        addCommand.addEquipment(userInput);
        Equipment actualEquipment = equipmentManager.listEquipment().get(equipmentListOriginalSize);

        assertEquals(equipmentListOriginalSize + 1, equipmentManager.getEquipmentList().size());
        assertEquals(expectedEquipment, actualEquipment);
    }
}