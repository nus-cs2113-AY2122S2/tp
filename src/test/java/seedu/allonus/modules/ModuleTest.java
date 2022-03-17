package seedu.allonus.modules;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Represents test class to test Module class methods.
 */
public class ModuleTest {

    /**
     * Tests the toString() method in Module class.
     */
    @Test
    public void testStringConversion() {
        assertEquals("[Module] CS2113 Lecture: Thursday, 2pm-4pm",
                new Module("CS2113","Lecture","Thursday","2pm-4pm").toString());
    }
}
