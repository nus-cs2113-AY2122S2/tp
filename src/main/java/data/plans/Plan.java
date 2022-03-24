package data.plans;

import data.workouts.Workout;

import java.util.ArrayList;

/**
 * This class represents a Plan, which groups a number of workout(s) together and is given a plan name.
 */
public class Plan {
    private String planName;
    private ArrayList<Workout> listOfWorkouts = new ArrayList<>();

    public Plan(String planName, ArrayList<Workout> listOfWorkouts) {
        this.planName = planName;
        this.listOfWorkouts = listOfWorkouts;
    }

    public String getPlanName() {
        return planName;
    }

    public ArrayList<Workout> getWorkoutsInPlanList() {
        return listOfWorkouts;
    }

    public void setPlanName() {
        this.planName = planName;
    }

    public void setWorkoutsInPlanList() {
        this.listOfWorkouts = listOfWorkouts;
    }

    public String toString() {
        String displayString = getPlanName();
        return displayString;
    }
}
