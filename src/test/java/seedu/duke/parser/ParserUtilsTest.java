package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.InvMgrException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserUtilsTest {

    @Test
    void parseIndex_invalidStringIndex_throwException() {
        String testInput = "asd";
        assertThrows(InvMgrException.class, () -> ParserUtils.parseIndex(testInput));
    }

    @Test
    void parseIndex_invalidIntIndex_throwException() {
        String testInput = "0";
        assertThrows(InvMgrException.class, () -> ParserUtils.parseIndex(testInput));
    }

    @Test
    void parseIndex_validIndex_returnInt() throws InvMgrException {
        String testInput = "1";
        int expectedOutput = 1;
        int actualOutput = ParserUtils.parseIndex(testInput);
        assertEquals(expectedOutput, actualOutput);
    }

    // Implementation of parseQuantity() is mostly similar to parseIndex. invalidStringQuantity is skipped.

    @Test
    void parseQuantity_invalidIntQuantity_returnInt() {
        String testInput = "-1";
        assertThrows(InvMgrException.class, () -> ParserUtils.parseQuantity(testInput));
    }

    @Test
    void parseQuantity_validQuantity_returnInt() throws InvMgrException {
        String testInput = "1";
        int expectedOutput = 1;
        int actualOutput = ParserUtils.parseQuantity(testInput);
        assertEquals(expectedOutput, actualOutput);
    }
}
