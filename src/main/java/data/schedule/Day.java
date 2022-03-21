package data.schedule;

import commands.InvalidCommandException;
import data.plans.Plan;

import static data.schedule.InvalidScheduleException.INVALID_DAY;

public class Day {
    private int dayNumber;
    private Plan planForThisDay;


    public Day(int dayNumber, Plan planForThisDay) {
        this.dayNumber = dayNumber;
        this.planForThisDay = planForThisDay;
    }

    public void setPlanForThisDay() {
        this.planForThisDay = planForThisDay;
    }

    public void setDayNumber() {
        this.dayNumber = dayNumber;
    }

    public Plan getPlanForThisDay() {
        return planForThisDay;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setNewPlanForThisDay(Day day, Plan newPlan) {
        day.planForThisDay = newPlan;
    }

    public String toString() {
        int dayNumber = getDayNumber();
        String day = "";
        try {
            switch (dayNumber) {
            case 1:
                day = "Monday";
                break;
            case 2:
                day = "Tuesday";
                break;
            case 3:
                day = "Wednesday";
                break;
            case 4:
                day = "Thursday";
                break;
            case 5:
                day = "Friday";
                break;
            case 6:
                day = "Saturday";
                break;
            case 7:
                day = "Sunday";
                break;
            default:
                String className = this.getClass().getSimpleName();
                throw new InvalidScheduleException(className, INVALID_DAY);
            } 
        } catch (InvalidScheduleException e) {
            System.out.println(e.getMessage());
        }
        String displayString = day + " -- " + getPlanForThisDay().getPlanName();
        return displayString;
    }

}
