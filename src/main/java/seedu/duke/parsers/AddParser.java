package seedu.duke.parsers;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.ModHappyException;

import java.util.HashMap;
import java.util.HashSet;

public class AddParser extends Parser{

    private static final String ADD_COMMAND_WORD = "add";

    public AddParser(){
        super();
        // See also https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html

    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        return null;
    }
}
