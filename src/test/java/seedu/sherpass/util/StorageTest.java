package seedu.sherpass.util;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.task.Task;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StorageTest {

    @Test
    public void writeSaveData_oneTask_expectFileCreated() {
        File testFile = new File("data/test.json");
        if (testFile.exists()) {
            testFile.delete();
        }
        try {
            Storage storage = new Storage("data/test.json");
            TaskList tasks = new TaskList();
            tasks.addTask("task_one", LocalDate.parse("2022-12-12"), null);
            storage.writeSaveData(tasks);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        assertTrue(testFile.exists());
    }

    @Test
    public void load_oneTask_expectTaskList() {
        writeSaveData_oneTask_expectFileCreated();
        try {
            Storage storage = new Storage("data/test.json");
            TaskList actualList = new TaskList(storage.load());
            Task task = actualList.getTasks().get(0);
            assertEquals(task.getDescription(), "task_one");
            assertEquals(task.getByDate(), LocalDate.parse("2022-12-12"));
            assertEquals(task.getDoOnDate(), null);
            assertEquals(task.getStatusIcon(), " ");
        } catch (InvalidInputException | IOException | JSONException exception) {
            exception.printStackTrace();
        }
    }
}
