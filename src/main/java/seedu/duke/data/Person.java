package seedu.duke.data;

import seedu.duke.exceptions.InvalidDataException;

import java.util.ArrayList;

public class Person {
    private String name;
    private double totalCost;
    private ArrayList<ActivityCost> activityCostList;

    public Person(String name) throws InvalidDataException {
        if (name == null || name.isEmpty()) {
            throw new InvalidDataException("Name cannot be empty.");
        }
        this.name = name;
    }

    public void addActivityCost(int activityId, double cost) throws InvalidDataException {
        ActivityCost activityCost = new ActivityCost(activityId, cost);
        activityCostList.add(activityCost);
        totalCost += activityCost.getCost();
    }



}
