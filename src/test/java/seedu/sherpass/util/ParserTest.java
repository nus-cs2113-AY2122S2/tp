package seedu.sherpass.util;

import org.junit.jupiter.api.Test;
import seedu.sherpass.exception.InvalidTimeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.sherpass.constant.Index.DEFAULT_TIMER_TWO;

public class ParserTest {

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
