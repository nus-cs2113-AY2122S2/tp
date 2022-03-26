//@@author 1szheng

package seedu.planitarium.parser;

import org.junit.jupiter.api.Test;
import seedu.planitarium.exceptions.EmptyStringException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ParserUtilityTest {

    @Test
    void parseDelimitedTerm_delimitedTerm_success() throws EmptyStringException {
        String input1 = "add";
        String delimiter1 = "/n";
        String output1 = ParserUtility.parseDelimitedTerm(input1, delimiter1);
        assertEquals(input1, output1);

        String input2 = "add /n bill";
        String output2 = ParserUtility.parseDelimitedTerm(input2, delimiter1);
        assertEquals("bill", output2);

        String input3 = "add /n bill /g 1";
        String output3 = ParserUtility.parseDelimitedTerm(input3, delimiter1);
        assertEquals("bill", output3);
    }

    @Test
    void parseDelimitedTerm_emptyTerm_exceptionThrown() {
        try {
            String input = "add /n  /e";
            String delimiter = "/n";

            ParserUtility.parseDelimitedTerm(input, delimiter);
        } catch (EmptyStringException e) {
            assertEquals("Empty string after `/n` detected", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void checkNegativeMoney_positiveMoney_success() {
        ParserUtility.checkNegativeMoney(1.0);
    }

    @Test
    void checkNegativeMoney_negativeMoney_exceptionThrown() {
        try {
            ParserUtility.checkNegativeMoney(-1);
        } catch (NumberFormatException e) {
            assertEquals("Money is negative", e.getMessage());
        }
    }
}