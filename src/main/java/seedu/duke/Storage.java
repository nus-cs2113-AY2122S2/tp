package seedu.duke;

import org.json.JSONArray;
import org.json.JSONObject;
import seedu.duke.exception.InputRepeatedException;
import seedu.duke.exception.InvalidInputException;
import seedu.duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static seedu.duke.constant.Indexes.DIRECTORY_INDEX;
import static seedu.duke.constant.Messages.ERROR_CORRUPT_SAVED_FILE_MESSAGE;
import static seedu.duke.constant.Messages.ERROR_FILE_NOT_FOUND_MESSAGE;
import static seedu.duke.constant.Messages.ERROR_IO_FAILURE_MESSAGE;

public class Storage {
    private String saveFilePath;

    private static final String PLACEHOLDER_GET_BY_DATE = "2050-12-12";
    private static final String PLACEHOLDER_GET_DO_DATE = "2050-12-12";

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
    private void wipeSavedData() {
        try {
            FileWriter fw = new FileWriter(saveFilePath);
            fw.close();
        } catch (IOException e) {
            System.out.println(ERROR_IO_FAILURE_MESSAGE);
            System.exit(1);
        }
    }

    /**
     * Appends new tasks to the save file.
     *
     * @param newTaskDescription Task Description.
     * @param newTaskByDate Task Deadline and/or time.
     * @param newTaskDoDate Task Do date
     * @param taskStatus Mark status of the task.
     */
    public void appendToFile(String newTaskDescription,
                             String newTaskByDate, String newTaskDoDate,
                             String taskStatus) {
        try {
            FileWriter fw = new FileWriter(saveFilePath, true);
            String textToAppend = taskStatus + " | "
                    + newTaskDescription + " | " + newTaskByDate
                    + " | " + newTaskDoDate;

            fw.write(textToAppend + System.lineSeparator());
            fw.close();

        } catch (IOException e) {
            System.out.println(ERROR_IO_FAILURE_MESSAGE);
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
            JSONObject task = new JSONObject();
            task.put("status", t.getStatusIcon());
            task.put("by_date", PLACEHOLDER_GET_BY_DATE);
            task.put("do_date", PLACEHOLDER_GET_DO_DATE);
            task.put("description", t.getDescription());
            tasks.put(task);
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
        String taskString = taskJson.toString(4);
        try {
            FileWriter writer = new FileWriter(saveFilePath);
            writer.write(taskString);
            writer.close();
        } catch (IOException e) {
            System.out.println(ERROR_IO_FAILURE_MESSAGE);
        }
    }

    private ArrayList<Task> readSavedData() throws IOException, InvalidInputException {
        ArrayList<Task> taskList = new ArrayList<>();
        List<String> dataLines = Files.readAllLines(new File(saveFilePath).toPath());
        String dataString = String.join("", dataLines);
        JSONObject dataJson = new JSONObject(dataString);
        JSONArray array = dataJson.getJSONArray("tasks");
        for (int i = 0; i < array.length(); i++) {
            JSONObject taskData = array.getJSONObject(i);
            taskList.add(Parser.parseSavedData(taskData));
        }
        return taskList;
    }

    private boolean isTaskRepeated(ArrayList<Task> saveTaskList, int index) {
        for (int j = index + 1; j < saveTaskList.size(); j++) {
            if (saveTaskList.get(index).getDescription().trim()
                    .equalsIgnoreCase(saveTaskList.get(j).getDescription().trim())) {
                return true;
            }
        }
        return false;
    }

    private void checkForRepeatedInputs(ArrayList<Task> saveTaskList) throws InputRepeatedException {
        for (int i = 0; i < saveTaskList.size() - 1; i++) {
            if (isTaskRepeated(saveTaskList, i)) {
                throw new InputRepeatedException();
            }
        }
    }

    /**
     * Loads back the save file onto the program.
     *
     * @return The saved data of the tasks in the saved file.
     *       Tasks are represented in an array.
     */
    public ArrayList<Task> load() {
        try {
            ArrayList<Task> savedTaskList = readSavedData();
            return savedTaskList;
        } catch (IOException e) {
            System.out.println(ERROR_FILE_NOT_FOUND_MESSAGE);
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException | InvalidInputException e) {
            System.out.println(ERROR_CORRUPT_SAVED_FILE_MESSAGE);
            wipeSavedData();
        }
        return new ArrayList<>();
    }

}
