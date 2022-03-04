package seedu.duke.data;

import java.time.LocalDate;
import java.util.ArrayList;

public class Session {
    // To be completed when Activity, Person and Group classes are added
    private String sessionName;
    private int sessionId;
    private LocalDate dateCreated;
    // private ArrayList<Activity> activityList;
    // private ArrayList<Person> personList;
    // private Group group;
    public String getSessionName() {
        return sessionName;
    }

    public int getSessionId() {
        return sessionId;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public String getDateString() {
        return dateCreated.getDayOfMonth() + " " + dateCreated.getMonth() + " " + dateCreated.getYear();
    }
}
