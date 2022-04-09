package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.UserInputErrorException;
import seedu.duke.exception.NotFoundException;
import seedu.duke.status.Status;


public abstract class Command {
    public String[] parameterArray;

    public Command(String[] parameterArray) {
        this.parameterArray = parameterArray;
    }

    public abstract Status execute(List list) throws DuplicateEntryException, NotFoundException,
            UserInputErrorException;
}


