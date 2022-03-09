package seedu.splitlah.command;

import seedu.splitlah.data.Activity;
import seedu.splitlah.data.Manager;
import seedu.splitlah.data.Person;
import seedu.splitlah.data.Session;
import seedu.splitlah.exceptions.InvalidDataException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents an ActivityCreateCommand which has a run method that creates an activity.
 */
public class ActivityCreateCommand extends Command {

    public static final String COMMAND_TEXT = "activity /create";
    private static final String COMMAND_FORMAT = "There are 3 different types of formats:\n"
            + "activity /create /sid <SESSIONID> /n <ACTIVITYNAME> /p <PAYER> /i <NAME1 NAME2…> /c <OVERALLCOST> "
            + "[<OPTIONAL ARGS>]\n"
            + "activity /create /sid <SESSIONID> /n <ACTIVITYNAME> /p <PAYER> /i <NAME1 NAME2…> /c <COST1 COST2…> "
            + "[<OPTIONAL ARGS>]\n"
            + "activity /create /sid <SESSIONID> /n <ACTIVITYNAME> /p <PAYER> /c <OVERALLCOST> [<OPTIONAL ARGS>]";
    private static final double ZERO_COST_PAID = 0;
    public static final int NO_COST = 0;

    private int sessionId;
    private String activityName;
    private double cost;
    private String payer;
    private String[] involvedList;
    private double[] costList;
    private int gst;
    private int serviceCharge;

    /**
     * Constructor to create a ActivityCreateCommand object.
     * 
     * @param sessionId Ths id of the session.
     * @param activityName The name of the activity.
     * @param cost The total cost of the activity.
     * @param payer The name of the person who paid for the activity.
     * @param involvedList The names of the persons who are involved in the activity.
     * @param costList The respective costs of each person involved in the activity.
     * @param gst The gst to be included for the cost of the activity.
     * @param serviceCharge The service charge to be included for the cost of the activity.
     */
    public ActivityCreateCommand(int sessionId, String activityName, double cost, String payer, String[] involvedList,
                                 double[] costList, int gst, int serviceCharge) {
        this.sessionId = sessionId;
        this.activityName = activityName;
        this.cost = cost;
        this.payer = payer;
        this.involvedList = involvedList;
        this.costList = costList;
        this.gst = gst;
        this.serviceCharge = serviceCharge;
    }

    /**
     * Runs the command to create an activity.
     * Gets relevant parameters to create an Activity object.
     * If no errors getting parameters, an Activity object is created and added to the session.
     *
     * @param manager A Manager object that manages the TextUI and Profile object.
     */
    @Override
    public void run(Manager manager) {
        try {
            int activityId = manager.getProfile().getNewActivityId();
            updateCostAndCostList();
            Session session = manager.getProfile().getSession(sessionId);
            Person personPaid = session.getPersonByName(payer);
            ArrayList<Person> involvedPersonList = session.getPersonListByName(involvedList);
            addAllActivityCost(involvedPersonList, personPaid, cost, costList, activityId);
            Activity activity = new Activity(activityId, activityName, cost, personPaid, involvedPersonList);
            session.addActivity(activity);
        } catch (InvalidDataException e) {
            manager.getUi().printlnMessage(e.getMessage());
        }
    }

    /**
     * Adds all relevant activity costs to each involved person's list of activity costs.
     *
     * @param involvedPersonList The list of persons involved in the activity.
     * @param personPaid The person who paid for the activity.
     * @param cost The total cost of the activity.
     * @param costList The costs owed by each person involved in the activity.
     * @param activityId The id of the activity.
     * @throws InvalidDataException If the activityCost cannot be created from the given parameters.
     * @see InvalidDataException
     */
    private static void addAllActivityCost(ArrayList<Person> involvedPersonList, Person personPaid, double cost,
                                           double[] costList, int activityId) throws InvalidDataException {
        for (int i = 0; i < involvedPersonList.size(); i++) {
            Person person = involvedPersonList.get(i);
            addCostOwedAndCostPaid(personPaid, cost, costList, activityId, i, person);
        }
    }

