package seedu.duke.storage;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.google.gson.Gson;

import seedu.duke.exceptions.FileCreateFailException;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.WriteException;


/**
 * A data access object that can manage the storage of ArrayList style objects.
 * @param <ModHappyT> ModHappy class
 */
public abstract class ListStorage<ModHappyT extends Object> implements Storage<ArrayList<ModHappyT>> {


    /**
     * Writes a ArrayList of ModHappy Data type into a json file.
     * @param arrayList ArrayList of ModHappy objects to be written
     * @param path The relative path of storage file to the project root
     * @throws ModHappyException Write fail exception
     */
    @Override
    public void jsonWriter(ArrayList<ModHappyT> arrayList, String path) throws ModHappyException {
        try {
            createTargetFile(path);
            FileOutputStream fos = new FileOutputStream(path);
            OutputStreamWriter isr = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            Gson gson = new Gson();
            gson.toJson(arrayList, isr);
            isr.close();
            fos.close();
        } catch (Exception e) {
            throw new WriteException();
        }
    }

    /**
     * Checks the existence of the storage file, and create if not exists.
     * @param path Relative path of the storage file
     * @throws ModHappyException Fail to create file exception
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
    public abstract ArrayList<ModHappyT> jsonReader(String path) throws ModHappyException;

}
