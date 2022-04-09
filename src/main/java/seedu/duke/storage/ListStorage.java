package seedu.duke.storage;


import java.util.ArrayList;

import seedu.duke.exceptions.ModHappyException;

//@@author Ch40gRv1-Mu

/**
 * A data access object that can manage the storage of ArrayList instances.
 * @param <ModHappyT> ModHappy class
 */
public abstract class ListStorage<ModHappyT> extends JsonStorage<ArrayList<ModHappyT>> {

    @Override
    public abstract ArrayList<ModHappyT> loadData(String path) throws ModHappyException;

}
