package seedu.duke.commands;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.parsers.AddParser;

import java.util.HashMap;

public class MarkCommand extends Command{
    private static final String MARK_COMMAND_WORD = "mark";
    private static final String FLAG = "flag";
    private static final String COMPLETED_FLAG = "/c";
    private static final String UNCOMPLETED_FLAG = "/u";
    private static final String MARK_MESSAGE_TOP = "Nice! I have marked this task as completed!\n";
    private static final String UNMARK_MESSAGE_TOP = "Ok! I have marked this task for you as uncompleted!\n";

    public MarkCommand (String arg) throws ModHappyException {
        try {
            commandName = MARK_COMMAND_WORD;
            AddParser addParser = new AddParser();
            HashMap<String, String> parsedArg = addParser.parseString(arg);

            switch (parsedArg.get(FLAG)) {
            case COMPLETED_FLAG:


                break;
            case UNCOMPLETED_FLAG:
                //

                break;
            default:
                throw new UnsupportedOperationException();
            }
        } catch (ModHappyException e) {
            throw e;
        }
    }

    @Override
    public CommandResult execute() throws ModHappyException {
        return null;
    }
}
