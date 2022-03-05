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
    private Person personPaying;
    private ArrayList<Person> involvedPersonList;

    public Activity(int sessionId, int activityId, String activityName, double cost, Person personPaying,
                    ArrayList<Person> involvedPersonList) {
        this .sessionId = sessionId;
        this.activityId = activityId;
        this.activityName = activityName;
        this.cost = cost;
        this.personPaying = personPaying;
        this.involvedPersonList = involvedPersonList;
    }

}
