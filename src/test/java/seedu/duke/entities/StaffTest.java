package seedu.duke.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class StaffTest {
    @Test
    void staff_InvalidInput_ThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Staff(-1, "Jay", "Waiter", 5000));
        assertThrows(IllegalArgumentException.class, () -> new Staff(1, "", "Waiter", 5000));
        assertThrows(IllegalArgumentException.class, () -> new Staff(1, "Jay", null, 5000));
        assertThrows(IllegalArgumentException.class, () -> new Staff(1, "Jay", "Waiter", 0));
    }
}