package seedu.allonus.modules;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ModuleTest {

    @Test
    public void testStringConversion() {
        assertEquals("[Module] Code='CS2113', Category='Lecture'",new Module("CS2113","Lecture").toString());
    }
}
