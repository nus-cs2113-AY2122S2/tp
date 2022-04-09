package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.HalpmiException;
import seedu.duke.exception.NotFoundException;
import seedu.duke.status.Status;

/*
 * Abstract class that is parent to various other specific command classes.
 * Holds an array of Strings named parameterArray.
 */
public abstract class Command {
    public String[] parameterArray;

    /*
     * Constructor method that assigns value to parameterArray
     * @ param parameterArray an array of Strings - additional parameters given by user
     */
    public Command(String[] parameterArray) {
        this.parameterArray = parameterArray;
    }

    /*
     * Abstract Method that takes in a list that needs to be acted on.
     * @ param list a List object
     */
    public abstract Status execute(List list) throws DuplicateEntryException, NotFoundException,
            HalpmiException;
}


