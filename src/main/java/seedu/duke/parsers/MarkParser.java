package seedu.duke.parsers;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.ModHappyException;

public class MarkParser extends Parser{
    private static final String FLAG = "flag";
    private static final String COMPLETED_FLAG = "/c";
    private static final String UNCOMPLETED_FLAG = "/u";
    private static final String MARK_FORMAT = "";

    public MarkParser(){
        super();
        // See also https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
        this.commandFormat = MARK_FORMAT;
        groupNames.add("flag");
        groupNames.add("taskNumber");
        groupNames.add("subFlag");
        groupNames.add("moduleCode");
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        return null;
    }
}
