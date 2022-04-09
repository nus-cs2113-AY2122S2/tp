package seedu.duke.storage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import seedu.duke.exceptions.FileCreateFailException;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.UnknownException;
import seedu.duke.exceptions.WriteException;

//@@author Ch40gRv1-Mu
public abstract class JsonStorage<T> implements Storage<T> {
    /**
     * Writes a ArrayList with elements of type ModHappyT to a json file.
     * @param path json file path
     * @throws ModHappyException if an error was encountered during writing
     */
    @Override
    public void writeData(T object, String path) throws ModHappyException {
        try {
            createTargetFile(path);
            FileOutputStream fos = new FileOutputStream(path);
            OutputStreamWriter isr = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            Gson gson = new Gson();
            gson.toJson(object, isr);
            isr.close();
            fos.close();
        } catch (JsonParseException e) {
            throw new WriteException();
        } catch (Exception e) {
            throw new UnknownException(e.toString());
        }

    }


    /**
     * Checks for the existence of the storage file and create it if it does not already exist.
     * @param path json file path
     * @throws ModHappyException if the file could not be created
     */
    public void createTargetFile(String path) throws ModHappyException {
        File targetFile = new File(path);
        if (targetFile.exists()) {
            return;
        }
        try {
            // the result is ignored intentionally
            targetFile.getParentFile().mkdirs();
            targetFile.createNewFile();
        } catch (IOException e) {
            throw new FileCreateFailException();
        } catch (Exception e) {
            throw new UnknownException(e.toString());
        }
    }

    @Override
    public abstract T loadData(String path) throws ModHappyException;

}
