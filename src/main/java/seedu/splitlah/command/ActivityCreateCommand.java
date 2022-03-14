package seedu.splitlah.command;

import seedu.splitlah.data.Activity;
import seedu.splitlah.data.Manager;
import seedu.splitlah.data.Person;
import seedu.splitlah.data.Session;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.ui.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a command that creates an Activity object from user input and stores it in the Session object.
 */
public class ActivityCreateCommand extends Command {

    public static final String COMMAND_TEXT = "activity /create";

    private static final String COMMAND_FORMAT = "Syntax:\n\t";

    public static final String COMMAND_FORMAT_FIRST =
            "activity /create /sid [SESSION_ID] /n [ACTIVITY_NAME] /p [PAYER] /i [NAME1 NAME2…] "
                    + "/c <TOTAL_COST> [</gst GST_PERCENT /sc SERVICE_CHARGE>]";

    public static final String COMMAND_FORMAT_SECOND =
            "activity /create /sid [SESSION_ID] /n [ACTIVITY_NAME] /p [PAYER] /i [NAME1 NAME2…] "
                    + "/cl [COST1 COST2…] [</gst GST_PERCENT /sc SERVICE_CHARGE>]";

    private static final String COMMAND_SUCCESS = "The activity was created successfully with activity id of: ";

    private int sessionId;
    private String activityName;
    private double totalCost;
    private String payer;
    private String[] involvedList;
    private double[] costList;
    private int gst;
    private int serviceCharge;

    private static final double ZERO_COST_PAID = 0;
    private static final int NO_COST = 0;

    /**
     * Constructor to create a ActivityCreateCommand object.
     *
     * @param sessionId     An integer that uniquely identifies a session.
     * @param activityName  A String object that represents the Activity object's name.
     * @param totalCost     A double that represents total cost of the activity.
     * @param payer         The name of the person who paid for the activity.
     * @param involvedList  The names of the persons who are involved in the activity.
     * @param costList      The respective costs of each person involved in the activity.
     * @param gst           The gst to be included for the cost of the activity.
     * @param serviceCharge The service charge to be included for the cost of the activity.
     */
    public ActivityCreateCommand(int sessionId, String activityName, double totalCost, String payer,
                                 String[] involvedList, double[] costList, int gst, int serviceCharge) {
        this.sessionId = sessionId;
        this.activityName = activityName;
        this.totalCost = totalCost;
        this.payer = payer;
        this.involvedList = involvedList;
        this.costList = costList;
        this.gst = gst;
        this.serviceCharge = serviceCharge;
    }

    /**
     * Prepares user arguments for activity create command.
     *
     * @param commandArgs The user's arguments.
     * @return An ActivityCreateCommand object if necessary parameters were found in user arguments,
     *         an InvalidCommand object otherwise.
     */
    public static Command prepare(String commandArgs) {
        boolean isMissingCost = false;
        boolean isMissingCostList = false;
        double totalCost = 0;
        double[] costList = null;

        try {
            totalCost = Parser.parseTotalCost(commandArgs);
        } catch (InvalidFormatException e) {
            isMissingCost = true;
        }

        try {
            costList = Parser.parseCostList(commandArgs);
        } catch (InvalidFormatException e) {
            isMissingCostList = true;
        }

        boolean hasMissingCostAndMissingCostList = isMissingCostList && isMissingCost;
        if (hasMissingCostAndMissingCostList) {
            return new InvalidCommand(Message.ERROR_ACTIVITYCREATE_MISSING_COST_AND_COST_LIST
                    + "\n" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST + "\n\t" + COMMAND_FORMAT_SECOND);
        }

        boolean hasBothCostAndCostList = !isMissingCostList && !isMissingCost;
        if (hasBothCostAndCostList) {
            return new InvalidCommand(Message.ERROR_ACTIVITYCREATE_HAS_BOTH_COST_AND_COST_LIST
                    + "\n" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST + "\n\t" + COMMAND_FORMAT_SECOND);
        }

        try {
            int sessionId = Parser.parseSessionId(commandArgs);
            String activityName = Parser.parseName(commandArgs);
            String payer = Parser.parsePayer(commandArgs);
            String[] involvedList = Parser.parseInvolved(commandArgs);
            int gst = Parser.parseGst(commandArgs);
            int serviceCharge = Parser.parseServiceCharge(commandArgs);
            boolean hasDifferentLength = false;
            if (isMissingCost) {
                hasDifferentLength = involvedList.length != costList.length;
            }
            if (hasDifferentLength) {
                return new InvalidCommand(Message.ERROR_ACTIVITYCREATE_INVOLVED_AND_COST_DIFFERENT_LENGTH
                        + "\n" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST + "\n\t" + COMMAND_FORMAT_SECOND);
            }
            return new ActivityCreateCommand(sessionId, activityName, totalCost, payer, involvedList, costList, gst,
                    serviceCharge);
        } catch (InvalidFormatException e) {
            return new InvalidCommand(e.getMessage() + "\n\t" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST + "\n"
                    + COMMAND_FORMAT_SECOND);
        }
    }

