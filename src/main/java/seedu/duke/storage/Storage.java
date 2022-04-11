package seedu.duke.storage;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import seedu.duke.data.BorrowRecord;
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
import java.util.List;

import seedu.duke.common.Messages;

public class Storage {
    private static final String DEFAULT_PATH = "data/inventoryData.json";

    private final String filePath;
    private static Path dataPath;

    public Storage(String filePath) throws InvMgrException {
        this.filePath = filePath;
        this.dataPath = validatePathExists(filePath);
    }

    /**
     * Loads the inventory list from a JSON file.
     *
     * @return an {@code ArrayList<Item>} representing a list of the serialized {@code Item} objects.
     * @throws InvMgrException for any IO errors or when there is a problem parsing the JSON file.
     */
    public ArrayList<Item> load() throws InvMgrException {
        ArrayList<Item> bufferTaskList;

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(BorrowRecord.class, new BorrowRecordAdapter())
                .create();
        try {
            List<String> jsonDataList = Files.readAllLines(dataPath);
            String wholeJsonData = String.join("\n", jsonDataList);
            TypeToken<ArrayList<Item>> dataType = new TypeToken<ArrayList<Item>>(){};
            bufferTaskList = gson.fromJson(wholeJsonData, dataType.getType());
        } catch (JsonParseException | NullPointerException e) {
            throw new InvMgrException(Messages.JSON_PARSING_ERROR,e);
        } catch (IOException e) {
            throw new InvMgrException(Messages.READ_FILE_IOERROR,e);
        }
        if (bufferTaskList == null) {
            bufferTaskList = new ArrayList<Item>();
        }
        return bufferTaskList;
    }

    /**
     * Writes the inventory list into the data file.
     *
     * @param itemList the ArrayList of items to write to the data file.
     * @throws InvMgrException for any IO exceptions while writing.
     */
    public void save(List<Item> itemList) throws InvMgrException {
        if (itemList == null) {
            throw new NullPointerException();
        }
        try {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(BorrowRecord.class, new BorrowRecordAdapter())
                    .create();

            String serializedItems = gson.toJson(itemList);
            Files.writeString(dataPath,
                    serializedItems,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new InvMgrException(Messages.WRITER_IO_ERROR);
        }
    }

    /**
     * Returns the Path representing the data file.
     *
     * @return the dataPath of this Storage.
     */
    public Path getDataPath() {
        return this.dataPath;
    }

    /**
     * Checks if the given filePath points to an existing JSON data file.
     * If the filePath does not point to a valid file, the relevant files and subdirectories are created.
     * If the filePath points to a valid file, a Path object is returned pointing to the JSON data file.
     *
     * @param filePath the JSON data file to load.
     * @return Path object pointing to JSON data file.
     * @throws InvMgrException for any IO errors or when filePath is not a valid directory path.
     */
    private Path validatePathExists(String filePath) throws InvMgrException {
        assert filePath != null : "filePath is null";
        try {
            Path dataPath = Paths.get(filePath);
            int directoryElementIndex = dataPath.getNameCount() - 1;
            assert directoryElementIndex >= 0 : "directoryElementIndex below 0";
            Path directory = dataPath.subpath(0, directoryElementIndex);
            Files.createDirectories(directory.toAbsolutePath());
            Files.createFile(dataPath);
            return dataPath;
        } catch (FileAlreadyExistsException e) {
            return Paths.get(filePath);
        } catch (NullPointerException e) {
            return validatePathExists(DEFAULT_PATH);
        } catch (IllegalArgumentException e) {
            throw new InvMgrException(Messages.INVALID_PATH, e);
        } catch (IOException e) {
            throw new InvMgrException(Messages.CREATE_FILE_IOERROR, e);
        }
    }
}
