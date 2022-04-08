package seedu.equipment;

import org.junit.jupiter.api.Test;
import seedu.Pair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EquipmentManagerTest {
    @Test
    public void updateEquipment_success() throws DuplicateSerialNumberException {
        EquipmentManager equipmentManager = new EquipmentManager();
        equipmentManager.addEquipment("Speaker B",
                "S1404115ASF",
                EquipmentType.valueOf("SPEAKER"),
                1000,
                "Loud Technologies",
                LocalDate.parse("2022-02-23"));
        ArrayList<Pair<String, ?>> updates = new ArrayList<>();
        updates.add(new Pair<>("itemName", "Speaker A"));
        updates.add(new Pair<>("type", EquipmentType.STAND));
        updates.add(new Pair<>("cost", 2000.0));
        updates.add(new Pair<>("purchasedDate", LocalDate.parse("2022-03-17")));
        updates.add(new Pair<>("purchasedFrom", "Louder Technologies"));
        equipmentManager.updateEquipment("S1404115ASF", updates);
        HashMap<String, Equipment> equipments = equipmentManager.getEquipmentList();
        Equipment expectedEquipment = new Equipment("Speaker A",
                "S1404115ASF",
                EquipmentType.valueOf("STAND"),
                2000,
                "Louder Technologies",
                LocalDate.parse("2022-03-17"));
        assertEquals(expectedEquipment, equipments.get("S1404115ASF"));
    }

    @Test
    public void updateEquipment_keyNotFound() {
        ArrayList<Pair<String, ?>> updates = new ArrayList<>();
        updates.add(new Pair<>("itemName", "Speaker A"));
        updates.add(new Pair<>("type", "STAND"));
        updates.add(new Pair<>("cost", "2000"));
        updates.add(new Pair<>("purchaseDate", LocalDate.parse("2022-03-17")));
        updates.add(new Pair<>("purchaseFrom", "Louder Technologies"));
        EquipmentManager equipmentManager = new EquipmentManager();
        assertFalse(equipmentManager.updateEquipment("WRONG SERIAL NUMBER", updates));
    }
}
