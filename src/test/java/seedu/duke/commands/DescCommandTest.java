package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DescCommandTest {
    @Test
    public void execute_descCommand() {
        Item item1 = new Item("Markers", 3, "Drawing");
        assertEquals("Markers", item1.getName());
        assertEquals("Drawing", item1.getDescription());
    }
}
