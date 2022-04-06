package seedu.sherpass.util.parser;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.task.Task;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StorageParserTest {

    @Test
    void parseSaveData_validData_expectTaskReturned() throws InvalidInputException {
        String testInput = "{\n"
                + "    \"by_date\": \"7/6/2022 23:59\",\n"
                + "    \"identifier\": 57077,\n"
                + "    \"do_date_start\": \"6/6/2022 18:00\",\n"
                + "    \"description\": \"test_task\",\n"
                + "    \"do_date_end\": \"6/6/2022 20:00\",\n"
                + "    \"status\": \" \"\n"
                + "}";
        JSONObject taskInJson = new JSONObject(testInput);
        Task actualTask = StorageParser.parseSaveData(taskInJson);
        assertEquals(actualTask.getIdentifier(), 57077);
        assertEquals(actualTask.getDescription(), "test_task");
        assertEquals(actualTask.getDoOnStartDateTime(),
                LocalDateTime.of(2022,6,6,18,0));
        assertEquals(actualTask.getDoOnEndDateTime(),
                LocalDateTime.of(2022,6,6,20,0));
        assertEquals(actualTask.getByDateTime(),
                LocalDateTime.of(2022,6,7,23,59));
        assertEquals(actualTask.isDone(), false);
    }

    @Test
    void parseSaveData_dataMissingIdentifier_expectExceptionThrown() {
        String testInput = "{\n"
                + "    \"by_date\": \"7/6/2022 23:59\",\n"
                + "    \"do_date_start\": \"6/6/2022 18:00\",\n"
                + "    \"description\": \"test_task\",\n"
                + "    \"do_date_end\": \"6/6/2022 20:00\",\n"
                + "    \"status\": \" \"\n"
                + "}";
        JSONObject taskInJson = new JSONObject(testInput);
        assertThrows(InvalidInputException.class, () -> {
            StorageParser.parseSaveData(taskInJson);
        });
    }
}