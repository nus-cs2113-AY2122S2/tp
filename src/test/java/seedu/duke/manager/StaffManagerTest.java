package seedu.duke.manager;

import org.junit.jupiter.api.Test;
import seedu.duke.entities.Dish;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertEquals("August", staffManager.findByStaffId(1, false).getStaffName());
        assertEquals("Ben", staffManager.findByStaffId(2, false).getStaffName());
        assertEquals("Waiter", staffManager.findByStaffId(3, false).getPosition());
        assertEquals("Chef", staffManager.findByStaffId(4, false).getPosition());
    }

    @Test
    void addDeleteFindStaff_test_invalidId() {
        StaffManager.resetInstance();
        StaffManager staffManager = StaffManager.getInstance();
        staffManager.addStaff(1, "August", "Chef", 5500);
        staffManager.addStaff(2, "Ben", "Chef", 6000);
        staffManager.addStaff(3, "Cathy", "Waiter", 5000);
        staffManager.addStaff(4, "Darylharhar", "Chef", 5000);
        assertThrows(IllegalArgumentException.class, () -> staffManager.addStaff(1, "Eric", "Waiter", 4500));
        assertDoesNotThrow(() -> staffManager.findByStaffId(1, false));
        assertDoesNotThrow(() -> staffManager.findByStaffId(2, false));
        assertThrows(IllegalArgumentException.class, () -> staffManager.findByStaffId(-1, false));
        assertThrows(IllegalArgumentException.class, () -> staffManager.findByStaffId(0, false));
        assertDoesNotThrow(() -> staffManager.deleteByStaffId(1));
        assertDoesNotThrow(() -> staffManager.deleteByStaffId(2));
        assertThrows(IllegalArgumentException.class, () -> staffManager.deleteByStaffId(-1));
        assertThrows(IllegalArgumentException.class, () -> staffManager.deleteByStaffId(5));
    }

    @Test
    void printStaff_test_invalidId() {
        StaffManager.resetInstance();
        StaffManager staffManager = StaffManager.getInstance();
        assertThrows(IllegalStateException.class, () -> staffManager.printStaff());
    }
}
