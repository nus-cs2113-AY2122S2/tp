package seedu.duke.storage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import seedu.duke.exceptions.FileCreateFailException;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.WriteException;

public abstract class JsonStorage<T extends Object> implements Storage<T> {
    /**
     * Writes a ArrayList with elements of type ModHappyT to a json file.
     * @param path json file path
     * @throws ModHappyException if an error was encountered during writing
     */
    @Override
    public void jsonWriter(T object, String path) throws ModHappyException {
        try {
            createTargetFile(path);
            FileOutputStream fos = new FileOutputStream(path);
            OutputStreamWriter isr = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            Gson gson = new Gson();
            gson.toJson(object, isr);
            isr.close();
            fos.close();
        } catch (Exception e) {
            throw new WriteException();
        }

    }

    /**
     * Checks for the existence of the storage file and create it if it does not already exist.
     * @param path json file path
     * @throws ModHappyException if the file could not be created
     */
    @Override
    public void createTargetFile(String path) throws ModHappyException {
        File targetFile = new File(path);
        if (targetFile.exists()) {
            return;
        }
        try {
            targetFile.getParentFile().mkdirs();
            targetFile.createNewFile();
            return;
        } catch (Exception e) {
            throw new FileCreateFailException();
        }
    }

    @Override
    public abstract T jsonReader(String path) throws ModHappyException;

}
