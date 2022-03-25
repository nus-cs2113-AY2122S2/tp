package seedu.sherpass.util.parser;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskParserTest {

    @Test
    void parseArgument_byParameter_expectDateTime() {
        String date = "foo /by 21/12/2022 /start 09:00";
        String expected = "21/12/2022";
        String result = TaskParser.parseArgument("/by", date);
        assertEquals(result, expected);
    }

    @Test
    void parseArgument_invalidParameter_expectEmptyString() {
        String date = "foo /by 21/12/2022 09:00";
        String expected = "";
        String result = TaskParser.parseArgument("/do", date);
        assertEquals(result, expected);
    }

    @Test
    void parseDescription_validDescription_expectFoo() {
        String input = "foo /by 21/12/2022";
        String expected = "foo";
        String result = TaskParser.parseDescription(input);
        assertEquals(result, expected);
    }

    @Test
    void parseDescription_emptyDescription_expectEmptyString() {
        String input = "/by 21/12/2022";
        String expected = "";
        String result = TaskParser.parseDescription(input);
        assertEquals(result, expected);
    }

    @Test
    void removeRecurringDelimiter_noDelimiter_expectIdenticalString() {
        String input = "foo";
        String actualOutput = TaskParser.removeRecurringDelimiter(input);
        String expectedOutput = input;
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void removeRecurringDelimiter_haveDelimiter_expectFoo() {
        String input = "foo /repeat";
        String actualOutput = TaskParser.removeRecurringDelimiter(input);
        String expectedOutput = "foo";
        assertEquals(expectedOutput, actualOutput);

    }

    @Test
    void prepareTaskDate_notDateInput_expectNull() {
        String date = "foo";
        String time = "bar";
        LocalDateTime actualOutput = TaskParser.prepareTaskDate(date, time);
        assertEquals(null, actualOutput);
    }

    @Test
    void prepareTaskDate_invalidDateFormat_expectNull() {
        String date = "25-3-2022";
        String time = "13:00";
        LocalDateTime actualOutput = TaskParser.prepareTaskDate(date, time);
        assertEquals(null, actualOutput);
    }

    @Test
    void prepareTaskDate_invalidTimeFormat_expectNull() {
        String date = "25/3/2022";
        String time = "1300";
        LocalDateTime actualOutput = TaskParser.prepareTaskDate(date, time);
        assertEquals(null, actualOutput);
    }

    @Test
    void prepareTaskDate_validInput_expectDateTime() {
        String date = "25/3/2022";
        String time = "13:00";
        LocalDateTime actualOutput = TaskParser.prepareTaskDate(date, time);
        LocalDateTime expectedOutput = LocalDateTime.of(2022, Month.MARCH, 25, 13, 0);
        assertEquals(expectedOutput, actualOutput);
    }
}
