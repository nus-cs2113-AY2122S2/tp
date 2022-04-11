package seedu.duke.storage;

import java.io.File;
import java.io.IOException;

import seedu.duke.exceptions.FolderCreationFailException;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.FileCreationFailException;

public abstract class FileManager {
    public static final String FILE_SEPARATOR = "\\|";

    /**
     * Returns a file object which allows the program to write to or read from the file which contains the
     * list to update or read from.
     *
     * @param filePath The path of the file containing the list to update or read from.
     * @return a File object which allows us to manipulate the file specified by the file path inputted into the
     *     function.
     * @throws HotelLiteManagerException if the folder that the file is stored in does not exist and we are unable to
     *                                   create it or if the file specified by the file path does not exist and we are
     *                                   unable to create it.
     */
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

    /**
     * Checks if the file specified by the file path exists.
     *
     * @param filePath The path of the file containing the list to update or read from.
     * @return true if the file specified by the file path exists. Otherwise, false.
     */
    public boolean isFileExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
}
