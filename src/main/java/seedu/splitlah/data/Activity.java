package seedu.splitlah.data;

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

    public int getActivityId() {
        return activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public Person getPersonPaid() {
        return personPaid;
    }

    public ArrayList<Person> getInvolvedPersonList() {
        return involvedPersonList;
    }

    public String getActivitySummaryString() {
        String costInString = "$" + String.format("%.2f", totalCost);
        String personName = personPaid.getName();
        return activityId + DELIMITER + activityName + DELIMITER + costInString + DELIMITER + personName;
    }

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

    private String getInvolvedListString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= involvedPersonList.size(); i++) {
            Person person = involvedPersonList.get(i - 1);
            String personName = person.getName();
            Double costOwed = person.getActivityCost(activityId);
            String nextLineToAppend = formString(i, personName, costOwed);
            sb.append(nextLineToAppend);
        }
        return sb.toString();
    }

    private String formString(int index, String personName, Double costOwed) {
        return "<" + index + ". " + personName + ", $" + String.format("%.2f", costOwed) + ">\n";
    }

}
