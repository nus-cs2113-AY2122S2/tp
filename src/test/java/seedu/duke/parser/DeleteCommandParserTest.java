package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.exceptions.InvMgrException;
import seedu.duke.stubs.ParserStubs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

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
        DeleteCommand actualOutput = parser.parse(testInput);
        assertEquals(ParserStubs.ZEROINDEX_DELETECOMMAND, actualOutput);
    }
}
