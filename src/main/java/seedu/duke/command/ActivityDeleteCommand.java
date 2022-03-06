package seedu.duke.command;

import seedu.duke.data.Manager;

public class ActivityDeleteCommand extends Command {

    public static final String COMMAND_TEXT = "activity /delete";
    private static final String COMMAND_FORMAT = "activity /delete /sid <SESSIONID> /aid <ACTIVITYID>";

    private int sessionId;
    private int activityId;

    @Override
    public void run(Manager manager) {

    }
}
