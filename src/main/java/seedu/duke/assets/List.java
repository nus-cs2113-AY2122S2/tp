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

    public abstract void findByNric(String[] parameters);

    public abstract void findByName(String[] parameters);

    public abstract void findByAge(String[] parameters);

    public abstract void findByGender(String[] parameters);

    public abstract void findByAddress(String[] parameters);

    public abstract void findByDob(String[] parameters);

    public abstract void findBySpecialization(String[] parameters);

    public abstract void findByDateAdmission(String[] parameters);

    public abstract void findById(String[] parameters);

    public abstract void findByDosage(String[] parameters);

    public abstract void findByExpiry(String[] parameters);

    public abstract void findBySideEffects(String[] parameters);

    public abstract void findByQuantity(String[] parameters);
}
