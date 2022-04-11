package seedu.duke.manager;

import org.junit.jupiter.api.Test;
import seedu.duke.entities.Dish;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaffManagerTest {
    @Test
    void addDeleteStaff_test_addDeleteCorrectly() {
        StaffManager.resetInstance();
        StaffManager staffManager = StaffManager.getInstance();
        staffManager.addStaff(1, "August", "Chef", 5500);
        staffManager.addStaff(2, "Ben", "Chef", 6000);
        staffManager.addStaff(3, "Cathy", "Waiter", 5000);
        staffManager.addStaff(4, "Darylharhar", "Chef", 5000);
        assertDoesNotThrow(() -> staffManager.deleteByStaffId(3));
        assertDoesNotThrow(() -> staffManager.deleteByStaffId(2));
        assertDoesNotThrow(() -> staffManager.deleteByStaffId(1));
        assertEquals(1, staffManager.getNumOfStaffs());
    }

    @Test
    void findStaff_test_findCorrectly() {
        StaffManager.resetInstance();
        StaffManager staffManager = StaffManager.getInstance();
        staffManager.addStaff(1, "August", "Chef", 5500);
        staffManager.addStaff(2, "Ben", "Chef", 6000);
        staffManager.addStaff(3, "Cathy", "Waiter", 5000);
        staffManager.addStaff(4, "Darylharhar", "Chef", 5000);
        assertEquals("August", staffManager.findByStaffId(1).getStaffName());
        assertEquals("Ben", staffManager.findByStaffId(2).getStaffName());
        assertEquals("Waiter", staffManager.findByStaffId(3).getPosition());
        assertEquals("Chef", staffManager.findByStaffId(4).getPosition());
        assertThrows(IllegalArgumentException.class, () -> staffManager.findByStaffId(-1));
    }

    @Test
    void addDeleteFindStaff_test_invalidId() {
        StaffManager.resetInstance();
        StaffManager staffManager = StaffManager.getInstance();
        staffManager.addStaff(1, "August", "Chef", 5500);
        staffManager.addStaff(2, "Ben", "Chef", 6000);
        staffManager.addStaff(3, "Cathy", "Waiter", 5000);
        staffManager.addStaff(4, "Darylharhar", "Chef", 5000);
        assertDoesNotThrow(() -> staffManager.findByStaffId(1));
        assertDoesNotThrow(() -> staffManager.findByStaffId(2));
        assertEquals("1     | August          | Chef       | $5500.00", staffManager.findByStaffId(1).toString());
        assertThrows(IllegalArgumentException.class, () -> staffManager.findByStaffId(-1));
        assertThrows(IllegalArgumentException.class, () -> staffManager.findByStaffId(0));
    }
}
