package seedu.duke.data;

import java.util.ArrayList;

/**
 * Represents a Profile for the SplitLah application
 */
public class Profile {
    private ArrayList<Session> sessionList;
    private int sessionIdTracker;
    private int activityIdTracker;

    /**
     * Constructor to create a profile object.
     */
    public Profile() {
        this.sessionList = new ArrayList<>();
        sessionIdTracker = 0;
        activityIdTracker = 0;
    }

    /**
     * Returns the list of session stored in the profile.
     * @return The ArrayList of sessions.
     */
    public ArrayList<Session> getSessionList() {
        return sessionList;
    }

    /**
     * Returns a new session id for session object to be created.
     * @return The new session id as int.
     */
    public int getNewSessionId() {
        return sessionIdTracker + 1;
    }

    /**
     * Updates the new sessionIdTracker.
     *
     * Assumption: when this function is called,
     * a new session object is created without errors.
     */
    public void updateSessionIdTracker() {
        this.sessionIdTracker += 1;
    }

    /**
     * Returns a new activity id for activity object to be created
     * @return The new activity id as int.
     */
    public int getNewActivityIdTracker() {
        return activityIdTracker + 1;
    }

    /**
     * Updates the new activityIdTracker.
     *
     * Assumption: when this function is called,
     * a new activity object is created without errors.
     */
    public void updateActivityIdTracker() {
        this.activityIdTracker += 1;
    }


}
