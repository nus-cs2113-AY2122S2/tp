package seedu.duke.data;

import java.util.ArrayList;

public class Profile {
    // To be completed when Warren pushes
    private ArrayList<Session> sessionList;
    private int sessionIdTracker;
    private int activityIdTracker;

    public Profile() {
        this.sessionList = new ArrayList<>();
        sessionIdTracker = 0;
        activityIdTracker = 0;
    }

    public ArrayList<Session> getSessionList() {
        return sessionList;
    }

    public int getNewSessionId() {
        return sessionIdTracker + 1;
    }

    public void updateSessionIdTracker() {
        this.sessionIdTracker += 1;
    }

    public int getNewActivityIdTracker() {
        return activityIdTracker + 1;
    }

    public void updateActivityIdTracker() {
        this.activityIdTracker += 1;
    }


}
