package seedu.duke.storage;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.ReadException;

import seedu.duke.data.Module;
import seedu.duke.exceptions.UnknownException;

import static seedu.duke.util.StringConstants.MODIFIED_JSON_EXCEPTION;

//@@author Ch40gRv1-Mu
/**
 * A data access object managing the loading and saving of ModuleList instances.
 *
 */
public class ModuleListStorage extends ListStorage<Module> {

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

        } catch (JsonSyntaxException e) {
            throw new ReadException(MODIFIED_JSON_EXCEPTION);
        } catch (JsonParseException e) {
            throw new ReadException(MODIFIED_JSON_EXCEPTION);
        } catch (IOException e) {
            throw new ReadException();
        } catch (Exception e) {
            throw new UnknownException(e.toString());
        }
        return arrayList;
    }

}
