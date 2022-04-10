package seedu.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import seedu.command.Command;
import seedu.command.DeleteCommand;
import seedu.command.IncorrectCommand;
import seedu.command.ListCommand;
import seedu.command.UpdateCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
                Arrays.asList("add", "n/`ITEM_NAME` s/`SERIAL_NUMBER` t/`TYPE` c/`COST` pf/`PURCHASED_FROM` "
                        + "pd/`PURCHASED_DATE`")
        );
        ArrayList<String> actualResult = parser.splitCommandTerm(
                "add n/`ITEM_NAME` s/`SERIAL_NUMBER` t/`TYPE` c/`COST` pf/`PURCHASED_FROM` "
                        + "pd/`PURCHASED_DATE`");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void splitCommandTerm_listCommandOnly_success() throws IncompleteCommandException {
        ArrayList<String> expectedResult = new ArrayList<>(
                Arrays.asList("list", null)
        );
        ArrayList<String> actualResult = parser.splitCommandTerm(
                "list");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void splitCommandTerm_listCommandExtraWordsRetained_success() throws IncompleteCommandException {
        ArrayList<String> expectedResult = new ArrayList<>(
                Arrays.asList("list", "t/`SPEAKER`")
        );
        ArrayList<String> actualResult = parser.splitCommandTerm(
                "list t/`SPEAKER`");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void splitCommandTerm_noSpaceDelimiter_exceptionThrown() {
        ArrayList<String> unexpectedResult = new ArrayList<>(
                Arrays.asList("add", "n/`ITEM_NAME`s/`SERIAL_NUMBER`t/`TYPE`c/`COST`pf/`PURCHASED_FROM`"
                        + "pd/`PURCHASED_DATE`")
        );
        try {
            ArrayList<String> actualResult = parser.splitCommandTerm(
                    "addn/`ITEM_NAME`s/`SERIAL_NUMBER`t/`TYPE`c/`COST`pf/`PURCHASED_FROM`pd/`PURCHASED_DATE`");
            assertEquals(unexpectedResult, actualResult);
            fail();
        } catch (IncompleteCommandException e) {
            assertEquals("Could not find space delimiter between command and arguments!", e.getMessage());
        }
    }

    @Test
    void prepareCheck_viewStringWithSpaces_success() throws IncompleteCommandException {
        ArrayList<String> expectedResult = new ArrayList<>(
                Arrays.asList("n/Speaker   B")
        );
        ArrayList<String> actualResult = parser.prepareCheck(
                "n/`Speaker   B`");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void prepareCheck_checkUsingSerialNum_success() throws IncompleteCommandException {
        ArrayList<String> expectedResult = new ArrayList<>(
                Arrays.asList("s/Speaker   B")
        );
        ArrayList<String> actualResult = parser.prepareCheck(
                "s/`Speaker   B`");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void prepareCheck_missingFrontBackTick_exceptionThrown() {
        ArrayList<String> expectedResult = new ArrayList<>(
                Arrays.asList("n/Speaker   B")
        );
        try {
            ArrayList<String> actualResult = parser.prepareCheck(
                    "n/`Speaker   B");
            assertEquals(expectedResult, actualResult);
            fail();
        } catch (IncompleteCommandException e) {
            assertEquals("Check command values are incomplete or missing!", e.getMessage());
        }
    }

    @Test
    void prepareCheck_missingBackBackTick_exceptionThrown() {
        ArrayList<String> expectedResult = new ArrayList<>(
                Arrays.asList("n/Speaker   B")
        );
        try {
            ArrayList<String> actualResult = parser.prepareCheck(
                    "n/Speaker   B`");
            assertEquals(expectedResult, actualResult);
            fail();
        } catch (IncompleteCommandException e) {
            assertEquals("Check command values are incomplete or missing!", e.getMessage());
        }
    }

    @Test
    void prepareDelete_deleteStringWithSpaces_success() throws IncompleteCommandException {
        ArrayList<String> expectedResult = new ArrayList<>(
                Arrays.asList("s/SM58 - 1")
        );
        ArrayList<String> actualResult = parser.prepareDelete(
                "s/`SM58 - 1`");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void prepareDelete_wrongArgumentTag_exceptionThrown() {
        ArrayList<String> expectedResult = new ArrayList<>(
                Arrays.asList("n/Speaker   B")
        );
        try {
            ArrayList<String> actualResult = parser.prepareDelete(
                    "n/Speaker   B");
            assertEquals(expectedResult, actualResult);
            fail();
        } catch (IncompleteCommandException e) {
            assertEquals("Delete command values are incomplete or missing!", e.getMessage());
        }
    }

    @Test
    void prepareDelete_missingFrontBackTick_exceptionThrown() {
        ArrayList<String> expectedResult = new ArrayList<>(
                Arrays.asList("s/Speaker   B")
        );
        try {
            ArrayList<String> actualResult = parser.prepareDelete(
                    "s/`Speaker   B");
            assertEquals(expectedResult, actualResult);
            fail();
        } catch (IncompleteCommandException e) {
            assertEquals("Delete command values are incomplete or missing!", e.getMessage());
        }
    }

    @Test
    void prepareDelete_missingBackBackTick_exceptionThrown() {
        ArrayList<String> expectedResult = new ArrayList<>(
                Arrays.asList("s/Speaker   B")
        );
        try {
            ArrayList<String> actualResult = parser.prepareDelete(
                    "s/Speaker   B`");
            assertEquals(expectedResult, actualResult);
            fail();
        } catch (IncompleteCommandException e) {
            assertEquals("Delete command values are incomplete or missing!", e.getMessage());
        }
    }

    @Test
    void extractArguments_noSpaceBeforeTypeSlashDelimiterFound_exceptionThrown() {
        ArrayList<String> expectedResult = new ArrayList<>(Arrays.asList(
                "n/Speaker B", "t/Speaker", "c/1000", "pf/Loud Technologies", "pd/2022-02-23"));
        try {
            ArrayList<String> actualResult = parser.extractArguments(
                    "n/`Speaker B`t/`Speaker`c/`1000`pf/`Loud Technologies`pd/`2022-02-23`");
            assertEquals(expectedResult, actualResult);
            fail();
        } catch (IncompleteCommandException e) {
            assertEquals(IncompleteCommandException.NO_PARAMETERS_FOUND, e.getMessage());
        }
    }

    @Test
    void extractArguments_idealArgumentPairs_success() throws IncompleteCommandException {
        ArrayList<String> actualResult = parser.extractArguments("S/`S1404115Ax` n/`Speaker B` "
                        + "c/`1000` Pf/`Loud Technologies` PD/`2022-02-23` t/`Speaker`");
        ArrayList<String> expectedResult = new ArrayList<>(Arrays.asList("s/S1404115Ax",
                "n/Speaker B", "c/1000", "pf/Loud Technologies", "pd/2022-02-23", "t/Speaker"));
        assertEquals(actualResult, expectedResult);
    }

    @Test
    void extractArguments_wrongArgTypesIgnored_success() throws IncompleteCommandException {
        ArrayList<String> actualResult = parser.extractArguments("s/`S1404115ASF` rand/`SpeakerC` "
                + "c/`2510` name/`blahblah` pd/`2022-08-21`");
        ArrayList<String> expectedResult = new ArrayList<>(Arrays.asList("s/S1404115ASF",
                "c/2510", "pd/2022-08-21"));
        assertEquals(actualResult, expectedResult);
    }

    @Test
    void extractArguments_noCorrectArgTypes_exceptionThrown() {
        Throwable exception = assertThrows(IncompleteCommandException.class, () -> parser.extractArguments(
                "x/`Speaker B` a/`Speaker` b/`1000` d/`Loud Technologies` e/`2022-02-23`"));
        assertEquals(IncompleteCommandException.NO_PARAMETERS_FOUND, exception.getMessage());
    }

    @Test
    void parseCommand_deleteCommand_success() {
        Command testCommand = parser.parseCommand("delete s/`S1234567E`");
        Command expectedCommand = new DeleteCommand(new ArrayList<>(Collections.singleton("s/S1234567E")));
        assertEquals(expectedCommand, testCommand);
    }

    @Test
    void parseCommand_trailingWhiteSpace_success() {
        Command testCommand = parser.parseCommand("delete s/`S1234567E`         ");
        Command expectedCommand = new DeleteCommand(new ArrayList<>(Collections.singleton("s/S1234567E")));
        assertEquals(expectedCommand, testCommand);
    }

    @Test
    void parseCommand_deleteCommandWrongArgType_exceptionCaught() {
        Command expectedCommand = new IncorrectCommand(DeleteCommand.COMMAND_WORD
                + DeleteCommand.COMMAND_DESCRIPTION);
        Command testCommand = parser.parseCommand("delete x/`S1234567E`");
        assertEquals(expectedCommand, testCommand);
    }

    @Test
    void parseCommand_updateCommandIncorrectDateFormat_exceptionCaught() {
        Command expectedCommand = new IncorrectCommand(UpdateCommand.COMMAND_WORD
                + UpdateCommand.COMMAND_DESCRIPTION);
        Command testCommand = parser.parseCommand("update s/`S1234567E` pd `2022-13-23`");
        assertEquals(expectedCommand, testCommand);
    }

}