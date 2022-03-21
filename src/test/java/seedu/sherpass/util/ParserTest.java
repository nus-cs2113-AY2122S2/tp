package seedu.sherpass.util;

import org.junit.jupiter.api.Test;
import seedu.sherpass.exception.InvalidTimeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.sherpass.constant.Index.DEFAULT_TIMER_TWO;

public class ParserTest {

    @Test
    void parseArgument_byParameter_expectDateTime() {
        String date = "foo /by 21/12/2022 09:00";
        String expected = "21/12/2022 09:00";
        String result = Parser.parseArgument("/by", date);
        assertEquals(result, expected);
    }

    @Test
    void parseArgument_invalidParameter_expectEmptyString() {
        String date = "foo /by 21/12/2022 09:00";
        String expected = "";
        String result = Parser.parseArgument("/do", date);
        assertEquals(result, expected);
    }

    @Test
    void parseDescription_validDescription_expectFoo() {
        String input = "foo /by 21/12/2022";
        String expected = "foo";
        String result = Parser.parseDescription(input);
        assertEquals(result, expected);
    }

    @Test
    void parseDescription_emptyDescription_expectEmptyString() {
        String input = "/by 21/12/2022";
        String expected = "";
        String result = Parser.parseDescription(input);
        assertEquals(result, expected);
    }

    @Test
    void parseTimerInput_noInput_InvalidTimeExceptionThrown() {
        String studyCommandInput = "start ";
        String[] timerInput = studyCommandInput.split(" ", 2);
        assertThrows(InvalidTimeException.class,
            () -> Parser.parseTimerInput(timerInput));
    }

    @Test
    void parseTimerInput_blankDefaultTimerInput_InvalidTimeExceptionThrown() {
        String studyCommandInput = "start  ";
        String[] timerInput = studyCommandInput.split(" ", 2);
        assertThrows(InvalidTimeException.class,
            () -> Parser.parseTimerInput(timerInput));
    }

    @Test
    void parseTimerInput_blankCustomTimerInput_NumberFormatExceptionThrown() {
        String studyCommandInput = "start /custom  ";
        String[] timerInput = studyCommandInput.split(" ", 2);
        assertThrows(NumberFormatException.class,
            () -> Parser.parseTimerInput(timerInput));
    }

    @Test
    void parseTimerInput_defaultTimerTwo_expectOneHour() throws InvalidTimeException {
        String studyCommandInput = "start 2";
        String[] timerInput = studyCommandInput.split(" ", 2);
        int duration = Parser.parseTimerInput(timerInput);
        assertEquals(DEFAULT_TIMER_TWO, duration);
    }

    @Test
    void parseTimerInput_customInput900_expect900Seconds() throws InvalidTimeException {
        String studyCommandInput = "start /custom 900";
        String[] timerInput = studyCommandInput.split(" ", 2);
        int duration = Parser.parseTimerInput(timerInput);
        assertEquals(900, duration);
    }

    @Test
    void parseTimerInput_defaultTimerTwoInputs_InvalidTimeExceptionThrown() {
        String studyCommandInput = "start 2 3";
        String[] timerInput = studyCommandInput.split(" ", 2);
        assertThrows(InvalidTimeException.class,
            () -> Parser.parseTimerInput(timerInput));
    }

    @Test
    void parseTimerInput_customTimerThreeInputs_NumberFormatExceptionThrown() {
        String studyCommandInput = "start /custom 600 1500 1200";
        String[] timerInput = studyCommandInput.split(" ", 2);
        assertThrows(NumberFormatException.class,
            () -> Parser.parseTimerInput(timerInput));
    }

    @Test
    void parseTimerInput_validDefaultAndCustomTimerInputs_InvalidTimeExceptionThrown() {
        String studyCommandInput = "start 2 /custom 900";
        String[] timerInput = studyCommandInput.split(" ", 2);
        assertThrows(InvalidTimeException.class,
            () -> Parser.parseTimerInput(timerInput));
    }

}
