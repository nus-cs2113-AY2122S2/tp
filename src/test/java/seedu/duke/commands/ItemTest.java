package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ItemTest {
    @Test
    public void test_item_name() {
        Item newItem = new Item("Paper", 10, "A4 stacks of 30");

        assertEquals("Paper", newItem.getName());
        assertEquals(10, newItem.getQuantity());
        assertEquals("A4 stacks of 30", newItem.getDescription());
        assertEquals(true, newItem.contains("Paper"));
        assertEquals("Paper | 10 | A4 stacks of 30", newItem.saveString());
        assertEquals("Paper | 10 ", newItem.toString());
    }
}
