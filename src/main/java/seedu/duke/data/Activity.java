package seedu.duke.data;

import java.util.ArrayList;

/**
 * Represents an activity that was done during a group outing session.
 */
public class Activity {

    private int sessionId;
    private int activityId;
    private String activityName;
    private double cost;
    private Person personPaid;
    private ArrayList<Person> involvedPersonList;

    /**
     * Constructs an Activity object that stores the relevant information required for an Activity.
     *
     * @param sessionId The id of the session.
     * @param activityId The id of the activity
     * @param activityName The name of the activity.
     * @param cost The total cost of the activity.
     * @param personPaid The person who paid for the activity.
     * @param involvedPersonList The persons involved in the activity.
     */
    public Activity(int sessionId, int activityId, String activityName, double cost, Person personPaid,
                    ArrayList<Person> involvedPersonList) {
        this.sessionId = sessionId;
        this.activityId = activityId;
        this.activityName = activityName;
        this.cost = cost;
        this.personPaid = personPaid;
        this.involvedPersonList = involvedPersonList;
    }

}
