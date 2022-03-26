package seedu.splitlah.command;

import seedu.splitlah.data.Profile;
import seedu.splitlah.data.Manager;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.ui.Message;
import seedu.splitlah.ui.TextUI;

import java.util.logging.Level;

/**
 * Represents a command object that edits an Activity.
 *
 * @author Saurav
 */
public class ActivityEditCommand extends Command {

    public static final String COMMAND_TEXT = "activity /edit";

    private int activityId;
    private final int sessionId;
    private final String activityName;
    private final String payer;
    private final String[] involvedList;
    private final Double totalCost;
    double[] costList;
    double gst;
    double serviceCharge;

    /**
     * Constructs an ActivityEditCommand object.
     *
     * @param activityId   An integer that uniquely identifies an activity.
     * @param sessionId    An integer that uniquely identifies a session.
     * @param activityName A string that represents the Activity object's name.
     * @param payer        A String that represents the person who paid for the activity.
     * @param involvedList An array of String objects representing the participants in the activity.
     * @param totalCost    A Double representing the total cost of the activity.
     */
    public ActivityEditCommand(int activityId, int sessionId, String activityName, String payer, String[] involvedList,
        Double totalCost, double[] costList, double gst, double serviceCharge) {
        assert sessionId != -1 : Message.ASSERT_ACTIVITYEDIT_SESSIONID_MISSING;
        assert activityId != -1 : Message.ASSERT_ACTIVITYEDIT_ACTIVITYID_MISSING;
        this.activityId = activityId;
        this.sessionId = sessionId;
        this.activityName = activityName;
        this.payer = payer;
        this.involvedList = involvedList;
        this.totalCost = totalCost;
        this.costList = costList;
        this.gst = gst;
        this.serviceCharge = serviceCharge;
    }

    /**
     * Runs the command with the session identifier and activity identifier as provided by the user input.
     *
     * @param manager A Manager object that manages the TextUI, Profile and Storage objects.
     */
    @Override
    public void run(Manager manager) {
        assert manager != null : Message.ASSERT_ACTIVITYEDIT_MANAGER_DOES_NOT_EXIST;
        try {
            manager.getProfile().getSession(sessionId).removeActivity(activityId);
            ActivityCreateCommand activityCreateCommand = new ActivityCreateCommand(activityId, sessionId,
                    activityName, totalCost, payer, involvedList, costList, gst, serviceCharge);
            activityCreateCommand.run(manager);
        } catch (Exception e) {
            manager.getUi().printlnMessage(e.getMessage());
            Manager.getLogger().log(Level.FINEST, Message.LOGGER_ACTIVITYEDIT_FAILED_EDITING_ACTIVITY
                    + "\n" + e.getMessage());
        }
    }
}


