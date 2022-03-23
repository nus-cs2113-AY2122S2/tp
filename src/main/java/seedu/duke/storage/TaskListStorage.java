package seedu.duke.storage;

import java.io.File;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.ReadException;
import seedu.duke.data.Task;

/**
 * A data access object managing the loading and saving of TaskList instances.
 */
public class TaskListStorage extends ListStorage<Task> {

    /**
     * Deserialises the TaskList stored in the json file.
     * @param path json file path
     * @return deserialised TaskList object
     * @throws ModHappyException if an error was encountered during reading
     */
    @Override
    public ArrayList<Task> jsonReader(String path) throws ModHappyException {
        Gson gson = new GsonBuilder().create();
        Path file = new File(path).toPath();
        try {
            Reader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8);
            Task[] list = gson.fromJson(reader, Task[].class);
            ArrayList<Task> arrayList;
            if (!Objects.isNull(list)) {
                arrayList = new ArrayList<>(Arrays.asList(list));
            } else {
                arrayList = new ArrayList<>();
            }
            return arrayList;

        } catch (Exception e) {
            throw new ReadException();
        }
    }

}
