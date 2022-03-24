package seedu.duke.storage;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.google.gson.Gson;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.WriteException;

/**
 * A data access object that can manage the storage of ArrayList instances.
 * @param <ModHappyT> ModHappy class
 */
public abstract class ListStorage<ModHappyT extends Object> extends JsonStorage<ArrayList<ModHappyT>> {

    @Override
    public abstract ArrayList<ModHappyT> loadData(String path) throws ModHappyException;

}
