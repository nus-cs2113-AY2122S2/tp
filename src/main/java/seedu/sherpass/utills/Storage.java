package seedu.sherpass.utills;

import seedu.sherpass.exception.InputRepeatedException;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.sherpass.constant.Indexes.DIRECTORY_INDEX;
import static seedu.sherpass.constant.Messages.ERROR_CORRUPT_SAVED_FILE_MESSAGE;
import static seedu.sherpass.constant.Messages.ERROR_FILE_NOT_FOUND_MESSAGE;
import static seedu.sherpass.constant.Messages.ERROR_IO_FAILURE_MESSAGE;

public class Storage {
    private String saveFilePath;


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

    private void wipeSavedData() {
        try {
            // Create new FileWriter to overwrite existing file. But
            // no new data is written to overwrite file content, so content remains empty, i.e. clears file content
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
     * @param newTaskDate Task Date and/or time.
     * @param taskStatus Mark status of the task.
     * @param taskType Task Type.
     */
    public void appendToFile(String newTaskDescription,
                             String newTaskDate, String taskStatus,
                             String taskType) {
        try {
            FileWriter fw = new FileWriter(saveFilePath, true);
            String textToAppend = taskType + " | " + taskStatus + " | "
                    + newTaskDescription;
            if (!taskType.equals("T")) {
                textToAppend += " | " + newTaskDate;
            }

            fw.write(textToAppend + System.lineSeparator());
            fw.close();

        } catch (IOException e) {
            System.out.println(ERROR_IO_FAILURE_MESSAGE);
        }
    }


    /**
     * Overwrites existing saved data in save file with new data.
     *
     * @param taskList Array of tasks that are to be saved.
     */
    public void rewriteSavedState(TaskList taskList) {
        wipeSavedData();
        ArrayList<Task> replicatedTasks = taskList.getTasks();
        String taskStatus = "0";
        for (Task task : replicatedTasks) {
            if (task.isDone()) {
                taskStatus = "1";
            }
            appendToFile(task.getDescription(), task.getDate(),
                    taskStatus, task.getType());
            taskStatus = "0";
        }
    }

    private ArrayList<Task> readSavedData() throws
            FileNotFoundException, ArrayIndexOutOfBoundsException, InvalidInputException {
        ArrayList<Task> decodedTasks = new ArrayList<>();
        File f = new File(saveFilePath);
        Scanner s = new Scanner(f);
        String[] taskRawData;
        Task taskParsedData;
        while (s.hasNext()) {
            taskRawData = s.nextLine().split(" \\| ");
            taskParsedData = Parser.parseSavedData(taskRawData);
            decodedTasks.add(taskParsedData);
        }
        return decodedTasks;
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
            ArrayList<Task> saveTaskList = readSavedData();
            checkForRepeatedInputs(saveTaskList);
            return saveTaskList;
        } catch (FileNotFoundException e) {
            System.out.println(ERROR_FILE_NOT_FOUND_MESSAGE);
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException | InvalidInputException | InputRepeatedException e) {
            System.out.println(ERROR_CORRUPT_SAVED_FILE_MESSAGE);
            wipeSavedData();
        }
        return new ArrayList<>();
    }

}
