package seedu.duke.data;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a group outing session which contains a collection of activities done in that day
 * and is participated by a group of participants.
 * 
 * @author Warren
 */
public class Session {
    private String sessionName;
    private int sessionId;
    private LocalDate dateCreated;
    // private ArrayList<Person> personList;
    private ArrayList<Activity> activityList;
    // private Group group;

    /**
     * Returns the session's name.
     * 
     * @return A string object containing the name of the session
     */
    public String getSessionName() {
        return sessionName;
    }

    /**
     * Returns the session's unique identifier number.
     * Assumption: The identifier number is unique across all session identifiers in the profile.
     * 
     * @return An integer representing the session's identifier number.
     */
    public int getSessionId() {
        return sessionId;
    }

    /**
     * Returns the date of which the session is created.
     * 
     * @return A LocalDate object containing the date of creation of the session.
     */
    public LocalDate getDateCreated() {
        return dateCreated;
    }

    /**
     * Returns a String object containing a human-readable version of the date of creation of the session.
     * Format: DD Month YYYY, e.g. 04 March 2022
     * 
     * @return A String object containing a human-readable version of the date of creation 
     */
    public String getDateString() {
        return dateCreated.getDayOfMonth() + " " + dateCreated.getMonth() + " " + dateCreated.getYear();
    }
    public ArrayList<Activity> getActivityList() {
        return activityList;
    }

    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

}
