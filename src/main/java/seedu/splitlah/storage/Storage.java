package seedu.splitlah.storage;

import seedu.splitlah.data.Profile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {

    private static final String FILE_DIRECTORY = "data/";
    private static final String FILE_NAME = "SplitLah.data";
    private static final String FILE_FULL_PATH = FILE_DIRECTORY + FILE_NAME;

    private Profile profile;

    public Storage() {
        profile = new Profile();
    }
    public Profile getProfile() {
        return profile;
    }
    public boolean hasDataDirectory() {
        File directory = new File(FILE_DIRECTORY);
        return directory.exists() || directory.mkdir();
    }
    public boolean hasDataFile() {
        File file = new File(FILE_FULL_PATH);
        try {
            if (!file.exists() && !file.createNewFile()) {
                return true;
            }
        } catch (IOException ioException) {
            return false;
        }
        return true;
    }
    public void saveProfileToFile(Profile profile) throws IOException {
        FileOutputStream file = new FileOutputStream(FILE_FULL_PATH);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(profile);
        out.close();
        file.close();
    }
    public void loadStorage() throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(FILE_FULL_PATH);
        ObjectInputStream in = new ObjectInputStream(file);
        profile = (Profile) in.readObject();
        in.close();
        file.close();
    }
}
