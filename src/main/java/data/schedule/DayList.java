package data.schedule;

import data.plans.Plan;
import data.plans.PlanList;

public class DayList {
    private PlanList planList;
    private String[] scheduleList = new String[7]; //store all plan name for the day
    private String[] schedulePrintList = new String[7]; //store all plan name for the day to be printed
    private Day[] dayList = new Day[7]; //store all created day object

    public DayList(PlanList planList) {
        this.planList = planList;
    }

    public String[] getScheduleList() {
        return scheduleList;
    }

    public String[] getSchedulePrintList() {
        return schedulePrintList;
    }

    public Day updateDay(String userArgument) throws ArrayIndexOutOfBoundsException, InvalidScheduleException {
        String className = this.getClass().getSimpleName();
        String[] userArgumentArray = userArgument.split(" ", -1);
        if (userArgumentArray.length > 2) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int dayNumber = Integer.parseInt(userArgumentArray[0]);
        int planNumber = Integer.parseInt(userArgumentArray[1]);
        if (!isDayValid(dayNumber)) {
            throw new InvalidScheduleException(className, InvalidScheduleException.DAY_NUMBER_OUT_OF_RANGE);
        }
        if (!isPlanValid(planNumber)) {
            throw new InvalidScheduleException(className, InvalidScheduleException.INVALID_PLAN);
        }
        String planToAddKey = planList.getPlansDisplayList().get(planNumber - 1); //plan name
        Plan planToAdd = planList.getPlanFromKey(planToAddKey); //plan hash value
        Day newDay;
        if (scheduleList[dayNumber - 1] == null) {
            newDay = new Day(dayNumber, planToAdd);
            dayList[dayNumber - 1] = newDay;
        } else {
            newDay = dayList[dayNumber - 1];
            newDay.setNewPlanForThisDay(newDay, planToAdd);
        }
        scheduleList[dayNumber - 1] = planToAdd.getPlanName();
        return newDay;
    }

    public Boolean isDayValid(int dayNumber) {
        if (dayNumber < 1 || dayNumber > 7) {
            return false;
        }
        return true;
    }

    public Boolean isPlanValid(int planNumber) {
        if (planNumber < 1 || planNumber > planList.getPlansDisplayList().size()) {
            return false;
        }
        return true;
    }
}
