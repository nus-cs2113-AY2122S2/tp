package seedu.duke.storage;

import java.io.File;
import java.io.IOException;

public abstract class FileManager {
    public static final String FILE_SEPARATOR = " \\| ";

    public File getFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    public boolean isFileExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
}
