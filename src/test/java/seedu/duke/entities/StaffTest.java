package seedu.duke.entities;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.OperationTerminationException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class StaffTest {
    @Test
    void staff_testCreateStaff_createCorrectly() {
        Staff staff1 =  new Staff(1, "Jay", "Waiter", 5000);
        Staff staff2 = new Staff(2, "Mary", "Chef", 5000);
        assertTrue(staff1.toString().equals("1     | Jay             | Waiter     | $5000.00"));
        assertTrue(staff2.toString().equals("2     | Mary            | Chef       | $5000.00"));
    }

    @Test
    void staff_testCreateStaff_createWrongly() {
        assertThrows(IllegalArgumentException.class, () -> new Staff(0, "Jane", "Manager", 6000));
        assertThrows(IllegalArgumentException.class, () -> new Staff(-1, "Jane", "Manager", 6000));
        assertThrows(IllegalArgumentException.class, () -> new Staff(10, "Larry", "Manager", 0));
        assertThrows(IllegalArgumentException.class, () -> new Staff(11, "Larry", "Manager", -1));
        assertDoesNotThrow(() -> new Staff(3, "Thomas", "Waiter", 2000));
    }
}