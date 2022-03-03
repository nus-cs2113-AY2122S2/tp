package seedu.duke.data;

import java.util.ArrayList;

public class Profile {
    // To be completed when Warren pushes
    private ArrayList<Session> sessionList;
    private int sessionIdTracker;
    private int activityCount;

    public Profile() {
        
    }

    public ArrayList<Session> getSessionList() {
        return sessionList;
    }

    public void setSessionList(ArrayList<Session> sessionList) {
        this.sessionList = sessionList;
    }

    public int getSessionIdTracker() {
        return sessionIdTracker;
    }

    public void setSessionIdTracker(int sessionIdTracker) {
        this.sessionIdTracker = sessionIdTracker;
    }

    public int getActivityCount() {
        return activityCount;
    }

    public void setActivityCount(int activityCount) {
        this.activityCount = activityCount;
    }
}
