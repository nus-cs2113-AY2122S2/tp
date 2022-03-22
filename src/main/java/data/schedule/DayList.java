package data.schedule;

import data.plans.Plan;
import data.plans.PlanList;
import werkit.Parser;

import java.util.logging.Level;
import java.util.logging.Logger;

import static data.schedule.InvalidScheduleException.INVALID_DAY;

public class DayList {
    private static final int FIXED_LENGTH = 30;
    private static final int NUMBER_OF_SCHEDULE_DAYS = 7;

    private PlanList planList;
    private String[] scheduleList = new String[NUMBER_OF_SCHEDULE_DAYS]; 
    private String[] schedulePrintList = new String[NUMBER_OF_SCHEDULE_DAYS];
    private Day[] dayList = new Day[NUMBER_OF_SCHEDULE_DAYS]; //store all created day object

    private static Logger logger = Logger.getLogger(Parser.class.getName());

    public DayList(PlanList planList) {
        this.planList = planList;
    }

    public String[] getScheduleList() {
        return scheduleList;
    }

    public String[] getSchedulePrintList() {
        return schedulePrintList;
    }

    public Day[] getDayList() {
        return this.dayList;
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
            logger.log(Level.WARNING, "User entered an invalid day number.");
            throw new InvalidScheduleException(className, InvalidScheduleException.DAY_NUMBER_OUT_OF_RANGE);
        }
        if (!isPlanValid(planNumber)) {
            logger.log(Level.WARNING, "User entered an invalid plan number.");
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
        if (dayNumber < 1 || dayNumber > NUMBER_OF_SCHEDULE_DAYS) {
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

    public void printSchedule() {
        for (int i = 0; i < NUMBER_OF_SCHEDULE_DAYS; i += 1) {
            if (scheduleList[i] == null) {
                schedulePrintList[i] = "rest day";
            } else {
                schedulePrintList[i] = scheduleList[i];
            }
            schedulePrintList[i] = padWithSpaces(schedulePrintList[i]);
        }

        String header = String.format("%8s %7s %20s", "Day", "|", "Plan Name");
        String info = "";
        for (int i = 1; i <= NUMBER_OF_SCHEDULE_DAYS; i++) {
            info += String.format("%12s %3s %35s", covertDayNumberToDay(i), "|", schedulePrintList[i - 1]) + "\n";
        }

        System.out.println("\n                         WORKOUT SCHEDULE\n"
                + "----------------------------------------------------------------------\n"
                + header + "\n" +  "----------------------------------------------------------------------\n"
                + info
        );
    }

    public String padWithSpaces(String planForDay) {
        String prePadded = "";
        String postPadded = "";
        int numberOfSpacesToBePadFront = (FIXED_LENGTH - planForDay.length()) / 2;
        int numberOfSpacesToBePadBack = FIXED_LENGTH - prePadded.length() - planForDay.length();
        for (int i = 0; i < numberOfSpacesToBePadFront; i++) {
            prePadded += " ";
        }
        for (int i = 0; i < numberOfSpacesToBePadBack; i++) {
            postPadded += " ";
        }

        String planForDayWithPadding = prePadded + planForDay + postPadded;
        return planForDayWithPadding;
    }

    public void clearDayPlan(String userArgument) throws InvalidScheduleException {
        int dayNumber = Integer.parseInt(userArgument);
        String className = this.getClass().getSimpleName();
        if (!isDayValid(dayNumber)) {
            logger.log(Level.WARNING, "User entered an invalid day number.");
            throw new InvalidScheduleException(className, InvalidScheduleException.DAY_NUMBER_OUT_OF_RANGE);
        }
        scheduleList[dayNumber - 1] = null;
        dayList[dayNumber - 1] = null;
        System.out.println("Plan had been cleared for " + covertDayNumberToDay(dayNumber) + ".");
    }

    public void clearAllSchedule() {
        for (int i = 0; i < NUMBER_OF_SCHEDULE_DAYS; i += 1) {
            scheduleList[i] = null;
            dayList[i] = null;
        }
    }

    public String covertDayNumberToDay(int dayNumber) {
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
                logger.log(Level.WARNING, "User entered an invalid day.");
                throw new InvalidScheduleException(className, INVALID_DAY);
            }
        } catch (InvalidScheduleException e) {
            System.out.println(e.getMessage());
        }
        return day;
    }
}
