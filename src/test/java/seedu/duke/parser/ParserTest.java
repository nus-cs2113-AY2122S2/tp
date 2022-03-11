package seedu.duke.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.parser.IncompleteCommandException;
import seedu.parser.Parser;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {

    Parser parser;

    @BeforeEach
    void setup() {
        parser = new Parser();
    }

    @Test
    void splitCommandTerm_validCommand_success() throws IncompleteCommandException {
        ArrayList<String> expectedResult = new ArrayList<>(
                Arrays.asList("add", "n/ITEM_NAME sn/SERIAL_NUMBER t/TYPE c/COST pf/PURCHASED_FROM pd/PURCHASED_DATE")
        );
        ArrayList<String> actualResult = parser.splitCommandTerm(
                "add n/ITEM_NAME sn/SERIAL_NUMBER t/TYPE c/COST pf/PURCHASED_FROM pd/PURCHASED_DATE");
        assertEquals(expectedResult, actualResult);
        assertEquals(expectedResult.get(0), actualResult.get(0));
        assertEquals(expectedResult.get(1), actualResult.get(1));
    }

    @Test
    void splitCommandTerm_incompleteCommand_exceptionThrown() {
        ArrayList<String> unexpectedResult = new ArrayList<>(
                Arrays.asList("add", "n/ITEM_NAMEsn/SERIAL_NUMBERt/TYPEc/COSTpf/PURCHASED_FROMpd/PURCHASED_DATE")
        );
        try {
            ArrayList<String> actualResult = parser.splitCommandTerm(
                    "addn/ITEM_NAMEsn/SERIAL_NUMBERt/TYPEc/COSTpf/PURCHASED_FROMpd/PURCHASED_DATE");
            assertEquals(unexpectedResult, actualResult);
            fail();
        } catch (IncompleteCommandException e) {
            assertEquals("Could not find space delimiter between command and arguments!", e.getMessage());
        }
    }

    @Test
    void prepareAdd_validAddString_success() throws IncompleteCommandException {
        ArrayList<String> expectedResult = new ArrayList<>(
                Arrays.asList("Speaker B", "S1404115ASF", "Speaker", "1000", "Loud Technologies", "2022-02-23")
        );
        ArrayList<String> actualResult = parser.prepareAdd(
                "n/Speaker B sn/S1404115ASF t/Speaker c/1000 pf/Loud Technologies pd/2022-02-23");
        assertEquals(expectedResult, actualResult);
    }

}