    /**
     * Adds cost owed and cost paid to each individual's list of activity costs.
     * Checks if the current person is the person who paid for the activity.
     * If it is, the cost paid is set to the total cost of the activity.
     * Else, the cost paid is set to 0.
     *
     * @param personPaid The person who paid for the activity.
     * @param cost The total cost of the activity.
     * @param costList The costs owed by each person involved in the activity.
     * @param activityId The id of the activity.
     * @param indexOfCostOwed The index of the cost owed in the list of costs.
     * @param person The current person whose costs are added to the list of activity costs.
     * @throws InvalidDataException If the activityCost cannot be created from the given parameters.
     * @see InvalidDataException
     */
    private static void addCostOwedAndCostPaid(Person personPaid, double cost, double[] costList, int activityId,
                                               int indexOfCostOwed, Person person) throws InvalidDataException {
        if (person == personPaid) {
            person.addActivityCost(activityId, cost, costList[indexOfCostOwed]);
        } else {
            person.addActivityCost(activityId, ZERO_COST_PAID, costList[indexOfCostOwed]);
        }
    }

    /**
     * Updates cost and list of costs by adding the extra charges and
     * checks if cost list or cost was provided by the user.
     * If cost was not provided by the user, the list of costs is summed up to get the total cost of the activity.
     * Else, the total cost is distributed evenly.
     */
    private void updateCostAndCostList() {
        boolean isZeroCost = cost == NO_COST;
        if (isZeroCost) {
            updateCostListWithExtraCharges(costList, gst, serviceCharge);
            cost = getTotalCost(costList);
        } else {
            cost = updateCostWithExtraCharges(cost, gst, serviceCharge);
            int numberOfPeopleInvolved = involvedList.length;
            costList = distributeCostEvenly(cost, numberOfPeopleInvolved);
        }
    }

    /**
     * Updates cost list by including the extra charges.
     * Extra charges may include gst and service charge.
     *
     * @param costList The costs owed by each person involved in the activity.
     * @param gst The gst to be added to the costs for the activity.
     * @param serviceCharge The service charge to be added to the costs for the activity.
     */
    private static void updateCostListWithExtraCharges(double[] costList, int gst, int serviceCharge) {
        double extraCharges = getExtraCharges(gst, serviceCharge);
        for (int i = 0; i < costList.length; i++) {
            costList[i] *= extraCharges;
        }
    }

    /**
     * Returns the total cost of the activity by summing up the costs owed by each person involved in the activity.
     *
     * @param costList The costs owed by each person involved in the activity.
     * @return A double representing the total cost of the activity.
     */
    private static double getTotalCost(double[] costList) {
        double cost = 0;
        for (int i = 0; i < costList.length; i++) {
            cost += costList[i];
        }
        return cost;
    }

    /**
     * Returns the total cost by including the extra charges.
     * Extra charges may include gst and service charge.
     *
     * @param cost The total cost of the activity excluding extra charges.
     * @param gst The gst to be added to the total cost of the activity.
     * @param serviceCharge The service charge to be added to the total cost of the activity.
     * @return A double representing the total cost of the activity including extra charges.
     */
    private static double updateCostWithExtraCharges(double cost, int gst, int serviceCharge) {
        double extraCharges = getExtraCharges(gst, serviceCharge);
        return cost * extraCharges;
    }

    private static double getExtraCharges(int gst, int serviceCharge) {
        double gstMultiplier = 1 + (double)gst / 100;
        double serviceChargeMultiplier = 1 + (double)serviceCharge / 100;
        return gstMultiplier * serviceChargeMultiplier;
    }

    private static double[] distributeCostEvenly(double cost, int numberOfPeopleInvolved) {
        double dividedCost = cost / numberOfPeopleInvolved;
        double[] costList = new double[numberOfPeopleInvolved];
        Arrays.fill(costList, dividedCost);
        return costList;
    }

}
