package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.DescCommand;
import seedu.duke.exceptions.InvMgrException;
import seedu.duke.stubs.ParserStubs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DescCommandParserTest {

    private DescCommandParser parser = new DescCommandParser();

    @Test
    void parse_invalidIndexString_throwException() {
        String testInput = " /n asd";
        assertThrows(InvMgrException.class, () -> parser.parse(testInput));
    }

    @Test
    void parse_invalidIndexInt_throwException() {
        String testInput = "0";
        assertThrows(InvMgrException.class, () -> parser.parse(testInput));
    }

    @Test
    void parse_validIndex_success() throws InvMgrException {
        String testInput = "1";
        DescCommand actualOutput = parser.parse(testInput);
        assertEquals(ParserStubs.ZEROINDEX_DESCCOMMAND, actualOutput);
    }
}