    /**
     * Checks if String object array of names has duplicated names.
     *
     * @return true if it contains duplicates,
     *         false otherwise.
     */
    private boolean hasNameDuplicates() {
        Set<String> nameSet = new HashSet<>();
        for (String name : involvedList) {
            String nameToBeAdded = name.toLowerCase();
            if (!nameSet.add(nameToBeAdded)) {
                return true;
            }
        }
        assert nameSet.size() == involvedList.length :
                Message.ASSERT_ACTIVITYCREATE_NAME_DUPLICATE_EXISTS_BUT_NOT_DETECTED;
        return false;
    }

    /**
     * Adds all relevant activity costs to each involved person's list of activity costs.
     *
     * @param involvedPersonList The list of persons involved in the activity.
     * @param personPaid         The person who paid for the activity.
     * @param totalCost          The total cost of the activity.
     * @param costList           The costs owed by each person involved in the activity.
     * @param activityId         The id of the activity.
     * @throws InvalidDataException If the activityCost cannot be created from the given parameters.
     * @see InvalidDataException
     */
    private static void addAllActivityCost(ArrayList<Person> involvedPersonList, Person personPaid, double totalCost,
                                           double[] costList, int activityId) throws InvalidDataException {
        for (int i = 0; i < involvedPersonList.size(); i++) {
            Person person = involvedPersonList.get(i);
            addCostOwedAndCostPaid(personPaid, totalCost, costList, activityId, i, person);
        }
    }

