package data.plans;

import data.workouts.Workout;

import java.util.ArrayList;

public class Plan {
    private String planName;
    private ArrayList<Workout> workoutsInPlanList = new ArrayList<>();

    public Plan(String planName, ArrayList<Workout> workoutsInPlanList) {
        this.planName = planName;
        this.workoutsInPlanList = workoutsInPlanList;
    }

    public String getPlanName() {
        return planName;
    }

    public ArrayList<Workout> getWorkoutsInPlanList() {
        return workoutsInPlanList;
    }

    public void setPlanName() {
        this.planName = planName;
    }

    public void setWorkoutsInPlanList() {
        this.workoutsInPlanList = workoutsInPlanList;
    }

    public String toString() {
        String displayString = getPlanName();
        return displayString;
    }
}
