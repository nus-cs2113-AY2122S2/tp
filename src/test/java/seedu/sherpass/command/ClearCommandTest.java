package seedu.sherpass.command;

import org.junit.jupiter.api.Test;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.exception.TimeClashException;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static seedu.sherpass.constant.Message.LS;
import static seedu.sherpass.constant.Message.CLEAR_ALL_COMMAND_CONFIRMATION_MESSAGE;
import static seedu.sherpass.constant.Message.CLEAR_ALL_COMMAND_CONFIRMED_MESSAGE;
import static seedu.sherpass.constant.Message.CLEAR_ALL_COMMAND_CANCEL_MESSAGE;
import static seedu.sherpass.constant.Message.CLEAR_ALL_COMMAND_RESULT_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_CLEAR_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_PREFIX;
import static seedu.sherpass.constant.CommandMessage.MESSAGE_USAGE_CLEAR;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClearCommandTest {

    @Test
    void execute_clearEmptyList_expectNoTaskRemoved() throws IOException, InvalidInputException, TimeClashException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ArrayList<Task> testArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(testArrayList);
        Storage storage = new Storage("data/test.json");
        storage.wipeSaveData();
        storage.load(taskList);
        outContent.reset();

        String selection1 = "expired";
        String selection2 = "done";
        String expectedOutput = "There are no expired tasks to be removed." + LS
                + "There are no completed tasks to be removed." + LS;
        new ClearCommand(selection1).execute(taskList, new Ui(), storage);
        new ClearCommand(selection2).execute(taskList, new Ui(), storage);
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void execute_clearListWithNoExpiredOrCompletedTasks_expectNoTaskRemoved()
            throws IOException, InvalidInputException, TimeClashException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ArrayList<Task> testArrayList = new ArrayList<>();
        testArrayList.add(new Task(1,"watch cs2113t videos", null,
                LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusMinutes(1)));
        TaskList taskList = new TaskList(testArrayList);

        Storage storage = new Storage("data/test.json");
        storage.wipeSaveData();
        storage.load(taskList);
        outContent.reset();

        String selection1 = "expired";
        String selection2 = "done";
        String expectedOutput = "There are no expired tasks to be removed." + LS
                + "There are no completed tasks to be removed." + LS;
        new ClearCommand(selection1).execute(taskList, new Ui(), storage);
        new ClearCommand(selection2).execute(taskList, new Ui(), storage);
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void execute_clearCompletedTask_expectTaskRemoved() throws IOException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ArrayList<Task> testArrayList = new ArrayList<>();
        testArrayList.add(new Task(1,"watch cs2113t videos", null,
                LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusMinutes(1)));
        testArrayList.get(0).markAsDone();
        TaskList taskList = new TaskList(testArrayList);

        Storage storage = new Storage("data/test.json");
        outContent.reset();

        String selection = "done";
        String expectedOutput = "Done! 1 task(s) have been removed." + LS
                + "Now you have 0 task(s) in the list." + LS;
        new ClearCommand(selection).execute(taskList, new Ui(), storage);
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void execute_clearExpiredTask_expectTaskRemoved() throws IOException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ArrayList<Task> testArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(testArrayList);
        testArrayList.add(new Task(1,"watch cs2113t videos", null,
                LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1)));

        Storage storage = new Storage("data/test.json");
        outContent.reset();

        String selection = "expired";
        String expectedOutput = "Done! 1 task(s) have been removed." + LS
                + "Now you have 0 task(s) in the list." + LS;
        new ClearCommand(selection).execute(taskList, new Ui(), storage);
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void execute_clearAll_expectAllTasksRemoved() throws IOException, InvalidInputException, TimeClashException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ByteArrayInputStream inContent = new ByteArrayInputStream("y".getBytes());
        System.setIn(inContent);

        ArrayList<Task> testArrayList = new ArrayList<>();
        testArrayList.add(new Task(1,"dummy description 1", null,
                LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusMinutes(1)));
        testArrayList.add(new Task(2,"dummy description 2", null,
                LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusMinutes(1)));
        testArrayList.add(new Task(3,"dummy description 3", null,
                LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).plusMinutes(1)));
        TaskList taskList = new TaskList(testArrayList);

        Storage storage = new Storage("data/test.json");
        storage.wipeSaveData();
        storage.load(taskList);
        outContent.reset();

        String selection = "all";
        String expectedOutput = CLEAR_ALL_COMMAND_CONFIRMATION_MESSAGE + LS
                + "____________________________________________________________" + LS
                + CLEAR_ALL_COMMAND_CONFIRMED_MESSAGE + LS
                + "____________________________________________________________" + LS
                + CLEAR_ALL_COMMAND_RESULT_MESSAGE + LS;
        new ClearCommand(selection).execute(taskList, new Ui(), storage);
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void execute_clearAllCancelConfirmation_expectCancelMessage()
            throws IOException, InvalidInputException, TimeClashException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ByteArrayInputStream inContent = new ByteArrayInputStream("n".getBytes());
        System.setIn(inContent);

        ArrayList<Task> testArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(testArrayList);

        Storage storage = new Storage("data/test.json");
        storage.wipeSaveData();
        storage.load(taskList);
        outContent.reset();

        String selection = "all";
        String expectedOutput = CLEAR_ALL_COMMAND_CONFIRMATION_MESSAGE + LS
                + "____________________________________________________________" + LS
                + CLEAR_ALL_COMMAND_CANCEL_MESSAGE + LS;
        new ClearCommand(selection).execute(taskList, new Ui(), storage);
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void execute_clearInvalidCommand_expectInvalidClearMessage() throws IOException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ArrayList<Task> testArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(testArrayList);
        Storage storage = new Storage("data/test.json");
        outContent.reset();

        String selection = "foo";
        String expectedOutput = ERROR_PREFIX + ERROR_INVALID_CLEAR_MESSAGE + LS
                + "____________________________________________________________" + LS
                + MESSAGE_USAGE_CLEAR + LS;
        new ClearCommand(selection).execute(taskList, new Ui(), storage);
        assertEquals(expectedOutput, outContent.toString());
    }
}
