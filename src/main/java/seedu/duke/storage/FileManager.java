package seedu.duke.storage;

import java.io.File;
import java.io.IOException;

import seedu.duke.FolderCreationFailException;
import seedu.duke.HotelLiteManagerException;
import seedu.duke.FileCreationFailException;

public abstract class FileManager {
    public static final String FILE_SEPARATOR = "\\|";

    public File getFile(String filePath) throws HotelLiteManagerException {
        File file = new File(filePath);
        boolean isDirectoryCreated;
        File directory = file.getParentFile();
        if (!directory.exists()) {
            isDirectoryCreated = directory.mkdirs();
            if (!isDirectoryCreated) {
                throw new FolderCreationFailException();
            }
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new FileCreationFailException();
            }
        }
        return file;
    }

    public boolean isFileExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
}
