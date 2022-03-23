package seedu.sherpass.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import java.util.ArrayList;
import java.util.List;

import static seedu.sherpass.constant.DateAndTimeFormat.inputWithTimeFormat;
import static seedu.sherpass.constant.Index.DIRECTORY_INDEX;
import static seedu.sherpass.constant.Index.INDENT_FACTOR;
import static seedu.sherpass.constant.Message.ERROR_CORRUPT_SAVED_FILE_MESSAGE_1;
import static seedu.sherpass.constant.Message.ERROR_CORRUPT_SAVED_FILE_MESSAGE_2;
import static seedu.sherpass.constant.Message.ERROR_CORRUPT_SAVED_FILE_MESSAGE_3;
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
        File saveDirectory = new File(directoryName[DIRECTORY_INDEX]);
        if (saveDirectory.mkdir()) {
            System.out.println("Creating save directory...");
        }
        File saveState = new File(filePath);
        if (saveState.createNewFile()) {
            System.out.println("Creating new save state...");
        }
    }

    // Wipes the existing file
    private void wipeSaveData() {
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
                    (t.getByDate() == null ? " " : t.getByDate().format(inputWithTimeFormat)));
            taskToStore.put("do_date_start",
                    (t.getDoOnStartDateTime() == null ? " " : t.getDoOnStartDateTime().format(inputWithTimeFormat)));
            taskToStore.put("do_date_end",
                    (t.getDoOnStartDateTime() == null ? " " : t.getDoOnEndDateTime().format(inputWithTimeFormat)));
            taskToStore.put("frequency", (t.getRepeatFrequency() == null ? " " : t.getRepeatFrequency().toString()));
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
    public JSONObject writeSaveData(TaskList taskList) {
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
        return taskJson;
    }

    /**
     * Loads back the save file onto the program.
     *
     * @return ArrayList containing the tasks saved in the data file
     * @throws IOException           If an I/O error occurs while reading the data file
     * @throws InvalidInputException If the data has missing fields for a task
     * @throws JSONException         If the data file has an invalid JSON format
     */
    public ArrayList<Task> load() throws IOException, InvalidInputException, JSONException {
        ArrayList<Task> taskList = new ArrayList<>();
        List<String> dataLines = Files.readAllLines(new File(saveFilePath).toPath());
        if (dataLines.size() > 0) {
            String dataString = String.join("", dataLines);
            JSONArray taskArray = new JSONObject(dataString).getJSONArray("tasks");

            for (int i = 0; i < taskArray.length(); i++) {
                JSONObject taskData = taskArray.getJSONObject(i);
                taskList.add(Parser.parseSaveData(taskData));
            }
        }
        return taskList;
    }

    /**
     * Creates a new save file or exits the program.
     * <p>
     * When the save file fails to load, the user decides if the program creates new save file
     * or the user can manually inspect the save file.
     * </p>
     *
     * @param ui Ui for printing messages
     */
    public void handleCorruptedSave(Ui ui) {
        ui.showToUser(ERROR_CORRUPT_SAVED_FILE_MESSAGE_1);
        String response = "";

        while (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")) {
            ui.showToUser(ERROR_CORRUPT_SAVED_FILE_MESSAGE_2);
            response = ui.readCommand().trim();
        }

        assert response.equalsIgnoreCase("y") || response.equalsIgnoreCase("n");

        if (response.equalsIgnoreCase("y")) {
            wipeSaveData();
        } else {
            ui.showToUser(ERROR_CORRUPT_SAVED_FILE_MESSAGE_3);
            System.exit(1);
        }
    }

}
