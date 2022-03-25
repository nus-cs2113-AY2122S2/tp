package seedu.sherpass.util;

import org.junit.jupiter.api.Test;
import seedu.sherpass.exception.InvalidTimeException;

import seedu.sherpass.util.parser.TaskParser;
import seedu.sherpass.util.parser.TimerParser;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.sherpass.constant.Index.DEFAULT_TIMER_TWO;

public class ParserTest {

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

    @Test
    void parseTimerInput_noInput_InvalidTimeExceptionThrown() {
        String studyCommandInput = "start ";
        String[] timerInput = studyCommandInput.split(" ", 2);
        assertThrows(InvalidTimeException.class,
            () -> TimerParser.parseTimerInput(timerInput));
    }

    @Test
    void parseTimerInput_blankDefaultTimerInput_InvalidTimeExceptionThrown() {
        String studyCommandInput = "start  ";
        String[] timerInput = studyCommandInput.split(" ", 2);
        assertThrows(InvalidTimeException.class,
            () -> TimerParser.parseTimerInput(timerInput));
    }

    @Test
    void parseTimerInput_blankCustomTimerInput_NumberFormatExceptionThrown() {
        String studyCommandInput = "start /custom  ";
        String[] timerInput = studyCommandInput.split(" ", 2);
        assertThrows(NumberFormatException.class,
            () -> TimerParser.parseTimerInput(timerInput));
    }

    @Test
    void parseTimerInput_defaultTimerTwo_expectOneHour() throws InvalidTimeException {
        String studyCommandInput = "start 2";
        String[] timerInput = studyCommandInput.split(" ", 2);
        int duration = TimerParser.parseTimerInput(timerInput);
        assertEquals(DEFAULT_TIMER_TWO, duration);
    }

    @Test
    void parseTimerInput_customInput900_expect900Seconds() throws InvalidTimeException {
        String studyCommandInput = "start /custom 900";
        String[] timerInput = studyCommandInput.split(" ", 2);
        int duration = TimerParser.parseTimerInput(timerInput);
        assertEquals(900, duration);
    }

    @Test
    void parseTimerInput_defaultTimerTwoInputs_InvalidTimeExceptionThrown() {
        String studyCommandInput = "start 2 3";
        String[] timerInput = studyCommandInput.split(" ", 2);
        assertThrows(InvalidTimeException.class,
            () -> TimerParser.parseTimerInput(timerInput));
    }

    @Test
    void parseTimerInput_customTimerThreeInputs_NumberFormatExceptionThrown() {
        String studyCommandInput = "start /custom 600 1500 1200";
        String[] timerInput = studyCommandInput.split(" ", 2);
        assertThrows(NumberFormatException.class,
            () -> TimerParser.parseTimerInput(timerInput));
    }

    @Test
    void parseTimerInput_validDefaultAndCustomTimerInputs_InvalidTimeExceptionThrown() {
        String studyCommandInput = "start 2 /custom 900";
        String[] timerInput = studyCommandInput.split(" ", 2);
        assertThrows(InvalidTimeException.class,
            () -> TimerParser.parseTimerInput(timerInput));
    }
}
