package seedu.sherpass.util;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.timer.TimerLogic;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.sherpass.constant.Message.ERROR_INVALID_TIMER_INPUT_MESSAGE;


public class TimerTest {

    @Test
    void timerInvalidInput_expectError() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ArrayList<Task> testArrayList = new ArrayList<>();
        TaskList testTaskList = new TaskList(testArrayList);
        Ui ui = new Ui();
        TimerLogic timerLogic = new TimerLogic(testTaskList, ui);
        String rawInput = "start";
        String[] parsedInput = rawInput.trim().split(" ", 2);
        String expectedOutput = ERROR_INVALID_TIMER_INPUT_MESSAGE + System.lineSeparator();

        timerLogic.callStartTimer(parsedInput);
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void timerInvalidModeNumber_expectError() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ArrayList<Task> testArrayList = new ArrayList<>();
        TaskList testTaskList = new TaskList(testArrayList);
        Ui ui = new Ui();
        TimerLogic timerLogic = new TimerLogic(testTaskList, ui);
        String rawInput = "start 5";
        String[] parsedInput = rawInput.trim().split(" ", 2);
        String expectedOutput = ERROR_INVALID_TIMER_INPUT_MESSAGE + System.lineSeparator();

        timerLogic.callStartTimer(parsedInput);
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void timerNotInitialised_pauseCommand_expectError() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ArrayList<Task> testArrayList = new ArrayList<>();
        TaskList testTaskList = new TaskList(testArrayList);
        Ui ui = new Ui();
        TimerLogic timerLogic = new TimerLogic(testTaskList, ui);
        String expectedOutput = "You don't have a timer running!" + System.lineSeparator();

        timerLogic.callPauseTimer();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void timerNotInitialised_resumeCommand_expectError() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ArrayList<Task> testArrayList = new ArrayList<>();
        TaskList testTaskList = new TaskList(testArrayList);
        Ui ui = new Ui();
        TimerLogic timerLogic = new TimerLogic(testTaskList, ui);
        String expectedOutput = "You don't have a timer running!" + System.lineSeparator();

        timerLogic.callResumeTimer();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void timerNotInitialised_stopCommand_expectError() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ArrayList<Task> testArrayList = new ArrayList<>();
        TaskList testTaskList = new TaskList(testArrayList);
        Ui ui = new Ui();
        TimerLogic timerLogic = new TimerLogic(testTaskList, ui);
        String expectedOutput = "You don't have a timer running!" + System.lineSeparator();

        timerLogic.callStopTimer();
        assertEquals(expectedOutput, outContent.toString());
    }

}
