package seedu.duke.storage;

import seedu.duke.exceptions.ModHappyException;

/**
 * Storage interfaces of ModHappy.
 * @param <T> any data type
 */
public interface Storage<T extends Object> {

    /**
     * Writes an object of type T to a json file.
     * @param path json file path
     * @throws ModHappyException if an error was encountered during writing
     */
    void writeData(T object, String path) throws ModHappyException;

    /**
     * Load and deserialize a type T object from json file.
     * @param path json file path
     * @return the unserialised object of type T
     * @throws ModHappyException if an error was encountered during reading
     */
    T loadData(String path) throws ModHappyException;

    /**
     * Checks the existence of the storage file, and create if not exists.
     * @param path json file path
     * @throws ModHappyException if the file could not be created
     */
    void createTargetFile(String path) throws ModHappyException;
}