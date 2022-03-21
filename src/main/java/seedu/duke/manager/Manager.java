package seedu.duke.manager;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Manager class provides the base functionality that all Managers in the application must implement.
 * Managers function as the 'Controller' in the application.
 * <p>
 * Provides base implementation and helper methods to load data from database files.
 */
public abstract class Manager {
    /**
     * Database subdirectory name.
     */
    private static final String DATABASE_DIRECTORY = "data";
    /**
     * Data storage location.
     */
    private final String file;

    /**
     * Creates a Manager.
     *
     * @param file Name of the database file to store your required data (i.e. "employee.dat").
     */
    protected Manager(String file) {
        this.file = Path.of(DATABASE_DIRECTORY, file).toString();
    }

    /**
     * Ensure that the folder to store the database files are generated.
     *
     * @throws IOException Error when creating folder.
     */
    private void ensureDatabaseDirectoryExists() throws IOException {
        Files.createDirectories(Path.of(DATABASE_DIRECTORY));
    }

    /**
     * Wrapper to help with loading of database.
     *
     * @return Object.
     * @throws Exception For any errors while reading.
     */
    protected Object _load() throws Exception {
        this.ensureDatabaseDirectoryExists();

        FileInputStream fis = new FileInputStream(this.file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        Object o = ois.readObject();
        ois.close();
        fis.close();

        return o;
    }

    /**
     * Wrapper to help with saving of database.
     *
     * @param o Object to save.
     * @throws Exception For any errors while writing.
     */
    protected void _save(Object o) throws Exception {
        this.ensureDatabaseDirectoryExists();

        FileOutputStream fos = new FileOutputStream(this.file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(o);

        oos.close();
        fos.close();
    }

    /**
     * Subclasses must implement this method for reading and validating data read from database.
     *
     * @throws Exception When error loading data.
     */
    protected abstract void loadData() throws Exception;

    /**
     * Subclasses must implement this method for writing data to database.
     *
     * @throws Exception When error writing data.
     */
    public abstract void saveData() throws Exception;
}
