package seedu.splitlah.data;

import seedu.splitlah.ui.Message;

import java.io.Serializable;
import java.util.logging.Level;

/**
 * Represents the costs borne by a person for an activity.
 *
 * @author Saurav
 */
public class ActivityCost implements Serializable {

    private int activityId;
    private double costPaid;
    private double costOwed;

    /**
     * Initializes an ActivityCost object.
     * Assumption: Every field is non-negative.
     *
     * @param activityId An integer that uniquely identifies an activity.
     * @param costPaid   A double that represents the cost paid by a Person object.
     * @param costOwed   A double that represents the cost owed by a Person object.
     */
    public ActivityCost(int activityId, double costPaid, double costOwed) {
        Manager.getLogger().log(Level.FINEST, Message.LOGGER_ACTIVITYCOST_CONSTRUCT_WITH_ALL_PARAMS);
        this.activityId = activityId;
        this.costPaid = costPaid;
        this.costOwed = costOwed;
    }

    /**
     * Constructs an ActivityCost object and sets cost to 0 if only activityId is supplied.
     * Assumption: Every field is non-negative.
     *
     * @param activityId int representing the activityId.
     */
    public ActivityCost(int activityId) {
        this(activityId, 0, 0);
        Manager.getLogger().log(Level.FINEST, Message.LOGGER_ACTIVITYCOST_CONSTRUCT_WITH_ACTIVITYID);
    }

    /**
     * Default constructor, sets activityId to -1 to avoid conflict with other activityIds.
     */
    public ActivityCost() {
        this.activityId = -1;
        this.costPaid = 0;
        this.costOwed = 0;
        Manager.getLogger().log(Level.FINEST, Message.LOGGER_ACTIVITYCOST_CONSTRUCT_WITH_DEFAULT_PARAMS);
    }

    public int getActivityId() {
        return activityId;
    }

    public double getCostPaid() {
        return costPaid;
    }

    public double getCostOwed() {
        return costOwed;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public void setCostPaid(int costPaid) {
        this.costPaid = costPaid;
    }

    public void setCostOwed(int costOwed) {
        this.costOwed = costOwed;
    }
}
