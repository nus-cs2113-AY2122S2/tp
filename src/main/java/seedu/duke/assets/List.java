package seedu.duke.assets;

import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.NotFoundException;

public abstract class List {

    public abstract void add(String[] parameter) throws DuplicateEntryException;

    public abstract void remove(String parameter) throws NotFoundException;

    public abstract void view();

    public abstract void view(String parameter);
}
