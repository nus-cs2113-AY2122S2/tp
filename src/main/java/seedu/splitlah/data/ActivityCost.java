package seedu.splitlah.data;

import java.util.Objects;

/**
 * Represents an Activity and its cost.
 * Stores activityId and cost of the activity.
 *
 * @author Saurav
 */
public class ActivityCost {
    private int activityId;
    private double costPaid;
    private double costOwed;

    /**
     * Constructs an ActivityCost object.
     * Assumption: Every field is non-negative.
     *
     * @param activityId int representing the activityId.
     * @param costPaid double representing cost paid.
     * @param costOwed double representing cost owed.
     */
    public ActivityCost(int activityId, double costPaid, double costOwed) {
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
    }

    /**
     * Default constructor, sets activityId to -1 to avoid conflict with other activityIds.
     */
    public ActivityCost() {
        this.activityId = -1;
        this.costPaid = 0;
        this.costOwed = 0;
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

    /**
     * Compares the ActivityCost object to the given int.
     * Returns true if the supplied int is equal to this ActivityCost object's activityId.
     *
     * @param activityId int representing the activityId.
     * @return true if this ActivityCost's activityId is equal to the supplied activityId.
     */
    public boolean compareActivityId(int activityId) {
        return this.activityId == activityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityCost that = (ActivityCost) o;
        return activityId == that.activityId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityId);
    }
}
