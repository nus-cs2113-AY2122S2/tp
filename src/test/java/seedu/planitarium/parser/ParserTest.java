package seedu.planitarium.parser;

import org.junit.jupiter.api.Test;
import seedu.planitarium.exceptions.DuplicateDelimiterException;
import seedu.planitarium.exceptions.InvalidIndexException;
import seedu.planitarium.exceptions.InvalidMoneyException;
import seedu.planitarium.exceptions.MissingDelimiterException;
import seedu.planitarium.person.Person;
import seedu.planitarium.person.PersonList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {

    @Test
    void parseDelimitedTerm_delimitedTerm_success() {
        String input1 = "";
        String input2 = "add /n bill";
        String delimiter = "/n";
        String delimiterBack = "/e";

        String output1 = Parser.parseDelimitedTerm(input1, delimiter, delimiterBack);
        assertEquals(input1, output1);

        String output2 = Parser.parseDelimitedTerm(input2, delimiter, delimiterBack);
        assertEquals("bill", output2);
    }

    @Test
    void parseKeyword_keywordExist_success() {
        String input1 = "";
        String input2 = "add /n bill";

        String output1 = Parser.parseKeyword(input1);
        assertEquals(input1, output1);

        String output2 = Parser.parseKeyword(input2);
        assertEquals("add", output2);
    }

    @Test
    void parseKeyword_keywordIsNull_assertThrown() {
        try {
            Parser.parseKeyword(null);
            fail();
        } catch (AssertionError e) {
            assertEquals("User input should not be null", e.getMessage());
        }
    }

    @Test
    void parseName_delimiterExist_success() throws DuplicateDelimiterException, MissingDelimiterException {
        String input = "add /n bill";
        String output = Parser.parseName(input);
        assertEquals("bill", output);
    }

    @Test
    void parseName_nullInput_assertThrown() {
        try {
            Parser.parseKeyword(null);
            fail();
        } catch (AssertionError e) {
            assertEquals("User input should not be null", e.getMessage());
        }
    }

    @Test
    void parseName_delimiterIssues_exceptionThrown() {
        try {
            String noDelimiter = "add bill";
            Parser.parseName(noDelimiter);
            fail();
        } catch (MissingDelimiterException e) {
            assertEquals("Missing delimiter `/n`", e.getMessage());
        } catch (DuplicateDelimiterException e) {
            fail();
        }
        try {
            String tooManyDelimiter = "add /n bill /n alice";
            Parser.parseName(tooManyDelimiter);
            fail();
        } catch (DuplicateDelimiterException e) {
            assertEquals("Too many delimiter `/n`", e.getMessage());
        } catch (MissingDelimiterException e) {
            fail();
        }
    }

    @Test
    void parseUserIndex_delimiterExist_success() throws DuplicateDelimiterException, MissingDelimiterException {
        String input = "addin /u 1 /d Gift /i 100";
        String output = Parser.parseUserIndex(input);
        assertEquals("1", output);
    }

    @Test
    void parseUserIndex_nullInput_assertThrown() throws DuplicateDelimiterException, MissingDelimiterException {
        try {
            Parser.parseUserIndex(null);
            fail();
        } catch (AssertionError e) {
            assertEquals("User input should not be null", e.getMessage());
        }
    }

    @Test
    void parseUserIndex_delimiterIssues_exceptionThrown() {
        try {
            String noDelimiter = "addin 1 /d Gift /i 100";
            Parser.parseUserIndex(noDelimiter);
            fail();
        } catch (MissingDelimiterException e) {
            assertEquals("Missing delimiter `/u`", e.getMessage());
        } catch (DuplicateDelimiterException e) {
            fail();
        }
        try {
            String tooManyDelimiter = "addin /u 1 /u 2 /d Gift /i 100";
            Parser.parseUserIndex(tooManyDelimiter);
            fail();
        } catch (DuplicateDelimiterException e) {
            assertEquals("Too many delimiter `/u`", e.getMessage());
        } catch (MissingDelimiterException e) {
            fail();
        }
    }

    @Test
    void parseDescription_delimiterExist_success() throws DuplicateDelimiterException, MissingDelimiterException {
        String input = "addin /u 1 /d Gift /i 100";
        String output = Parser.parseDescription(input);
        assertEquals("Gift", output);
    }

    @Test
    void parseDescription_nullInput_assertThrown() throws DuplicateDelimiterException, MissingDelimiterException {
        try {
            Parser.parseDescription(null);
            fail();
        } catch (AssertionError e) {
            assertEquals("User input should not be null", e.getMessage());
        }
    }

    @Test
    void parseDescription_delimiterIssues_exceptionThrown() {
        try {
            String noDelimiter = "addin /u 1 Gift /i 100";
            Parser.parseDescription(noDelimiter);
            fail();
        } catch (MissingDelimiterException e) {
            assertEquals("Missing delimiter `/d`", e.getMessage());
        } catch (DuplicateDelimiterException e) {
            fail();
        }
        try {
            String tooManyDelimiter = "addin /u 1 /d Gift /d Something /i 100";
            Parser.parseDescription(tooManyDelimiter);
            fail();
        } catch (DuplicateDelimiterException e) {
            assertEquals("Too many delimiter `/d`", e.getMessage());
        } catch (MissingDelimiterException e) {
            fail();
        }
    }

    @Test
    void parseIncome_delimiterExist_success() throws DuplicateDelimiterException, MissingDelimiterException {
        String input = "addin /u 1 /d Gift /i 100";
        String output = Parser.parseIncome(input);
        assertEquals("100", output);
    }

    @Test
    void parseIncome_nullInput_assertThrown() throws DuplicateDelimiterException, MissingDelimiterException {
        try {
            Parser.parseIncome(null);
            fail();
        } catch (AssertionError e) {
            assertEquals("User input should not be null", e.getMessage());
        }
    }

    @Test
    void parseIncome_delimiterIssues_exceptionThrown() {
        try {
            String noDelimiter = "addin /u 1 /d Gift 100";
            Parser.parseIncome(noDelimiter);
            fail();
        } catch (MissingDelimiterException e) {
            assertEquals("Missing delimiter `/i`", e.getMessage());
        } catch (DuplicateDelimiterException e) {
            fail();
        }
        try {
            String tooManyDelimiter = "addin /u 1 /d Gift /i 100 /i";
            Parser.parseIncome(tooManyDelimiter);
            fail();
        } catch (DuplicateDelimiterException e) {
            assertEquals("Too many delimiter `/i`", e.getMessage());
        } catch (MissingDelimiterException e) {
            fail();
        }
    }

    @Test
    void parseExpenditure_delimiterExist_success() throws DuplicateDelimiterException, MissingDelimiterException {
        String input = "addout /u 1 /d Food /e 10.50";
        String output = Parser.parseExpenditure(input);
        assertEquals("10.50", output);
    }

    @Test
    void parseExpenditure_nullInput_assertThrown() throws DuplicateDelimiterException, MissingDelimiterException {
        try {
            Parser.parseExpenditure(null);
            fail();
        } catch (AssertionError e) {
            assertEquals("User input should not be null", e.getMessage());
        }
    }

    @Test
    void parseExpenditure_delimiterIssues_exceptionThrown() {
        try {
            String noDelimiter = "addout /u 1 /d Food 10.50";
            Parser.parseExpenditure(noDelimiter);
            fail();
        } catch (MissingDelimiterException e) {
            assertEquals("Missing delimiter `/e`", e.getMessage());
        } catch (DuplicateDelimiterException e) {
            fail();
        }
        try {
            String tooManyDelimiter = "addout /u 1 /d Food /e 10.50 /e";
            Parser.parseExpenditure(tooManyDelimiter);
            fail();
        } catch (DuplicateDelimiterException e) {
            assertEquals("Too many delimiter `/e`", e.getMessage());
        } catch (MissingDelimiterException e) {
            fail();
        }
    }

    @Test
    void parseRecordIndex_delimiterExist_success() throws DuplicateDelimiterException, MissingDelimiterException {
        String input = "deletein /u 1 /r 2";
        String output = Parser.parseRecordIndex(input);
        assertEquals("2", output);
    }

    @Test
    void parseRecordIndex_nullInput_assertThrown() {
        try {
            Parser.parseKeyword(null);
            fail();
        } catch (AssertionError e) {
            assertEquals("User input should not be null", e.getMessage());
        }
    }

    @Test
    void parseRecordIndex_delimiterIssues_exceptionThrown() {
        try {
            String noDelimiter = "deletein /u 1 2";
            Parser.parseRecordIndex(noDelimiter);
            fail();
        } catch (MissingDelimiterException e) {
            assertEquals("Missing delimiter `/r`", e.getMessage());
        } catch (DuplicateDelimiterException e) {
            fail();
        }
        try {
            String tooManyDelimiter = "deletein /u 1 /r 2 /r 1";
            Parser.parseRecordIndex(tooManyDelimiter);
            fail();
        } catch (DuplicateDelimiterException e) {
            assertEquals("Too many delimiter `/r`", e.getMessage());
        } catch (MissingDelimiterException e) {
            fail();
        }
    }
    }

    @Test
    void parseExpenditure() {
    }

    @Test
    void parseRecordIndex() {
    }

    @Test
    void getValidMoney() {
    }

    @Test
    void getValidUserIndex() {
    }

    @Test
    void getValidExpenditureIndex() {
    }

    @Test
    void getValidIncomeIndex() {
    }*/
}