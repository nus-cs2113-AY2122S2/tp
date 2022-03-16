package seedu.duke.storage;

import seedu.duke.data.Item;

import com.google.gson.Gson;
import seedu.duke.exceptions.InvMgrException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class Storage {
    private static final String WRITER_IO_ERROR = "An IO error occured while writing the data file. Your data may or may not have been saved.";
    private static final String CREATE_FILE_IOERROR = "An IO error occured while creating the data file. Any changes made while running will not be saved!";
    private static final String INVALID_PATH = "The given path has an invalid format.";

    private final String filePath;
    private Path dataPath;

    public Storage(String filePath) throws InvMgrException {
        this.filePath = filePath;
        this.dataPath = setPath(filePath);
    }

    /**
     * Writes the inventory list into the data file.
     *
     * @param itemList the ArrayList of items to write to the data file
     * @throws InvMgrException for any IO exceptions while writing
     */
    public void writeData(ArrayList<Item> itemList) throws InvMgrException {
        try {
            Gson gson = new Gson();
            String serializedItems = gson.toJson(itemList);
            Files.writeString(this.dataPath, serializedItems, StandardCharsets.UTF_8, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new InvMgrException(WRITER_IO_ERROR);
        }
    }

    /**
     * Returns the Path representing the data file.
     * @return
     */
    public Path getDataPath() {
        return dataPath;
    }

    /**
     * Checks if the given filePath points to an existing JSON data file.
     * If the filePath does not point to a valid file, the relevant files and subdirectories are created.
     * If the filePath points to a valid file, a Path object is returned pointing to the JSON data file.
     *
     * @param filePath the JSON data file to load
     * @return Path object pointing to JSON data file
     */
    private Path setPath(String filePath) throws InvMgrException {
        Path
                dataPath = Paths.get(filePath);
        try {
            int directoryElementIndex = dataPath.getNameCount() - 1;
            assert directoryElementIndex>=0: "directoryElementIndex below 0";
            Path directory = dataPath.subpath(0, directoryElementIndex);
            Files.createDirectories(directory.toAbsolutePath());
            Files.createFile(dataPath);
            return dataPath;
        } catch (FileAlreadyExistsException e) {
            return dataPath;
        } catch (IllegalArgumentException e) {
            throw new InvMgrException(INVALID_PATH ,e);
        } catch (IOException e) {
            throw new InvMgrException(CREATE_FILE_IOERROR, e);
        }
    }
}
