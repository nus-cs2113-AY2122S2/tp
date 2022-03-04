package seedu.duke.command;public class SessionDeleteCommand {
package seedu.duke.command;

import seedu.duke.data.Profile;
import seedu.duke.ui.TextUI;
public class SessionDeleteCommand extends Command {

    public static final String COMMAND_TEXT = "session /delete";

    private static final String COMMAND_FORMAT = "session /delete /sid <SESSIONID>";

    private int sessionId;
    public SessionDeleteCommand(int sessionId) {
        this.sessionId = sessionId;
    }
    public int getSessionId() {
        return sessionId;
    }
    @Override
    public void run(TextUI ui, Profile profile) {

    }
}
