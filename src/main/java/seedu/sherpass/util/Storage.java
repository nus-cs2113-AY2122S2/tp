package seedu.sherpass.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import seedu.sherpass.enums.Frequency;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.exception.TimeClashException;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.parser.StorageParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import java.util.List;

import static seedu.sherpass.constant.DateAndTimeFormat.inputWithTimeFormat;
import static seedu.sherpass.constant.Index.INDEX_DIRECTORY;
import static seedu.sherpass.constant.Index.INDENT_FACTOR;
import static seedu.sherpass.constant.Message.ERROR_IO_FAILURE_MESSAGE;

public class Storage {
    private final String saveFilePath;

    /**
     * Creates a constructor for the class Storage.
     * Initialises and creates location for save file if missing.
     *
     * @param filePath Location of the save file.
     * @throws IOException If failed to read save file.
     */
    public Storage(String filePath) throws IOException {
        saveFilePath = filePath;
        System.out.println("Booting up...");
        String[] directoryName = filePath.split("/");
        File saveDirectory = new File(directoryName[INDEX_DIRECTORY]);
        if (saveDirectory.mkdir()) {
            System.out.println("Creating save directory...");
        }
        File saveState = new File(filePath);
        if (saveState.createNewFile()) {
            System.out.println("Creating new save state...");
        }
    }

    /**
     * Wipes the existing save file.
     */
    public void wipeSaveData() {
        try {
            FileWriter fw = new FileWriter(saveFilePath);
            fw.close();
        } catch (IOException e) {
            System.out.println(ERROR_IO_FAILURE_MESSAGE);
            System.exit(1);
        }
    }

    /**
     * Returns the JSON representation of the task list.
     *
     * @param taskList The task list to be converted into JSON format.
     * @return JSONObject containing the tasks in the task list.
     */
    private JSONObject convertTaskListToJson(TaskList taskList) {
        JSONObject json = new JSONObject();
        JSONArray tasks = new JSONArray();
        for (Task t : taskList.getTasks()) {
            JSONObject taskToStore = new JSONObject();
            taskToStore.put("identifier", t.getIdentifier());
            taskToStore.put("status", t.getStatusIcon());
            taskToStore.put("by_date",
                    (t.getByDateTime() == null ? " " : t.getByDateTime().format(inputWithTimeFormat)));
            taskToStore.put("do_date_start", t.getDoOnStartDateTime().format(inputWithTimeFormat));
            taskToStore.put("do_date_end", t.getDoOnEndDateTime().format(inputWithTimeFormat));
            taskToStore.put("description", t.getDescription());
            tasks.put(taskToStore);
        }
        json.put("tasks", tasks);
        return json;
    }

    /**
     * Overwrites existing saved data in save file with new data.
     *
     * @param taskList Array of tasks that are to be saved.
     */
    public void writeSaveData(TaskList taskList) {
        JSONObject taskJson = convertTaskListToJson(taskList);
        String taskString = taskJson.toString(INDENT_FACTOR);
        assert taskString != null;
        try {
            FileWriter writer = new FileWriter(saveFilePath);
            writer.write(taskString);
            writer.close();
        } catch (IOException e) {
            System.out.println(ERROR_IO_FAILURE_MESSAGE);
        }
    }

    /**
     * Loads the save file into the task list.
     *
     * @throws IOException           If an I/O error occurs while reading the data file
     * @throws InvalidInputException If the data has missing fields for a task
     * @throws JSONException         If the data file has an invalid JSON format
     */
    public void load(TaskList taskList) throws IOException, InvalidInputException, JSONException, TimeClashException {
        List<String> dataLines = Files.readAllLines(new File(saveFilePath).toPath());
        if (dataLines.size() > 0) {
            String dataString = String.join("\n", dataLines);
            JSONArray taskArray = new JSONObject(dataString).getJSONArray("tasks");

            for (int i = 0; i < taskArray.length(); i++) {
                JSONObject taskData = taskArray.getJSONObject(i);
                taskList.addTask(StorageParser.parseSaveData(taskData), Frequency.SINGLE, true);
            }
        }
        writeSaveData(taskList);
    }
}
