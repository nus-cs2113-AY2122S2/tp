package seedu.splitlah.data;

import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.ui.Message;

import java.util.ArrayList;

/**
 * Represents a person participating in an activity during a group outing session.
 * 
 * @author Saurav
 */
public class Person {
    
    private final String name;
    private ArrayList<ActivityCost> activityCostList;

    /**
     * Constructs a Person object.
     *
     * @param name Name of the Person.
     */
    public Person(String name) {
        this.activityCostList = new ArrayList<>();
        this.name = name;
    }

    /**
     * Constructs an ActivityCost object and adds it to the list of ActivityCosts.
     *
     * @param activityId An integer that uniquely identifies an activity.
     * @param costPaid   A double that represents the cost paid by a Person object.
     * @param costOwed   A double that represents the cost owed by a Person object.
     * @throws InvalidDataException if the activityCost cannot be created from the given parameters.
     */
    public void addActivityCost(int activityId, double costPaid, double costOwed) throws InvalidDataException {
        ActivityCost activityCost = new ActivityCost(activityId, costPaid, costOwed);
        activityCostList.add(activityCost);
    }

    /**
     * Removes an ActivityCost object from the list of ActivityCosts.
     *
     * @param activityId the activityId of the ActivityCost to be removed.
     * @throws InvalidDataException if the activityId is not found.
     */
    public void removeActivityCost(int activityId) throws InvalidDataException {
        if (activityCostList.isEmpty()) {
            throw new InvalidDataException(Message.ERROR_PERSON_NO_ACTIVITIES);
        }
        boolean removed =
                activityCostList.removeIf(activityCost -> activityCost.getActivityId() == activityId);
        if (!removed) {
            throw new InvalidDataException(Message.ERROR_PERSON_ACTIVITY_NOT_FOUND);
        }
    }

    public double getTotalCostPaid() {
        double totalCostPaid = 0;
        for (ActivityCost i : activityCostList) {
            totalCostPaid += i.getCostPaid();
        }
        return totalCostPaid;
    }

    public double getTotalCostOwed() {
        double totalCostOwed = 0;
        for (ActivityCost i : activityCostList) {
            totalCostOwed += i.getCostOwed();
        }
        return totalCostOwed;
    }

    /**
     * Calculates the amount of money other Persons owe to this Person.
     * In other words, how much money this Person must receive in order to break even.
     *
     * @return Sum of all costPaids - sum of all costOweds in the list of ActivityCosts for this Person.
     */
    public double getTotalCost() {
        double totalCostPaid = 0;
        double totalCostOwed = 0;
        for (ActivityCost i : activityCostList) {
            totalCostPaid += i.getCostPaid();
            totalCostOwed += i.getCostOwed();
        }
        return totalCostPaid - totalCostOwed;
    }

    /**
     * Returns the cost owed by this Person object for an Activity.
     *
     * @param activityId int representing activityId of the Activity.
     * @return double representing cost owed by the Person for this Activity.
     * @throws InvalidDataException If this Person is not participating in any Activities.
     *                              If the activityId is not found.
     */
    public double getActivityCostOwed(int activityId) throws InvalidDataException {
        if (activityCostList.isEmpty()) {
            throw new InvalidDataException(Message.ERROR_PERSON_NO_ACTIVITIES);
        }
        for (ActivityCost activityCost : activityCostList) {
            if (activityCost.getActivityId() == activityId) {
                return activityCost.getCostOwed();
            }
        }
        throw new InvalidDataException(Message.ERROR_PERSON_ACTIVITY_NOT_FOUND);
    }

    public String getName() {
        return name;
    }

    public ArrayList<ActivityCost> getActivityCostList() {
        return activityCostList;
    }
}
