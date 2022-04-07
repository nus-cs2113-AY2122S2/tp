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

import seedu.duke.exceptions.DuplicateModuleException;
import seedu.duke.exceptions.InvalidModuleException;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.ReadException;

import seedu.duke.data.Module;
import seedu.duke.util.NumberConstants;

/**
 * A data access object managing the loading and saving of ModuleList instances.
 */
public class ModuleListStorage extends ListStorage<Module> {
    private static final int MAXIMUM_MODULAR_CREDITS = NumberConstants.MAXIMUM_MODULAR_CREDITS;

    /**
     * Deserialises the ModuleList stored in the json file.
     * @param path json file path
     * @return deserialised ModuleList object
     * @throws ModHappyException if an error was encountered during reading
     */
    @Override
    public ArrayList<Module> loadData(String path) throws ModHappyException {
        Gson gson = new GsonBuilder().create();
        Path file = new File(path).toPath();
        ArrayList<Module> arrayList;
        try {
            Reader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8);
            Module[] list = gson.fromJson(reader, Module[].class);
            if (!Objects.isNull(list)) {
                arrayList = new ArrayList<>(Arrays.asList(list));
            } else {
                arrayList = new ArrayList<>();
            }
        } catch (Exception e) {
            throw new ReadException();
        }
        return arrayList;
    }

}
