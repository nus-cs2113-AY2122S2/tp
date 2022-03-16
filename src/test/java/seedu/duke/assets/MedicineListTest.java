package seedu.duke.assets;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MedicineListTest {
    @Test
    void checkSize_arrayOfMedicines_expectedNumber() {
        MedicineList medicineList = new MedicineList();
        int currentNumber = 0;
        assertEquals(currentNumber, medicineList.size());
    }

    @Test
    void demoTestMethod() {
        assertTrue(true);
    }
}
