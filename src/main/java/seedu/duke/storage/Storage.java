package seedu.duke.storage;



import seedu.duke.exceptions.ModHappyException;


/**
 * Storage interfaces of ModHappy.
 * @param <T> Any data type
 */
public interface Storage<T extends Object> {

    /**
     * Writes a type T object to json file.
     * @param object Type T object
     * @param path Relative path of json file
     * @throws ModHappyException Write exception
     */
    void jsonWriter(T object, String path) throws ModHappyException;

    /**
     * Load and deserialize a type T object from json file.
     * @param path Relative path of json file
     * @return Type T object
     * @throws ModHappyException Read exception
     */
    T jsonReader(String path) throws ModHappyException;

    /**
     * Checks the existence of the storage file, and create if not exists.
     * @param path Relative path of json file
     * @throws ModHappyException Create file exception
     */
    void createTargetFile(String path) throws ModHappyException;
}