package seedu.splitlah.command;

import seedu.splitlah.data.Activity;
import seedu.splitlah.data.Manager;
import seedu.splitlah.data.Person;
import seedu.splitlah.data.Session;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.parser.ParserUtils;
import seedu.splitlah.ui.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

/**
 * Represents a command that creates an Activity object from user input and stores it in the Session object.
 *
 * @author Ivan
 */
public class ActivityCreateCommand extends Command {

    public static final String COMMAND_TEXT = "activity /create";

    private static final String COMMAND_FORMAT = "Syntax:\n\t";

    public static final String COMMAND_FORMAT_FIRST =
            "activity /create /sid [SESSION_ID] /n [ACTIVITY_NAME] /p [PAYER] /i [NAME1 NAME2…] "
                    + "/co <TOTAL_COST> [</gst GST_PERCENT /sc SERVICE_CHARGE>]";

    public static final String COMMAND_FORMAT_SECOND =
            "activity /create /sid [SESSION_ID] /n [ACTIVITY_NAME] /p [PAYER] /i [NAME1 NAME2…] "
                    + "/cl [COST1 COST2…] [</gst GST_PERCENT /sc SERVICE_CHARGE>]";

    private static final String COMMAND_SUCCESS = "The activity was created successfully.\n";
    
    public static final String[] COMMAND_DELIMITERS = { 
        ParserUtils.SESSION_ID_DELIMITER, 
        ParserUtils.NAME_DELIMITER, 
        ParserUtils.PAYER_DELIMITER, 
        ParserUtils.INVOLVED_DELIMITER, 
        ParserUtils.TOTAL_COST_DELIMITER, 
        ParserUtils.COST_LIST_DELIMITER,
        ParserUtils.GST_DELIMITER,
        ParserUtils.SERVICE_CHARGE_DELIMITER 
    };

    private int sessionId;
    private String activityName;
    private double totalCost;
    private String payer;
    private String[] involvedList;
    private double[] costList;
    private int gst;
    private int serviceCharge;

