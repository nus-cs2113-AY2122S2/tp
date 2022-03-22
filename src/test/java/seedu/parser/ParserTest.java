package seedu.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

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
                Arrays.asList("add", "n/ITEM_NAME s/SERIAL_NUMBER t/TYPE c/COST pf/PURCHASED_FROM pd/PURCHASED_DATE")
        );
        ArrayList<String> actualResult = parser.splitCommandTerm(
                "add n/ITEM_NAME s/SERIAL_NUMBER t/TYPE c/COST pf/PURCHASED_FROM pd/PURCHASED_DATE");
        assertEquals(expectedResult, actualResult);
        assertEquals(expectedResult.get(0), actualResult.get(0));
        assertEquals(expectedResult.get(1), actualResult.get(1));
    }

    @Test
    void splitCommandTerm_noSpaceDelimiter_exceptionThrown() {
        ArrayList<String> unexpectedResult = new ArrayList<>(
                Arrays.asList("add", "n/ITEM_NAMEs/SERIAL_NUMBERt/TYPEc/COSTpf/PURCHASED_FROMpd/PURCHASED_DATE")
        );
        try {
            ArrayList<String> actualResult = parser.splitCommandTerm(
                    "addn/ITEM_NAMEs/SERIAL_NUMBERt/TYPEc/COSTpf/PURCHASED_FROMpd/PURCHASED_DATE");
            assertEquals(unexpectedResult, actualResult);
            fail();
        } catch (IncompleteCommandException e) {
            assertEquals("Could not find space delimiter between command and arguments!", e.getMessage());
        }
    }

    @Deprecated
    @Disabled("extractArgument is a better replacement for prepareAdd")
    @Test
    void prepareAdd_addStringWithSpaces_success() throws IncompleteCommandException {
        ArrayList<String> expectedResult = new ArrayList<>(
                Arrays.asList("Speaker B", "S1404115ASF", "Speaker", "1000", "Loud Technologies", "2022-02-23")
        );
        ArrayList<String> actualResult = parser.prepareAdd(
                "n/Speaker B s/S1404115ASF t/Speaker c/1000 pf/Loud Technologies pd/2022-02-23");
        assertEquals(expectedResult, actualResult);
    }

    @Deprecated
    @Disabled("extractArgument is a better replacement for prepareAdd")
    @Test
    void prepareAdd_insufficientArguments_exceptionThrown() {
        ArrayList<String> unexpectedResult = new ArrayList<>(
                Arrays.asList("Speaker B", "S1404115ASF", "1000", "Loud Technologies", "2022-02-23")
        );
        try {
            ArrayList<String> actualResult = parser.prepareAdd(
                    "n/Speaker B s/S1404115ASF c/1000 pf/Loud Technologies pd/2022-02-23");
            assertEquals(unexpectedResult, actualResult);
            fail();
        } catch (IncompleteCommandException e) {
            assertEquals("Add command values are incomplete or missing!", e.getMessage());
        }
    }

    @Deprecated
    @Disabled("extractArgument is a better replacement for prepareAdd")
    @Test
    void prepareAdd_argumentEnteredTwice_exceptionThrown() {
        ArrayList<String> unexpectedResult = new ArrayList<>(
                Arrays.asList("Speaker B", "S1404115ASF", "Speaker", "1000 c/1000", "Loud Technologies", "2022-02-23")
        );
        try {
            ArrayList<String> actualResult = parser.prepareAdd(
                    "n/Speaker B s/S1404115ASF t/Speaker c/1000 c/1000 pf/Loud Technologies pd/2022-02-23");
            assertEquals(unexpectedResult, actualResult);
            fail();
        } catch (IncompleteCommandException e) {
            assertEquals("Use of '/' for purposes other than delimiter is forbidden!", e.getMessage());
        }
    }

    @Deprecated
    @Disabled("extractArgument is a better replacement for prepareAdd")
    @Test
    void prepareAdd_argumentIncludesSlashA_exceptionThrown() {
        ArrayList<String> unexpectedResult = new ArrayList<>(
                Arrays.asList("Speaker B", "S1404115/ASF", "Speaker", "1000", "Loud Technologies", "2022-02-23")
        );
        try {
            ArrayList<String> actualResult = parser.prepareAdd(
                    "n/Speaker B s/S1404115/ASF t/Speaker c/1000 pf/Loud Technologies pd/2022-02-23");
            assertEquals(unexpectedResult, actualResult);
            fail();
        } catch (IncompleteCommandException e) {
            assertEquals("Use of '/' for purposes other than delimiter is forbidden!", e.getMessage());
        }
    }

    @Deprecated
    @Disabled("extractArgument is a better replacement for prepareAdd")
    @Test
    void prepareAdd_argumentIncludesSlashB_exceptionThrown() {
        ArrayList<String> unexpectedResult = new ArrayList<>(
                Arrays.asList("Speaker B", "S1404115", "t/SF t/Speaker", "1000", "Loud Technologies", "2022-02-23")
        );
        try {
            ArrayList<String> actualResult = parser.prepareAdd(
                    "n/Speaker B s/S1404115t/SF t/Speaker c/1000 pf/Loud Technologies pd/2022-02-23");
            assertEquals(unexpectedResult, actualResult);
            fail();
        } catch (IncompleteCommandException e) {
            assertEquals("Use of '/' for purposes other than delimiter is forbidden!", e.getMessage());
        }
    }

    @Test
    void prepareView_viewStringWithSpaces_success() throws IncompleteCommandException {
        ArrayList<String> expectedResult = new ArrayList<>(
                Arrays.asList("Speaker   B")
        );
        ArrayList<String> actualResult = parser.prepareView(
                "   n/Speaker   B ");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void prepareView_wrongArgumentTag_exceptionThrown() {
        ArrayList<String> expectedResult = new ArrayList<>(
                Arrays.asList("Speaker   B")
        );
        try {
            ArrayList<String> actualResult = parser.prepareView(
                    "s/Speaker   B");
            assertEquals(expectedResult, actualResult);
            fail();
        } catch (IncompleteCommandException e) {
            assertEquals("View command values are incomplete or missing!", e.getMessage());
        }
    }

    @Test
    void prepareDelete_deleteStringWithSpaces_success() throws IncompleteCommandException {
        ArrayList<String> expectedResult = new ArrayList<>(
                Arrays.asList("SM58-1")
        );
        ArrayList<String> actualResult = parser.prepareDelete(
                "   s/SM58-1 ");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void prepareDelete_wrongArgumentTag_exceptionThrown() {
        ArrayList<String> expectedResult = new ArrayList<>(
                Arrays.asList("Speaker   B")
        );
        try {
            ArrayList<String> actualResult = parser.prepareDelete(
                    "   n/Speaker   B ");
            assertEquals(expectedResult, actualResult);
            fail();
        } catch (IncompleteCommandException e) {
            assertEquals("Delete command values are incomplete or missing!", e.getMessage());
        }
    }

    @Test
    void extractArguments_validCommands_success() throws IncompleteCommandException {
        ArrayList<String> testStrings = new ArrayList<>(Arrays.asList(
                "s/S1404115ASF n/Speaker B t/Speaker c/1000 pf/Loud Technologies pd/2022-02-23",
                "s/S1404115ASF     c/1000",
                "s/S1404115ASF n/Speaker B        ",
                "s/S1404115ASF pf/Loud Technologies n/Speaker B",
                "t/Speaker s/S1404115ASF",
                "c/1000 pf/Loud Technologies s/S1404115ASF"
        ));
        ArrayList<ArrayList<String>> expectedResults = new ArrayList<>();
        expectedResults.add(new ArrayList<>(Arrays.asList(
                "s/S1404115ASF", "n/Speaker B", "t/Speaker", "c/1000", "pf/Loud Technologies", "pd/2022-02-23")));
        expectedResults.add(new ArrayList<>(Arrays.asList(
                "s/S1404115ASF", "c/1000")));
        expectedResults.add(new ArrayList<>(Arrays.asList(
                "s/S1404115ASF", "n/Speaker B")));
        expectedResults.add(new ArrayList<>(Arrays.asList(
                "s/S1404115ASF", "pf/Loud Technologies", "n/Speaker B")));
        expectedResults.add(new ArrayList<>(Arrays.asList(
                "t/Speaker", "s/S1404115ASF")));
        expectedResults.add(new ArrayList<>(Arrays.asList(
                "c/1000", "pf/Loud Technologies", "s/S1404115ASF")));
        for (int i = 0; i < expectedResults.size(); i++) {
            assertEquals(expectedResults.get(i), parser.extractArguments(testStrings.get(i)));
        }
    }

    @Test
    void extractArguments_mixedCaseText_success() throws IncompleteCommandException {
        ArrayList<String> testStrings = new ArrayList<>(Arrays.asList(
                "S/S1404115ASF n/Speaker B t/Speaker c/1000 Pf/Loud Technologies PD/2022-02-23",
                "s/S1404115ASF     C/1000",
                "s/S1404115ASF N/Speaker B        ",
                "s/S1404115ASF pf/Loud Technologies n/Speaker B",
                "t/Speaker S/S1404115ASF",
                "c/1000 pF/Loud Technologies s/S1404115ASF"
        ));
        ArrayList<ArrayList<String>> expectedResults = new ArrayList<>();
        expectedResults.add(new ArrayList<>(Arrays.asList(
                "s/S1404115ASF", "n/Speaker B", "t/Speaker", "c/1000", "pf/Loud Technologies", "pd/2022-02-23")));
        expectedResults.add(new ArrayList<>(Arrays.asList(
                "s/S1404115ASF", "c/1000")));
        expectedResults.add(new ArrayList<>(Arrays.asList(
                "s/S1404115ASF", "n/Speaker B")));
        expectedResults.add(new ArrayList<>(Arrays.asList(
                "s/S1404115ASF", "pf/Loud Technologies", "n/Speaker B")));
        expectedResults.add(new ArrayList<>(Arrays.asList(
                "t/Speaker", "s/S1404115ASF")));
        expectedResults.add(new ArrayList<>(Arrays.asList(
                "c/1000", "pf/Loud Technologies", "s/S1404115ASF")));
        for (int i = 0; i < expectedResults.size(); i++) {
            assertEquals(expectedResults.get(i), parser.extractArguments(testStrings.get(i)));
        }
    }

    @Test
    void extractArguments_noSpaceBeforeTypeSlashDelimiterFound_exceptionThrown() {
        ArrayList<String> expectedResult = new ArrayList<>(Arrays.asList(
                "x/Speaker B", "t/Speaker", "c/1000", "pf/Loud Technologies", "pd/2022-02-23"));
        try {
            ArrayList<String> actualResult = parser.extractArguments(
                    "x/Speaker Bt/Speakerc/1000pf/Loud Technologiespd/2022-02-23");
            assertEquals(expectedResult, actualResult);
            fail();
        } catch (IncompleteCommandException e) {
            assertEquals("No parameters found!", e.getMessage());
        }
    }

    @Test
    void extractArguments_wrongArgTypesUsed_exceptionThrown() throws IncompleteCommandException {
        Throwable exception = assertThrows(IncompleteCommandException.class, () -> parser.extractArguments(
                "x/Speaker B a/Speaker b/1000 d/Loud Technologies e/2022-02-23"));
        assertEquals("No parameters found!", exception.getMessage());
    }

}