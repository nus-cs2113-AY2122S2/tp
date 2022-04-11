package seedu.sherpass.command;

import org.junit.jupiter.api.Test;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.sherpass.constant.DateAndTimeFormat.inputWithTimeFormat;

public class EditCommandTest {

    //@@author jltha
    @Test
    void execute_editByDateAndByTimeOnly_expectSuccess() throws IOException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Task testTask = new Task(1, "blah",
                LocalDateTime.parse("15/5/2022 15:00", inputWithTimeFormat),
                LocalDateTime.parse("31/3/2022 10:00", inputWithTimeFormat),
                LocalDateTime.parse("31/3/2022 12:00", inputWithTimeFormat));
        ArrayList<Task> dummyList = new ArrayList<>();
        dummyList.add(testTask);
        TaskList mainDummyList = new TaskList(dummyList);
        Ui ui = new Ui();
        Storage storage = new Storage("data/testEdit.json");
        EditCommand testCommand = new EditCommand(0, "blah",
                null, null, null);
        testCommand.setRepeating(false);
        testCommand.setByDate(LocalDateTime.parse("13/5/2022 12:00", inputWithTimeFormat));
        testCommand.execute(mainDummyList, ui, storage);
        String expectedOutput = "Booting up..."
                + System.lineSeparator()
                + "Okay! I've edited this task:" + System.lineSeparator()
                + "\t1. [ ] blah (to do on: Thu, 31/03/2022 10:00 - 12:00) (by: Fri, 13/05/2022 12:00)"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
}
