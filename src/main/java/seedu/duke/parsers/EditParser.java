package seedu.duke.parsers;

import seedu.duke.exceptions.UnknownCommandException;

//@@author heekit73098
public abstract class EditParser extends Parser {

    public EditParser() {
        super();
    }

    public static EditParser getParser(String commandType) throws UnknownCommandException {
        switch (commandType) {
        case TASK:
            return new EditTaskParser();
        case MODULE:
            return new EditModuleParser();
        default:
            throw new UnknownCommandException();
        }
    }
}
