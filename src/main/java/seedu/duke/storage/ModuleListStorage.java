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
import seedu.duke.exceptions.ReadStreamBrokenException;

import seedu.duke.tasks.Module;


/**
 * A data access object managing the write and read of module list.
 */
public class ModuleListStorage extends ListStorage<Module> {
    /**
     * Reads and deserializes the json file and return the module list.
     * @param path the relative path of the storage file
     * @return Loaded module list
     * @throws ModHappyException Read module list fail exception
     */
    @Override
    public ArrayList<Module> jsonReader(String path) throws ModHappyException {
        Gson gson = new GsonBuilder().create();
        Path file = new File(path).toPath();
        try {
            Reader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8);
            Module[] list = gson.fromJson(reader, Module[].class);
            ArrayList<Module> arrayList;
            if (!Objects.isNull(list)) {
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
