package seedu.duke.manager;

import org.junit.jupiter.api.Test;
import seedu.duke.entities.Staff;

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
        assertEquals(new Staff(1, "August", "Chef", 5500), staffManager.findByStaffId(1, false));
        assertEquals(new Staff(2, "Ben", "Chef", 6000), staffManager.findByStaffId(2, false));
        assertEquals(new Staff(3, "Cathy", "Waiter", 5000), staffManager.findByStaffId(3, false));
        assertEquals(new Staff(4, "Darylharhar", "Chef", 5000), staffManager.findByStaffId(4, false));
    }
}
