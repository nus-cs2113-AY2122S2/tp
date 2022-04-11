package seedu.duke.parser;

import seedu.duke.commands.DescCommand;
import seedu.duke.exceptions.InvMgrException;

public class DescCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the DescCommand
     * and returns a DescCommand object for execution.
     *
     * @throws InvMgrException if the user input does not conform to the expected format.
     */
    public DescCommand parse(String args) throws InvMgrException {
        int index = ParserUtils.parseIndex(args) - 1;
        return new DescCommand(index);
    }

}
