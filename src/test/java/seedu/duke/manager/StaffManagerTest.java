package seedu.duke.manager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaffManagerTest {
    @Test
    void addDeleteStaff_test_addDeleteCorrectly() {
        StaffManager staffManager = new StaffManager();
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
        StaffManager staffManager = new StaffManager();
        staffManager.addStaff(1, "August", "Chef", 5500);
        staffManager.addStaff(2, "Ben", "Chef", 6000);
        staffManager.addStaff(3, "Cathy", "Waiter", 5000);
        staffManager.addStaff(4, "Darylharhar", "Chef", 5000);
        assertEquals("August", staffManager.findByStaffId(1, false).getStaffName());
        assertEquals("Ben", staffManager.findByStaffId(2, false).getStaffName());
        assertEquals("Waiter", staffManager.findByStaffId(3, false).getPosition());
        assertEquals("Chef", staffManager.findByStaffId(4, false).getPosition());
    }
}