    private static final double ZERO_COST_PAID = 0;
    public static final double ZERO_COST_OWED = 0;
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
        assert sessionId > 0 : Message.ASSERT_ACTIVITYCREATE_SESSION_ID_LESS_THAN_ONE;
        assert activityName != null : Message.ASSERT_ACTIVITYCREATE_ACTIVITY_NAME_MISSING;
        assert payer != null : Message.ASSERT_ACTIVITYCREATE_PAYER_NAME_MISSING;
        assert involvedList != null : Message.ASSERT_ACTIVITYCREATE_INVOLVED_LIST_ARRAY_NULL;
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
     * @param commandArgs A String object representing the user's arguments.
     * @return An ActivityCreateCommand object if necessary parameters were found in user arguments,
     *         an InvalidCommand object otherwise.
     */
    public static Command prepare(String commandArgs) {
        int sessionId;
        String activityName;
        String payer;
        String[] involvedList;
        double totalCost = 0;
        double[] costList = null;
        int gst;
        int serviceCharge;

        try {
            sessionId = Parser.parseSessionId(commandArgs);
            activityName = Parser.parseName(commandArgs);
            payer = Parser.parsePayer(commandArgs);
            involvedList = Parser.parseInvolved(commandArgs);
        } catch (InvalidFormatException e) {
            return new InvalidCommand(e.getMessage() + "\n" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST + "\n\t"
                    + COMMAND_FORMAT_SECOND);
        }

        boolean isMissingCost = false;
        boolean isMissingCostList = false;
        boolean hasDifferentLength = false;

        try {
            totalCost = Parser.parseTotalCost(commandArgs);
        } catch (InvalidFormatException e) {
            if (!e.getMessage().equalsIgnoreCase(Message.ERROR_PARSER_DELIMITER_NOT_FOUND
                    + ParserUtils.TOTAL_COST_DELIMITER)) {
                return new InvalidCommand(e.getMessage() + "\n" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST
                        + "\n\t" + COMMAND_FORMAT_SECOND);
            }
            isMissingCost = true;
        }

        try {
            costList = Parser.parseCostList(commandArgs);
        } catch (InvalidFormatException e) {
            if (!e.getMessage().equalsIgnoreCase(Message.ERROR_PARSER_DELIMITER_NOT_FOUND
                    + ParserUtils.COST_LIST_DELIMITER)) {
                return new InvalidCommand(e.getMessage() + "\n" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST
                        + "\n\t" + COMMAND_FORMAT_SECOND);
            }
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

        if (isMissingCost) {
            hasDifferentLength = involvedList.length != costList.length;
        }
        if (hasDifferentLength) {
            return new InvalidCommand(Message.ERROR_ACTIVITYCREATE_INVOLVED_AND_COST_DIFFERENT_LENGTH
                    + "\n" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST + "\n\t" + COMMAND_FORMAT_SECOND);
        }

        try {
            gst = Parser.parseGst(commandArgs);
            serviceCharge = Parser.parseServiceCharge(commandArgs);
        } catch (InvalidFormatException e) {
            return new InvalidCommand(e.getMessage() + "\n" + COMMAND_FORMAT + COMMAND_FORMAT_FIRST
                    + "\n\t" + COMMAND_FORMAT_SECOND);
        }

        return new ActivityCreateCommand(sessionId, activityName, totalCost, payer, involvedList, costList, gst,
                serviceCharge);
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
     * @param involvedPersonList An ArrayList object containing Person objects
     *                           each representing a person involved in the activity.
     * @param personPaid         A Person object representing the person who paid for the activity.
     * @param activityId         An integer that uniquely identifies an activity.
     * @throws InvalidDataException If the activityCost cannot be created from the given parameters.
     */
    private void addAllActivityCost(ArrayList<Person> involvedPersonList, Person personPaid, int activityId)
            throws InvalidDataException {
        boolean hasAddedForPersonPaid = false;
        for (int i = 0; i < involvedPersonList.size(); i++) {
            Person person = involvedPersonList.get(i);
            addCostOwedAndCostPaid(personPaid, activityId, i, person);
            hasAddedForPersonPaid = isPersonPaid(personPaid, hasAddedForPersonPaid, person);
        }
        if (!hasAddedForPersonPaid) {
            personPaid.addActivityCost(activityId, totalCost, ZERO_COST_OWED);
        }
    }

    /**
     * Checks if the Person object currently referred to represents the person who paid for the activity.
     *
     * @param personPaid            A Person object representing the person who paid for the activity.
     * @param hasAddedForPersonPaid A boolean representing whether the activity cost has been added for the person who
     *                              paid for the activity.
     * @param person                A Person object representing the person currently referred to
     *                              among the persons involved.
     * @return true if the Person object currently referred to represents the person who paid for the activity,
     *         hasAddedForPersonPaid otherwise.
     */
    private boolean isPersonPaid(Person personPaid, boolean hasAddedForPersonPaid, Person person) {
        if (person == personPaid) {
            hasAddedForPersonPaid = true;
        }
        return hasAddedForPersonPaid;
    }

    /**
     * Adds cost owed and cost paid to each individual's list of activity costs.
     * Checks if the current person is the person who paid for the activity.
     * If it is, the cost paid is set to the total cost of the activity.
     * Else, the cost paid is set to 0.
     *
     * @param personPaid      A Person object representing the person who paid for the activity.
     * @param activityId      An integer that uniquely identifies an activity.
     * @param indexOfCostOwed An integer representing the index of the cost owed in the list of costs.
     * @param person          A person object representing the person whose costs are added to the
     *                        list of activity costs.
     * @throws InvalidDataException If the activityCost cannot be created from the given parameters.
     */
    private void addCostOwedAndCostPaid(Person personPaid, int activityId, int indexOfCostOwed, Person person)
            throws InvalidDataException {
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
            updateCostListWithExtraCharges();
            calculateTotalCost();
        } else {
            updateCostWithExtraCharges();
            int numberOfPeopleInvolved = involvedList.length;
            costList = distributeCostEvenly(numberOfPeopleInvolved);
        }
    }

    /**
     * Updates cost list by including the extra charges.
     * Extra charges may include gst and service charge.
     * Assumption: gst and service charge are non-negative values.
     */
    private void updateCostListWithExtraCharges() {
        double extraCharges = getExtraCharges();
        for (int i = 0; i < costList.length; i++) {
            costList[i] *= extraCharges;
        }
    }

    /**
     * Updates the total cost of the activity by summing up the costs in the list of costs.
     */
    private void calculateTotalCost() {
        for (double cost : costList) {
            totalCost += cost;
        }
    }

    /**
     * Updates total cost by including the extra charges.
     * Extra charges may include gst and service charge.
     * Assumption: gst and service charge are non-negative values.
     */
    private void updateCostWithExtraCharges() {
        double extraCharges = getExtraCharges();
        totalCost *= extraCharges;
    }

    /**
     * Returns a double representing the extra charges that is to be included in costs of the activity.
     *
     * @return A double representing the extra charges.
     */
    private double getExtraCharges() {
        double gstMultiplier = 1 + (double) gst / 100;
        double serviceChargeMultiplier = 1 + (double) serviceCharge / 100;
        return gstMultiplier * serviceChargeMultiplier;
    }

    /**
     * Returns an array of doubles of the costs that has been distributed evenly
     * among the persons involved in the activity.
     * Divides the total cost by the number of people involved in the activity.
     *
     * @param numberOfPeopleInvolved An integer representing the number of people involved in the activity.
     * @return An array of doubles representing the costs of each person involved in the activity.
     */
    private double[] distributeCostEvenly(int numberOfPeopleInvolved) {
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
            Manager.getLogger().log(Level.FINEST,Message.LOGGER_ACTIVITYCREATE_DUPLICATE_NAMES_IN_INVOLVED_LIST);
            return;
        }
        try {
            updateCostAndCostList();
            assert costList != null : Message.ASSERT_ACTIVITYCREATE_COST_LIST_ARRAY_NULL;
            assert totalCost > 0 : Message.ASSERT_ACTIVITYCREATE_TOTAL_COST_LESS_THAN_ONE;
            Session session = manager.getProfile().getSession(sessionId);
            Person personPaid = session.getPersonByName(payer);
            ArrayList<Person> involvedPersonList = session.getPersonListByName(involvedList);
            int activityId = manager.getProfile().getNewActivityId();
            addAllActivityCost(involvedPersonList, personPaid, activityId);
            Activity activity = new Activity(activityId, activityName, totalCost, personPaid, involvedPersonList);
            session.addActivity(activity);
            manager.getUi().printlnMessageWithDivider(COMMAND_SUCCESS + activity);
            Manager.getLogger().log(Level.FINEST,Message.LOGGER_ACTIVITYCREATE_ACTIVITY_ADDED + activityId);
        } catch (InvalidDataException e) {
            manager.getUi().printlnMessage(e.getMessage());
            Manager.getLogger().log(Level.FINEST,Message.LOGGER_ACTIVITYCREATE_FAILED_ADDING_ACTIVITY
                    + "\n" + e.getMessage());
        }
    }

}
