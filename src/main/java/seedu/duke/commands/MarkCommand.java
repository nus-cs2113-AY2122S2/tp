package seedu.duke.commands;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.parsers.AddParser;
import seedu.duke.parsers.MarkParser;

import java.util.HashMap;

public class MarkCommand extends Command {
    private static final String MARK_COMMAND_WORD = "mark";
    private static final String FLAG = "flag";
    private static final String COMPLETED_FLAG = "/c";
    private static final String UNCOMPLETED_FLAG = "/u";
    private static final String MARK_MESSAGE_TOP = "Nice! I have marked this task as completed!\n";
    private static final String UNMARK_MESSAGE_TOP = "Ok! I have marked this task for you as uncompleted!\n";
    private static int taskNumber = 0;

    public MarkCommand(String arg) throws ModHappyException {
        try {
            commandName = MARK_COMMAND_WORD;
            MarkParser markParser = new MarkParser();
            HashMap<String, String> parsedArg = markParser.parseString(arg);

            switch (parsedArg.get(FLAG)) {
            case COMPLETED_FLAG:
                //mark as completed
                taskNumber = Integer.parseInt(parsedArg.get("taskNumber"));

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
