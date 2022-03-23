package seedu.duke.storage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import seedu.duke.exceptions.FileCreateFailException;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.ReadException;
import seedu.duke.exceptions.WriteException;
import seedu.duke.tasks.Module;
import seedu.duke.util.Configuration;


public class ConfigurationStorage extends JsonStorage<Configuration> {
    @Override
    public Configuration jsonReader(String path) throws ModHappyException {
        Gson gson = new GsonBuilder().create();
        Path file = new File(path).toPath();
        try {
            Reader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8);
            Configuration configuration = gson.fromJson(reader, (Type) seedu.duke.util.Configuration.class);
            return configuration;
        } catch (Exception e) {
            throw new ReadException();
        }
    }
}