    /**
     * Adds cost owed and cost paid to each individual's list of activity costs.
     * Checks if the current person is the person who paid for the activity.
     * If it is, the cost paid is set to the total cost of the activity.
     * Else, the cost paid is set to 0.
     *
     * @param personPaid      The person who paid for the activity.
     * @param totalCost       The total cost of the activity.
     * @param costList        The costs owed by each person involved in the activity.
     * @param activityId      The id of the activity.
     * @param indexOfCostOwed The index of the cost owed in the list of costs.
     * @param person          The current person whose costs are added to the list of activity costs.
     * @throws InvalidDataException If the activityCost cannot be created from the given parameters.
     * @see InvalidDataException
     */
    private static void addCostOwedAndCostPaid(Person personPaid, double totalCost, double[] costList, int activityId,
                                               int indexOfCostOwed, Person person) throws InvalidDataException {
        if (person == personPaid) {
            person.addActivityCost(activityId, totalCost, costList[indexOfCostOwed]);
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
        boolean isZeroCost = totalCost == NO_COST;
        if (isZeroCost) {
            updateCostListWithExtraCharges(costList, gst, serviceCharge);
            totalCost = calculateTotalCost(costList);
        } else {
            totalCost = updateCostWithExtraCharges(totalCost, gst, serviceCharge);
            int numberOfPeopleInvolved = involvedList.length;
            costList = distributeCostEvenly(totalCost, numberOfPeopleInvolved);
        }
    }

    /**
     * Updates cost list by including the extra charges.
     * Extra charges may include gst and service charge.
     *
     * @param costList      The costs owed by each person involved in the activity.
     * @param gst           The gst to be added to the costs for the activity.
     * @param serviceCharge The service charge to be added to the costs for the activity.
     */
    private static void updateCostListWithExtraCharges(double[] costList, int gst, int serviceCharge) {
        double extraCharges = getExtraCharges(gst, serviceCharge);
        for (int i = 0; i < costList.length; i++) {
            costList[i] *= extraCharges;
        }
    }

    /**
     * Returns a double representing the total cost of the activity
     * by summing up the costs owed by each person involved in the activity.
     *
     * @param costList The costs owed by each person involved in the activity.
     * @return A double representing the total cost of the activity.
     */
    private static double calculateTotalCost(double[] costList) {
        double totalCost = 0;
        for (int i = 0; i < costList.length; i++) {
            totalCost += costList[i];
        }
        return totalCost;
    }

    /**
     * Returns a double representing the total cost by including the extra charges.
     * Extra charges may include gst and service charge.
     * Assumption: gst and service charge are non-negative integers.
     *
     * @param totalCost     The total cost of the activity excluding extra charges.
     * @param gst           The gst to be included in the total cost of the activity.
     * @param serviceCharge The service charge to be included in the total cost of the activity.
     * @return A double representing the total cost of the activity.
     */
    private static double updateCostWithExtraCharges(double totalCost, int gst, int serviceCharge) {
        double extraCharges = getExtraCharges(gst, serviceCharge);
        return totalCost * extraCharges;
    }

    /**
     * Returns a double representing the extra charges that is to be included in costs of the activity.
     *
     * @param gst           The gst to be included in the costs of the activity.
     * @param serviceCharge The service charge to be included in the costs of the activity.
     * @return A double representing the extra charges.
     */
    private static double getExtraCharges(int gst, int serviceCharge) {
        double gstMultiplier = 1 + (double) gst / 100;
        double serviceChargeMultiplier = 1 + (double) serviceCharge / 100;
        return gstMultiplier * serviceChargeMultiplier;
    }

    /**
     * Returns an array of doubles of the costs that has been distributed evenly
     * among the persons involved in the activity.
     * Divides the total cost by the number of people involved in the activity.
     *
     * @param totalCost              The total cost of the activity.
     * @param numberOfPeopleInvolved The number of people involved in the activity.
     * @return An array of doubles representing the costs of each person involved in the activity.
     */
    private static double[] distributeCostEvenly(double totalCost, int numberOfPeopleInvolved) {
        double dividedCost = totalCost / numberOfPeopleInvolved;
        double[] costList = new double[numberOfPeopleInvolved];
        Arrays.fill(costList, dividedCost);
        return costList;
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
        boolean hasDuplicates = hasNameDuplicates();
        if (hasDuplicates) {
            manager.getUi().printlnMessage(Message.ERROR_ACTIVITYCREATE_DUPLICATE_NAME);
            return;
        }
        try {
            int activityId = manager.getProfile().getNewActivityId();
            updateCostAndCostList();
            Session session = manager.getProfile().getSession(sessionId);
            Person personPaid = session.getPersonByName(payer);
            ArrayList<Person> involvedPersonList = session.getPersonListByName(involvedList);
            addAllActivityCost(involvedPersonList, personPaid, totalCost, costList, activityId);
            Activity activity = new Activity(activityId, activityName, totalCost, personPaid, involvedPersonList);
            session.addActivity(activity);
            manager.getUi().printlnMessage(COMMAND_SUCCESS + activityId);
        } catch (InvalidDataException e) {
            manager.getUi().printlnMessage(e.getMessage());
        }
    }

}
