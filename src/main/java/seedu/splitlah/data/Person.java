package seedu.splitlah.data;

import seedu.splitlah.exceptions.InvalidDataException;

import java.util.ArrayList;

/**
 * Represents a person.
 * Stores its name, total cost and a list of activityCosts.
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
        this.name = name;
    }

    /**
     * Constructs an activityCost object and adds it to the list of activityCosts.
     *
     * @param activityId the activityId.
     * @param costPaid the cost of the activity paid by the payer.
     * @param costOwed the money owed by an individual.
     * @throws InvalidDataException if the activityCost cannot be created from the given parameters.
     */
    public void addActivityCost(int activityId, double costPaid, double costOwed) throws InvalidDataException {
        ActivityCost activityCost = new ActivityCost(activityId, costPaid, costOwed);
        activityCostList.add(activityCost);
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

    public String getName() {
        return name;
    }

    public ArrayList<ActivityCost> getActivityCostList() {
        return activityCostList;
    }
}
