package seedu.duke.data;

import seedu.duke.exceptions.InvalidDataException;

import java.util.ArrayList;

/**
 * Represents a person.
 * Stores its name, total cost and a list of activityCosts.
 * @author Saurav
 */
public class Person {
    private final String name;
    private double totalCost;
    private ArrayList<ActivityCost> activityCostList;

    public Person(String name) throws InvalidDataException {
        if (name == null || name.isEmpty()) {
            throw new InvalidDataException("Name cannot be empty.");
        }
        this.name = name;
    }

    /**
     * Constructs an activityCost and adds it to the list of activityCosts.
     * @param activityId the activityId.
     * @param cost the cost of the activity.
     * @throws InvalidDataException if the activityCost cannot be created from the given parameters.
     */
    public void addActivityCost(int activityId, double cost) throws InvalidDataException {
        ActivityCost activityCost = new ActivityCost(activityId, cost);
        activityCostList.add(activityCost);
        totalCost += activityCost.getCost();
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getName() {
        return name;
    }

    public ArrayList<ActivityCost> getActivityCostList() {
        return activityCostList;
    }



}
