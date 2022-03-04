package seedu.duke.data;

import seedu.duke.exceptions.InvalidDataException;

/**
 * Represents an Activity and its cost.
 * Stores activityId and cost of the activity.
 * @author Saurav
 */
public class ActivityCost {
    private int activityId;
    private double cost;

    public ActivityCost(int activityId, double cost) throws InvalidDataException {
        if (activityId < 0) {
            throw new InvalidDataException("Activity ID cannot be negative.");
        }
        if (cost < 0) {
            throw new InvalidDataException("Cost cannot be negative.");
        }
        this.activityId = activityId;
        this.cost = cost;
    }

    public int getActivityId() {
        return activityId;
    }

    public double getCost() {
        return cost;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

}
