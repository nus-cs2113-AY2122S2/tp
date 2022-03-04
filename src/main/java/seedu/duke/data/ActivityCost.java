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

    /**
     * Constructs an ActivityCost object.
     * Assumption: Every field is non-negative.
     * @param activityId int representing the activityId.
     * @param cost double representing activity cost.
     * @throws InvalidDataException if activityId or cost are negative.
     */
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

    /**
     * Constructs an ActivityCost object and sets cost to 0 if only activityId is supplied.
     * Assumption: Every field is non-negative.
     * @param activityId int representing the activityId.
     * @throws InvalidDataException if activityId is negative.
     */
    public ActivityCost(int activityId) throws InvalidDataException {
        this(activityId, 0);
    }

    /**
     * Default constructor, sets activityId to -1 to avoid conflict with other activityIds.
     */
    public ActivityCost() {
        this.activityId = -1;
        this.cost = 0;
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
