package seedu.duke.storage;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.ReadException;
import seedu.duke.exceptions.UnknownException;
import seedu.duke.util.Configuration;

import static seedu.duke.util.StringConstants.MODIFIED_JSON_EXCEPTION;

//@@author Ch40gRv1-Mu
public class ConfigurationStorage extends JsonStorage<Configuration> {
    @Override
    public Configuration loadData(String path) throws ModHappyException {
        Gson gson = new GsonBuilder().create();
        Path file = new File(path).toPath();
        try {
            Reader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8);
            return gson.fromJson(reader, (Type) Configuration.class);
        } catch (JsonSyntaxException e) {
            throw new ReadException();
        } catch (JsonParseException | IOException e) {
            throw new ReadException(MODIFIED_JSON_EXCEPTION);
        } catch (Exception e) {
            throw new UnknownException(e.toString());
        }
    }
}
