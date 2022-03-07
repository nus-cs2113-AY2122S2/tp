package seedu.splitlah.data;

import seedu.splitlah.exceptions.InvalidDataException;

import java.util.ArrayList;

/**
 * Represents an activity that was done during a group outing session.
 */
public class Activity {

    private static final String DELIMITER = " | ";

    private int activityId;
    private String activityName;
    private double totalCost;
    private Person personPaid;
    private ArrayList<Person> involvedPersonList;

    /**
     * Constructs an Activity object that stores the relevant information required for an Activity.
     *
     * @param activityId The id of the activity
     * @param activityName The name of the activity.
     * @param totalCost The total cost of the activity.
     * @param personPaid The person who paid for the activity.
     * @param involvedPersonList The persons involved in the activity.
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
     * Returns the id of the activity.
     *
     * @return The id of the activity.
     */
    public int getActivityId() {
        return activityId;
    }

    /**
     * Returns the name of the activity.
     *
     * @return The name of the activity.
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * Returns the total cost of the activity.
     *
     * @return The total cost of the activity.
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * Returns a person object representing the person who paid.
     *
     * @return A person object.
     */
    public Person getPersonPaid() {
        return personPaid;
    }

    /**
     * Returns an array list of person objects representing those who are involved in the activity.
     *
     * @return An array list of person objects.
     */
    public ArrayList<Person> getInvolvedPersonList() {
        return involvedPersonList;
    }

    /**
     * Returns a string which summarises the activity, including id of the activity, the name of the activity,
     * the total cost of the activity and the name of the person who paid for the activity.
     *
     * @return A string which summarises the activity.
     */
    public String getActivitySummaryString() {
        String costInString = "$" + String.format("%.2f", totalCost);
        String personName = personPaid.getName();
        return activityId + DELIMITER + activityName + DELIMITER + costInString + DELIMITER + personName;
    }

    /**
     * Returns a string which provides the details of the activity, including the name of the activity,
     * the id of the activity, the name of the person who paid for the activity, the total cost of the activity,
     * the persons involved in the activity and their respective costs for the activity.
     *
     * @return A string which provides the details of the activity.
     * @throws InvalidDataException If there is no activity found or if the person in the involvedPersonsList
     *     did not participate in the activity.
     * @see InvalidDataException
     */
    public String getActivityDetails() throws InvalidDataException {
        return "Activity --"
                + "Name: " + activityName + '\n'
                + "Id:   " + activityId + '\n'
                + "Payer: " + personPaid.getName() + '\n'
                + "Cost: " + totalCost + '\n'
                + "Involved: \n"
                + getInvolvedListString();
    }

    /**
     * Returns a string of the persons involved in the activity and their respective costs for the activity.
     *
     * @return A string of persons involved and their respective costs.
     * @throws InvalidDataException If there is no activity found or if the person in the involvedPersonsList
     *     did not participate in the activity.
     * @see InvalidDataException
     */
    private String getInvolvedListString() throws InvalidDataException {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= involvedPersonList.size(); i++) {
            Person person = involvedPersonList.get(i - 1);
            String personName = person.getName();
            Double costOwed = person.getActivityCostOwed(activityId);
            String nextLineToAppend = formString(i, personName, costOwed);
            sb.append(nextLineToAppend);
        }
        return sb.toString();
    }

    private String formString(int index, String personName, Double costOwed) {
        return "<" + index + ". " + personName + ", $" + String.format("%.2f", costOwed) + ">\n";
    }

}
