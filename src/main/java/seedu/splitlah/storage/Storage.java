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

    /**
     * Initializes a Storage object.
     */
    public Storage() {
        profile = new Profile();
    }

    /**
     * Returns a Profile object.
     *
     * @return A Project object.
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Checks to see if file directory for save file exists.
     *
     * @return true if file directory exists, or
     *         true if it does not exist but can be created,
     *         false if file directory does not exist and no directory was created.
     */
    public boolean hasDataDirectory() {
        File directory = new File(FILE_DIRECTORY);
        return directory.exists() || directory.mkdir();
    }

    /**
     * Checks to see if file exists.
     *
     * @return true if file exists, or
     *         true if it does not exist but can be created,
     *         false if file does not exist and no file was created.
     */
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

    /**
     * Saves the serialized Profile object into a file.
     *
     * @param profile A Profile object to be saved.
     * @throws IOException if an I/O error occurs while writing to save file.
     */
    public void saveProfileToFile(Profile profile) throws IOException {
        FileOutputStream file = new FileOutputStream(FILE_FULL_PATH);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(profile);
        out.close();
        file.close();
    }

    /**
     * Saves the data from the save file into the Profile object.
     *
     * @throws IOException            if an I/O error occurs while reading from the save file.
     * @throws ClassNotFoundException if Class of a serialized object cannot be found
     */
    public void loadStorage() throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(FILE_FULL_PATH);
        ObjectInputStream in = new ObjectInputStream(file);
        profile = (Profile) in.readObject();
        in.close();
        file.close();
    }
}
