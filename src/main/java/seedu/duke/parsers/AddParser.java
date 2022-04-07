package seedu.duke.parsers;

import seedu.duke.exceptions.UnknownCommandException;

public abstract class AddParser extends Parser {

    public AddParser() {
        super();
    }

    public static AddParser getParser(String commandType) throws UnknownCommandException {
        switch (commandType) {
        case TASK:
            return new AddTaskParser();
        case MODULE:
            return new AddModuleParser();
        default:
            throw new UnknownCommandException();
        }
    }
}
