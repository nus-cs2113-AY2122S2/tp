package seedu.duke.entities;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.OperationTerminationException;

import static org.junit.jupiter.api.Assertions.assertTrue;


class StaffTest {
    @Test
    void staff_testCreateStaff() {
        Staff staff1 =  new Staff(1, "Jay", "Waiter", 5000);
        Staff staff2 = new Staff(2, "Mary", "Chef", 5000);
        assertTrue(staff1.toString().equals("1     | Jay             | Waiter"));
        assertTrue(staff2.toString().equals("2     | Mary            | Chef"));
    }
}