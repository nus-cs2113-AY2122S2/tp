package seedu.splitlah.command;

import seedu.splitlah.data.Manager;
import seedu.splitlah.data.Session;
import seedu.splitlah.parser.ParserUtils;
import seedu.splitlah.ui.Message;
import seedu.splitlah.ui.TableFormatter;

import java.util.ArrayList;

/**
 * Represents a command that displays the details of each Session object within a Profile object.
 *
 * @author Roy
 */
public class SessionListCommand extends Command {

    private static final String SESSION_LIST_HEADER = "List of Sessions";

    /**
     * Runs the command to list all existing sessions managed by the Profile Object.
     *
     * @param manager A Manager object that manages the TextUI, Profile and Storage object.
     */
    @Override
    public void run(Manager manager) {
        ArrayList<Session> sessionsToBePrinted = manager.getProfile().getSessionList();
        if (sessionsToBePrinted.isEmpty()) {
            manager.getUi().printlnMessage(Message.ERROR_PROFILE_SESSION_LIST_EMPTY);
            return;
        }
        TableFormatter tableFormatter =
                new TableFormatter("#","Name", "Date","# of Participants","# of Activities");
        tableFormatter.addTableName(SESSION_LIST_HEADER);

        for (Session session : sessionsToBePrinted) {
            String rowId = Integer.toString(session.getSessionId());
            String rowName = session.getSessionName();
            String rowDate = session.getDateCreated().format(ParserUtils.DATE_FORMAT);
            String rowNumParticipants = Integer.toString(session.getPersonList().size());
            String rowNumActivities = Integer.toString(session.getActivityList().size());
            tableFormatter.addRow(rowId, rowName, rowDate, rowNumParticipants, rowNumActivities);
        }
        manager.getUi().printlnMessage(tableFormatter.toString());
    }
}
