package seedu.allonus.modules;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ModuleTest {

    @Test
    public void testStringConversion() {
        assertEquals("[Module] CS2113 Lecture: Thursday, 2pm-4pm",
                new Module("CS2113","Lecture","Thursday","2pm-4pm").toString());
    }
}
