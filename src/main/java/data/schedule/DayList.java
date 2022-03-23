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

    /**
     * Add/Update a plan to a particular day of the week. User specify the plan (plan number) that they
     * want to add to a particular day (day number). A day object will be created if the day object does not exist,
     * otherwise the day object will be updated to reflect the latest changes.
     *
     * @param userArgument Day number and plan number to be added into the schedule.
     * @return newDay Day object created with the day number and plan name specified by user.
     * @throws ArrayIndexOutOfBoundsException If userArgument contains insufficient arguments and parsing fails.
     * @throws InvalidScheduleException If the schedule parameters specified in userArgument is invalid.
     */
    public Day updateDay(String userArgument) throws ArrayIndexOutOfBoundsException, InvalidScheduleException {
        String className = this.getClass().getSimpleName();
        String[] userArgumentArray = userArgument.split(" ", -1);
        if (userArgumentArray.length > 2) {
            logger.log(Level.WARNING, "User entered more parameters than expected.");
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
        assert (isPlanValid(planNumber) && isDayValid(dayNumber));
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

    /**
     * Checks if the provided day number is valid. A day number is valid if it falls
     * between 1 and 7. Day number 1 refers to Monday, Day number 2 refers to Tuesday
     * and so on till Day number 7 referring to Sunday, the last day of the week.
     *
     * @param dayNumber The day number entered by user to check for validity.
     * @return True if the day number falls between 1 to 7.
     *         Otherwise, returns false.
     */
    public Boolean isDayValid(int dayNumber) {
        if (dayNumber < 1 || dayNumber > NUMBER_OF_SCHEDULE_DAYS) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the provided plan number is valid. A plan number is valid if it falls between
     * the index range of the plan list.
     *
     * @param planNumber The plan number entered by user to check for validity.
     * @return True if the plan number specified exist in the plans list.
     *      *         Otherwise, returns false.
     */
    public Boolean isPlanValid(int planNumber) {
        if (planNumber < 1 || planNumber > planList.getPlansDisplayList().size()) {
            return false;
        }
        return true;
    }

    /**
     * Print out all the plans scheduled for the week. One week consists of 7 days.
     * If no plan is scheduled for any of the day, it is considered as rest day.
     */
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

    /**
     * Pad the plan name with empty spaces at the front of the plan name as well as the back of the plan name.
     *
     * @param planForDay Plan name scheduled for a particular day.
     * @return planForDayWith Plan name scheduled for a particular day with added empty spaces at the front and back.
     */
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

    /**
     * Clear the plan for a specific day. The plan scheduled for the day will be cleared/reset.
     * When a plan is clear/reset, the day will set to be a rest day.
     *
     * @param userArgument Day number to be reset/cleared.
     * @throws InvalidScheduleException If the day number specified in userArgument is invalid.
     */
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

    /**
     * Clear all the plans schdeule for the week. Reset the schedule list to default.
     * All plans for the week will be set as rest day.
     */
    public void clearAllSchedule() {
        for (int i = 0; i < NUMBER_OF_SCHEDULE_DAYS; i += 1) {
            scheduleList[i] = null;
            dayList[i] = null;
            assert (scheduleList[i] == null);
            assert (dayList[i] == null);
        }
    }

    /**
     * Covert the day number to its english meaning. For example,
     * day number 1 will be converted to Monday,
     * day number 2 will be converted to Tuesday,
     * and so on till day number 7 which will be converted to Sunday.
     *
     * @param dayNumber Day number to be converted to its String equivalent.
     * @return day English meaning of the day number specified.
     */
    public String covertDayNumberToDay(int dayNumber) {
        String day = "";
        assert (dayNumber > 0 && dayNumber <= NUMBER_OF_SCHEDULE_DAYS);
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
