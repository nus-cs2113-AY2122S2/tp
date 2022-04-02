package seedu.splitlah.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Level;

import seedu.splitlah.data.*;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.ui.Message;
import seedu.splitlah.ui.TextUI;

/**
 * Represents a command object that edits an Activity object.
 *
 * @author Saurav
 */
public class ActivityEditCommand extends Command {

    private static final String COMMAND_SUCCESS = "The activity was edited successfully.\n";

    private final static double ZERO_COST_PAID = 0;
    public static final double ZERO_COST_OWED = 0;
    private static final int NO_COST = 0;
    private static final int MISSING_ACTIVITYID = -1;
    private static final int MISSING_SESSIONID = -1;
    private static final double MISSING_TOTALCOST = -1;
    private static final double MISSING_GST = -1;
    private static final double MISSING_SERVICECHARGE = -1;
    private static final double[] MISSING_COSTLIST = null;
    private static final String MISSING_ACTIVITYNAME = null;
    private static final String MISSING_PAYER = null;
    private static final String[] MISSING_INVOLVEDLIST = null;
    private static final int DUMMY_ACTIVITYID = MISSING_ACTIVITYID;

    private int activityId = MISSING_ACTIVITYID;
    private int sessionId = MISSING_SESSIONID;
    private String activityName = MISSING_ACTIVITYNAME;
    private double totalCost = MISSING_TOTALCOST;
    private String payer = MISSING_PAYER;
    private String[] involvedList = MISSING_INVOLVEDLIST;
    double[] costList = MISSING_COSTLIST;
    private double gst = MISSING_GST;
    private double oldGst = MISSING_GST;
    private double serviceCharge = MISSING_SERVICECHARGE;
    private double oldServiceCharge = MISSING_SERVICECHARGE;

    private ArrayList<Person> involvedArrayList = null;

