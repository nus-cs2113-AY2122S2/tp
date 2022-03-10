package seedu.splitlah.data;

import seedu.splitlah.exceptions.InvalidDataException;

import java.util.ArrayList;

/**
 * Represents an activity that was done during a group outing session.
 */
public class Activity {

    private static final String SUMMARY_STRING_SEPARATOR = " | ";
    private static final int ZERO_INDEXING_OFFSET = 1;

    private int activityId;
    private String activityName;
    private double totalCost;
    private Person personPaid;
    private ArrayList<Person> involvedPersonList;

    /**
     * Constructs an Activity object that stores the relevant information required for an Activity.
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
     * Returns a String which provides the details of the activity, including the name of the activity,
     * the activity's unique identifier, the name of the person who paid for the activity,
     * the total cost of the activity, the persons involved in the activity
     * and their respective costs for the activity.
     *
     * @return A String object representing the details of the activity.
     * @throws InvalidDataException If there is no activity found or if the person in the involvedPersonsList
     *                              did not participate in the activity.
     */
    @Override
    public String toString() {
        return "Activity --"
                + "Name: " + activityName + '\n'
                + "Id:   " + activityId + '\n'
                + "Payer: " + personPaid.getName() + '\n'
                + "Cost: " + totalCost + '\n'
                + "Involved: \n"
                + getInvolvedListString();
    }

    /**
     * Returns a String object of the persons involved in the activity and their respective costs for the activity,
     * if the unique identifier of the activity exists and the persons involved indeed participated in the activity.
     * Else, the method returns an error message.
     *
     * @return A String object representing persons involved and their respective costs.
     * @throws InvalidDataException If there is no activity found or if the person in the involvedPersonsList
     *                              did not participate in the activity.
     */
    private String getInvolvedListString() {
        String involvedListString;
        try {
            involvedListString = convertInvolvedListToString();
        } catch (InvalidDataException e) {
            return e.getMessage();
        }
        return involvedListString;
    }

    /**
     * Returns a String object representing the name of the persons involved in the activity and the cost that each
     * person owed for the activity in a proper format.
     *
     * @return a String object that representing the name and costs of each person involved in the activity.
     * @throws InvalidDataException If there is no activity found or if the person in the involvedPersonsList
     *                              did not participate in the activity.
     */
    private String convertInvolvedListToString() throws InvalidDataException {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= involvedPersonList.size(); i++) {
            Person person = involvedPersonList.get(i - ZERO_INDEXING_OFFSET);
            String personName = person.getName();
            Double costOwed = person.getActivityCostOwed(activityId);
            String nextLineToAppend = formString(i, personName, costOwed);
            sb.append(nextLineToAppend);
        }
        return sb.toString();
    }

    /**
     * Returns a string with the correct format for the involved persons and their costs for the activity.
     * Format: <[INDEX]. [PERSON_NAME], $[COST_OWED]>, e.g. <1. Bob, $5.00>
     *
     * @param index The index of the person involved in the activity.
     * @param personName The name of the person involved in the activity.
     * @param costOwed The cost that has not been paid by the person involved in the activity.
     * @return A String object with the correct format representing involved persons and costs.
     */
    private String formString(int index, String personName, Double costOwed) {
        return "<" + index + ". " + personName + ", $" + String.format("%.2f", costOwed) + ">\n";
    }

}
