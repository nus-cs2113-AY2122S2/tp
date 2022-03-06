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
    private ArrayList<Activity> activityList;
    private ArrayList<Person> personList;
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

    /**
     * Returns a list of Activity objects representing the activities that occurred in that session.
     * 
     * @return An ArrayList object containing Activity objects that are part of the session.
     */
    public ArrayList<Activity> getActivityList() {
        return activityList;
    }

    /**
     * Adds an Activity object to the session.
     * 
     * @param activity An Activity object representing an activity that happened in the session.
     */
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public ArrayList<Person> getPersonList() {
        return personList;
    }

    public void addPerson(Person person) {
        personList.add(person);
    }
}
