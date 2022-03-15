package seedu.duke.storage;

import java.io.File;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.ReadStreamBrokenException;
import seedu.duke.tasks.Task;



/**
 * A data access object managing the write and read of task list.
 */
public class TaskListStorage extends ListStorage<Task> {

    /**
     * Reads and deserializes the json file and return the task list.
     * @param path the relative path of the storage file
     * @return Loaded task list
     * @throws ModHappyException Read task list fail exception
     */
    @Override
    public ArrayList<Task> jsonReader(String path) throws ModHappyException {
        Gson gson = new GsonBuilder().create();
        Path file = new File(path).toPath();
        try {
            Reader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8);
            Task[] list = gson.fromJson(reader, Task[].class);
            ArrayList<Task> arrayList;
            if (list != null) {
                arrayList = new ArrayList<>(Arrays.asList(list));
            } else {
                arrayList = new ArrayList<>();
            }
            return arrayList;

        } catch (Exception e) {
            throw new ReadStreamBrokenException();
        }
    }

}
