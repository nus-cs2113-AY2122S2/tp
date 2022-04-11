package seedu.mindmymoney.helper;

import org.junit.jupiter.api.Test;
import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.PropertyList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/** Tests for the PropertyList class. */
public class PropertyListTest {
    /** Asserts that PropertyList is able to properly serialize itself. */
    @Test
    void propertyList_serialization_stringOutput() {
        PropertyList plist = new PropertyList();
        plist.addProperty("test property", "test value");
        plist.addProperty("\"with \\quotes", "2.56");
        String serialized = plist.serialize();
        String expected = " \"test property\": \"test value\" "
                + " \"\\\"with \\\\quotes\": \"2.56\" ";
        assertEquals(expected, serialized);
    }

    /** Asserts that PropertyList can properly deserialize itself. */
    @Test
    void propertyList_deserialization_plistOutput() {
        String serialized = " \"test property\": \"test value\" "
                + " \"\\\"with \\\\quotes\": \"2.56\" ";
        try {
            PropertyList plist = PropertyList.deserialize(serialized);
            assertEquals("test value", plist.getValue("test property"));
            assertEquals("2.56", plist.getValue("\"with \\quotes"));
        } catch (MindMyMoneyException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
}
