package seedu.duke.storage;

import seedu.duke.exceptions.ModHappyException;

//@@author Ch40gRv1-Mu
/**
 * Storage interfaces of ModHappy.
 * @param <T> any data type
 */
public interface Storage<T> {

    /**
     * Writes an object of type T to a json file.
     * @param path The json file path
     * @throws ModHappyException If an error was encountered during writing
     */
    void writeData(T object, String path) throws ModHappyException;

    /**
     * Load and deserialize a type T object from json file.
     * @param path The json file path
     * @return The unserialised object of type T
     * @throws ModHappyException If an error was encountered during reading
     */
    T loadData(String path) throws ModHappyException;


}