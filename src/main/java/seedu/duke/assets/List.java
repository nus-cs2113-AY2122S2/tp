package seedu.duke.assets;

import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.HalpmiException;
import seedu.duke.exception.NotFoundException;

public abstract class List {

    public abstract void add(String[] parameters) throws DuplicateEntryException;

    public abstract void remove(String parameters) throws NotFoundException;

    public abstract void view() throws HalpmiException;

    public abstract void view(String parameter) throws HalpmiException;

    public abstract void edit(String[] parameters) throws NotFoundException;

}
