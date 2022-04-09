package seedu.duke.assets;

import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.UserInputErrorException;
import seedu.duke.exception.NotFoundException;

public abstract class List {

    public abstract void add(String[] parameters) throws DuplicateEntryException;

    public abstract void remove(String parameters) throws NotFoundException;

    public abstract void view() throws UserInputErrorException;

    public abstract void view(String parameter) throws UserInputErrorException;

    public abstract void edit(String[] parameters) throws NotFoundException;

}
