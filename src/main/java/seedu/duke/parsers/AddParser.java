package seedu.duke.parsers;

import seedu.duke.commands.AddCommand;
import seedu.duke.commands.Command;
import seedu.duke.exceptions.ModHappyException;

public class AddParser extends Parser {

    private static final String ADD_COMMAND_WORD = "add";
    private static final String FLAG = "\t";
    private static final String COMMAND_WORD = "commandWord";
    private static final String ADD_FORMAT = "\\s*(?<flag>(\\/(m|t)))\\s"
            + "+(?<argument1>[^\\-]*)\\s*((?<subFlag>\\-(\\bmod|d\\b))\\s+(?<argument2>.+))*";

    public AddParser() {
        super();
        // See also https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
        this.commandFormat = ADD_FORMAT;
        groupNames.add("flag");
        groupNames.add("argument1");
        groupNames.add("subFlag");
        groupNames.add("argument2");
    }

    
    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        return null;
    }
}