    /**
     * Initializes an ActivityEditCommand object.
     *
     * @param sessionId     An integer that uniquely identifies a session.
     * @param activityId    An integer that uniquely identifies a session.
     * @param activityName  A String object that represents the Activity object's name.
     * @param totalCost     A double that represents total cost of the activity.
     * @param payer         A String object that represents the name of the person who paid for the activity.
     * @param involvedList  An array of String objects that represents the names of the persons
     *                      who are involved in the activity.
     * @param costList      A double array object that represents the respective costs of
     *                      each person involved in the activity.
     * @param gst           A double that represents the GST percentage to be included for the cost of the activity.
     * @param serviceCharge A double that represents the service charge to be included for the cost of the activity.
     */
    public ActivityEditCommand(int sessionId, int activityId, String activityName, String payer, String[] involvedList,
                               Double totalCost, double[] costList, double gst, double serviceCharge) {
        assert sessionId > 0 : Message.ASSERT_ACTIVITYEDIT_SESSIONID_LESS_THAN_ONE;
        assert activityId > 0 : Message.ASSERT_ACTIVITYEDIT_ACTIVITYID_MISSING;
        this.activityId = activityId;
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
     * Adds all relevant activity costs to each involved person's list of activity costs.
     *
     * @param involvedPersonList An ArrayList object containing Person objects
     *                           each representing a person involved in the activity.
     * @param personPaid         A Person object representing the person who paid for the activity.
     * @param activityId         An integer that uniquely identifies an activity.
     */
    private void addAllActivityCost(ArrayList<Person> involvedPersonList, Person personPaid, int activityId) {
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
     */
    private void addCostOwedAndCostPaid(Person personPaid, int activityId, int indexOfCostOwed, Person person) {
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
        if (totalCost == MISSING_TOTALCOST && costList != MISSING_COSTLIST) {
            totalCost = 0;
            removeOldExtraChargesFromCostList();
            updateCostListWithExtraCharges();
            calculateTotalCost();
        } else if (totalCost != MISSING_TOTALCOST && costList == MISSING_COSTLIST) {
            removeOldExtraChargesFromCost();
            updateCostWithExtraCharges();
            int numberOfPeopleInvolved = involvedList.length;
            costList = distributeCostEvenly(numberOfPeopleInvolved);
        } else if (totalCost != MISSING_TOTALCOST) {
            totalCost = 0;
            updateCostListWithExtraCharges();
            calculateTotalCost();
        }
    }

    private void removeOldExtraChargesFromCost() {
        double oldGstMultiplier = 1 + (oldGst / 100);
        double oldServiceChargeMultiplier = 1 + (oldServiceCharge / 100);
        totalCost /= (oldGstMultiplier * oldServiceChargeMultiplier);
    }

    private void removeOldExtraChargesFromCostList() {
        double oldGstMultiplier = 1 + (oldGst / 100);
        double oldServiceChargeMultiplier = 1 + (oldServiceCharge / 100);
        for (int i = 0; i < costList.length; ++i) {
            costList[i] /= (oldGstMultiplier * oldServiceChargeMultiplier);
        }
    }

    /**
     * Updates cost list by including the extra charges.
     * Extra charges may include GST and service charge.
     * Assumption: GST and service charge are non-negative values.
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
     * Extra charges may include GST and service charge.
     * Assumption: GST and service charge are non-negative values.
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
        double gstMultiplier = 1 + gst / 100;
        double serviceChargeMultiplier = 1 + serviceCharge / 100;
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

    @Override
    public void run(Manager manager) {
        TextUI ui = manager.getUi();
        try {
            assert activityId != MISSING_ACTIVITYID;
            assert sessionId != MISSING_SESSIONID;

            Profile profile = manager.getProfile();
            Session session = profile.getSession(sessionId);
            Activity oldActivity = session.getActivity(activityId);
            retrieveDetailsFromOldActivity(oldActivity);
            validateCostListAndInvolvedList();
            updateCostAndCostList();
            assert costList != null : Message.ASSERT_ACTIVITYEDIT_COST_LIST_ARRAY_NULL;
            assert totalCost > 0 : Message.ASSERT_ACTIVITYEDIT_TOTAL_COST_LESS_THAN_ONE;
            involvedArrayList = session.getPersonListByName(involvedList);
            Person payerAsPerson = session.getPersonByName(payer);
            addAllActivityCost(involvedArrayList, payerAsPerson, DUMMY_ACTIVITYID);
            PersonList involvedPersonList = new PersonList(involvedArrayList);
            session.removeActivity(activityId);
            Activity newActivity = new Activity(activityId, activityName, totalCost, payerAsPerson, involvedPersonList, gst, serviceCharge);
            session.addActivity(newActivity);
            updateDummyActivityIdsInActivityCosts(session);
            manager.saveProfile();
            ui.printlnMessage(COMMAND_SUCCESS);
            ui.printlnMessage(newActivity.getActivitySummaryString());
            Manager.getLogger().log(Level.FINEST, Message.LOGGER_ACTIVITYEDIT_ACTIVITY_EDITED);
        } catch (InvalidDataException exception) {
            ui.printlnMessage(exception.getMessage());
        }
    }

    private void updateDummyActivityIdsInActivityCosts(Session session) {
        ArrayList<Person> involvedPersonList = session.getPersonList();
        for (Person person : involvedPersonList) {
            for (ActivityCost activityCost : person.getActivityCostList()) {
                if (activityCost.getActivityId() == MISSING_ACTIVITYID) {
                    activityCost.setActivityId(activityId);
                }
            }
        }
    }

    private void validateCostListAndInvolvedList() throws InvalidDataException {
        if (totalCost == MISSING_TOTALCOST
                && (involvedList != MISSING_INVOLVEDLIST
                ^ costList == MISSING_COSTLIST)) {
            Manager.getLogger().log(Level.FINEST, Message.LOGGER_ACTIVITYEDIT_FAILED_EDITING_ACTIVITY);
            throw new InvalidDataException(Message.ERROR_ACTIVITYEDIT_COSTLIST_AND_INVOLVEDLIST_MISMATCH);
        }

        if (involvedList != MISSING_INVOLVEDLIST && costList != MISSING_COSTLIST) {
            if (involvedList.length != costList.length) {
                Manager.getLogger().log(Level.FINEST, Message.LOGGER_ACTIVITYEDIT_FAILED_EDITING_ACTIVITY);
                throw new InvalidDataException(Message.ERROR_ACTIVITYEDIT_INVOLVED_AND_COST_DIFFERENT_LENGTH);
            }
        }

        if (involvedList != MISSING_INVOLVEDLIST && PersonList.hasNameDuplicates(involvedList)) {
            Manager.getLogger().log(Level.FINEST, Message.LOGGER_ACTIVITYEDIT_FAILED_EDITING_ACTIVITY);
            throw new InvalidDataException(Message.ERROR_ACTIVITYEDIT_DUPLICATE_NAME);
        }
    }

    private void retrieveDetailsFromOldActivity(Activity oldActivity) {
        oldGst = oldActivity.getGst();
        oldServiceCharge = oldActivity.getServiceCharge();
        if (Objects.equals(activityName, MISSING_ACTIVITYNAME)) {
            activityName = oldActivity.getActivityName();
        }
        if (totalCost == -1) {
            totalCost = oldActivity.getTotalCost();
        }
        if (Objects.equals(payer, MISSING_PAYER)) {
            payer = oldActivity.getPersonPaid().getName();
        }
        if (involvedList == MISSING_INVOLVEDLIST) {
            involvedList = getInvolvedListFromPersonList(oldActivity.getInvolvedPersonList());
        }
        if (gst == MISSING_GST) {
            gst = oldActivity.getGst();
        }
        if (serviceCharge == MISSING_SERVICECHARGE) {
            serviceCharge = oldActivity.getServiceCharge();
        }
    }

    private String[] getInvolvedListFromPersonList(ArrayList<Person> involvedPersonList) {
        int listSize = involvedPersonList.size();
        String[] involvedListStringArray = new String[listSize];
        for (int i = 0; i < listSize; ++i) {
            involvedListStringArray[i] = involvedPersonList.get(i).getName();
        }
        return involvedListStringArray;
    }

    /**
     * Runs the command with the session identifier and activity identifier as provided by the user input.
     *
     * @param manager A Manager object that manages the TextUI, Profile and Storage objects.
    @Override
    public void ru2n(Manager manager) {
        TextUI ui = manager.getUi();
        try {
            ArrayList<Person> involvedListOfPersons;
            Session session = manager.getProfile().getSession(sessionId);
            Activity oldActivity = session.getActivity(activityId);
            boolean hasDuplicates = false;
            // if the user provides the person list, check for duplicates
            // otherwise, set the person list to the existing person list
            if (involvedList != MISSING_INVOLVEDLIST) {
                hasDuplicates = PersonList.hasNameDuplicates(involvedList);
                involvedListOfPersons = session.getPersonListByName(involvedList);
            } else {
                involvedListOfPersons = oldActivity.getInvolvedPersonList();
            }
            if (hasDuplicates) {
                ui.printlnMessage(Message.ERROR_ACTIVITYEDIT_DUPLICATE_NAME);
                Manager.getLogger().log(Level.FINEST, Message.LOGGER_ACTIVITYEDIT_DUPLICATE_NAMES_IN_INVOLVED_LIST);
                return;
            }
            updateCostAndCostList();
            assert costList != null : Message.ASSERT_ACTIVITYEDIT_COST_LIST_ARRAY_NULL;
            assert totalCost > 0 : Message.ASSERT_ACTIVITYEDIT_TOTAL_COST_LESS_THAN_ONE;

            Person personPaid = session.getPersonByName(payer);
            ArrayList<Person> involvedArrayList = session.getPersonListByName(involvedList);

            session.removeActivity(activityId);

            addAllActivityCost(involvedArrayList, personPaid, activityId);

            PersonList involvedPersonList = new PersonList(involvedArrayList);
            Activity editedActivity = new Activity(activityId, activityName, totalCost, personPaid, involvedPersonList);

            session.addActivity(editedActivity);
            manager.saveProfile();
            ui.printlnMessage(COMMAND_SUCCESS + editedActivity);
            Manager.getLogger().log(Level.FINEST, Message.LOGGER_ACTIVITYEDIT_ACTIVITY_EDITED  + activityId);
        } catch (InvalidDataException e) {
            ui.printlnMessage(e.getMessage());
            Manager.getLogger().log(Level.FINEST, Message.LOGGER_ACTIVITYEDIT_FAILED_EDITING_ACTIVITY
                    + "\n" + e.getMessage());
        }
    }
    **/
}
