package seedu.splitlah.data;

import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.ui.Message;
import seedu.splitlah.ui.TableFormatter;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents an activity that was done during a group outing session.
 *
 * @author Ivan
 */
public class Activity implements Serializable, Comparable<Activity> {

    private int activityId;
    private String activityName;
    private double totalCost;
    private Person personPaid;
    private ArrayList<Person> involvedPersonList;

    private static final String SUMMARY_STRING_SEPARATOR = " | ";
    private static final int ZERO_INDEXING_OFFSET = 1;
    private static final String[] INVOLVED_PERSON_LIST_COLS = { "#", "Name", "Cost Owed" };

    /**
     * Initializes an Activity object.
     *
     * @param activityId         An integer that uniquely identifies an activity.
     * @param activityName       A String object that represents the activity's name.
     * @param totalCost          A double that represents the total cost spent in the activity.
     * @param personPaid         A Person object representing the person who paid for the activity.
     * @param involvedPersonList An ArrayList object containing Person objects
     *                           each representing a person involved in the activity.
     */
    public Activity(int activityId, String activityName, double totalCost, Person personPaid,
                    ArrayList<Person> involvedPersonList) {
        this.activityId = activityId;
        this.activityName = activityName;
        this.totalCost = totalCost;
        this.personPaid = personPaid;
        this.involvedPersonList = involvedPersonList;
    }

    /**
     * Returns an integer representing the activity's unique identifier.
     * Assumption: The identifier number is unique across all activity identifiers in the Profile object.
     *
     * @return An integer representing the activity's identifier.
     */
    public int getActivityId() {
        return activityId;
    }

    /**
     * Returns a String object representing the activity's name.
     *
     * @return A String object containing the name of the activity.
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * Returns a double representing the activity's total cost.
     *
     * @return A double representing the total cost spent in the activity.
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * Returns a Person object representing the activity's payer.
     *
     * @return A Person object representing the person who paid for the activity.
     */
    public Person getPersonPaid() {
        return personPaid;
    }

    /**
     * Returns an ArrayList object of Person objects representing the persons involved in the activity.
     *
     * @return An ArrayList object containing Person objects each representing a person involved in the activity.
     */
    public ArrayList<Person> getInvolvedPersonList() {
        return involvedPersonList;
    }

    /**
     * Returns a String object which summarises the activity, including the activity's unique identifier,
     * the name of the activity, the total cost spent in the activity
     * and the name of the person who paid for the activity.
     *
     * @return A String object which summarises the activity.
     */
    public String getActivitySummaryString() {
        String costInString = "$" + String.format("%.2f", totalCost);
        String personName = personPaid.getName();
        return activityId + SUMMARY_STRING_SEPARATOR + activityName + SUMMARY_STRING_SEPARATOR + costInString
                + SUMMARY_STRING_SEPARATOR + personName;
    }

    /**
     * Returns a String object of the persons involved in the activity and their respective costs for the activity.
     *
     * @return A String object representing persons involved and their respective costs owed.
     * @throws InvalidDataException If the person in the involvedPersonsList did not participate in the activity.
     */
    private String getInvolvedListString() throws InvalidDataException {
        if (involvedPersonList.isEmpty()) {
            return Message.ERROR_ACTIVITY_EMPTY_INVOLVED_PERSON_LIST;
        }

        TableFormatter summaryTable = new TableFormatter(
                INVOLVED_PERSON_LIST_COLS[0],
                INVOLVED_PERSON_LIST_COLS[1],
                INVOLVED_PERSON_LIST_COLS[2]
        );
        for (int i = 1; i <= involvedPersonList.size(); i++) {
            String index = Integer.toString(i);
            Person person = involvedPersonList.get(i - ZERO_INDEXING_OFFSET);
            String personName = person.getName();
            Double cost = person.getActivityCostOwed(activityId);
            String costOwed = String.format("%.2f", cost);
            summaryTable.addRow(index, personName, costOwed);
        }
        return summaryTable.toString();
    }

    /**
     * Returns an integer to identify whether this Activity object should be ordered
     * before or after another Activity object when sorted.
     *
     * @param activity The specified Activity object that this Activity object is compared against.
     * @return An integer less than 0 if this Activity object's activityId is smaller than the specified
     *         Activity object's sessionId,
     *         an integer greater than 0 if this object's activityId is larger,
     *         and 0 if both Activity objects' activityIds are numerically equal.
     */
    @Override
    public int compareTo(Activity activity) {
        return Integer.compare(activityId, activity.getActivityId());
    }

    /**
     * Returns a String which provides the details of the activity, including the name of the activity,
     * the activity's unique identifier, the name of the person who paid for the activity,
     * the total cost of the activity, the persons involved in the activity
     * and their respective costs for the activity.
     *
     * @return A String object representing the details of the activity.
     */
    @Override
    public String toString() {
        try {
            return "Activity Id #" + activityId + " --\n"
                    + "Name:  " + activityName + '\n'
                    + "Id:    " + activityId + '\n'
                    + "Payer: " + personPaid.getName() + '\n'
                    + "Cost:  $" + String.format("%.2f", totalCost) + '\n'
                    + "Involved: \n"
                    + getInvolvedListString();
        } catch (InvalidDataException e) {
            return Message.ERROR_ACTIVITY_INACCURATE_INVOLVED_LIST;
        }
    }
}
