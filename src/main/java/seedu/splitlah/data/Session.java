package seedu.splitlah.data;

import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.ui.Message;

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
    
    // CONSTANTS
    private static final String ACTIVITY_LIST_HEADER =
            "Id | Activity Name | Cost | Payee";
    private static final String PERSON_LIST_HEADER =
            "Participants";
    private static final int ZERO_INDEXING_OFFSET = 1;

    /**
     * Returns the session's name.
     * 
     * @return A String object containing the name of the session.
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
     * @return A String object containing a human-readable version of the date of creation.
     */
    public String getDateString() {
        return dateCreated.getDayOfMonth() + " " + dateCreated.getMonth() + " " + dateCreated.getYear();
    }

    /**
     * Returns an Activity object specified by a numerical identifier that uniquely identifies the activity.
     * 
     * @param activityId An integer that uniquely identifies an Activity object in the profile.
     * @return An Activity object in the Session class specified by activityId
     * @throws InvalidDataException if activityList is empty or activityList does not contain an Activity object 
     *                              with the specified activityId
     */
    public Activity getActivity(int activityId) throws InvalidDataException {
        if (activityList.isEmpty()) {
            throw new InvalidDataException(Message.ERROR_SESSION_EMPTY_ACTIVITY_LIST);
        }

        for (Activity activity : activityList) {
            if (activity.getActivityId() == activityId) {
                return activity;
            }
        }
        throw new InvalidDataException(Message.ERROR_SESSION_ACTIVITY_ID_NOT_IN_LIST);
    }

    /**
     * Removes an Activity object specified by a numerical identifier that uniquely identifies the activity
     * from the Session.
     *
     * @param activityId An integer that uniquely identifies an Activity object in the profile.
     * @throws InvalidDataException if activityList is empty or activityList does not contain an Activity object 
     *                              with the specified activityId
     */
    public void removeActivity(int activityId) throws InvalidDataException {
        if (activityList.isEmpty()) {
            throw new InvalidDataException(Message.ERROR_SESSION_EMPTY_ACTIVITY_LIST);
        }

        Activity deleteTarget = null;
        for (Activity activity : activityList) {
            if (activity.getActivityId() == activityId) {
                deleteTarget = activity;
                break;
            }
        }

        if (deleteTarget == null) {
            throw new InvalidDataException(Message.ERROR_SESSION_ACTIVITY_ID_NOT_IN_LIST);
        }
        activityList.remove(deleteTarget);
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

    /**
     * Returns a Person object specified by a numerical index that identifies the Person.
     * 
     * @param index A numerical index that identifies a Person object in the session.
     * @return A Person object in the Session class specified by index.
     * @throws InvalidDataException if personList is empty or index is not in [1, personList.size()].
     */
    public Person getPersonByIndex(int index) throws InvalidDataException {
        if (personList.isEmpty()) {
            throw new InvalidDataException(Message.ERROR_SESSION_EMPTY_PERSON_LIST);
        }
        
        try {
            return personList.get(index - ZERO_INDEXING_OFFSET);
        } catch (IndexOutOfBoundsException exception) {
            throw new InvalidDataException(Message.ERROR_SESSION_INDEX_OUT_OF_RANGE_PERSON_LIST + personList.size());
        }
    }

    /**
     * Returns a Person object with a name that matches the queried name.
     * 
     * @param name A query name used to search for a Person object.
     * @return A Person object in the Session class that has a matching name.
     * @throws InvalidDataException if personList is empty or no Person object in personList matches queried name.
     */
    public Person getPersonByName(String name) throws InvalidDataException {
        if (personList.isEmpty()) {
            throw new InvalidDataException(Message.ERROR_SESSION_EMPTY_PERSON_LIST);
        }
        
        for (Person person : personList) {
            if (person.getName().equalsIgnoreCase(name)) {
                return person;
            }
        }
        throw new InvalidDataException(Message.ERROR_SESSION_PERSON_NOT_IN_LIST);
    }

    /**
     * Returns a list of Person objects representing the participants in the session.
     * 
     * @return An ArrayList object containing Person objects that are part of the session.
     */
    public ArrayList<Person> getPersonList() {
        return personList;
    }

    /**
     * Adds a Person object to the session.
     * 
     * @param person A Person object representing a participant of the session.
     */
    public void addPerson(Person person) {
        personList.add(person);
    }

    /**
     * Constructs a Session object with the specified information as a new session.
     * 
     * @param sessionName The name of the session.
     * @param sessionId   A unique identifier for the session.
     * @param dateCreated A LocalDate object storing the date that the session occurs on.
     * @param personList  A list of Person objects representing participants of the session.
     * @see Profile for issuing a unique sessionId
     */
    public Session(String sessionName, int sessionId, LocalDate dateCreated, ArrayList<Person> personList) {
        this.sessionName = sessionName;
        this.sessionId = sessionId;
        this.dateCreated = dateCreated;
        this.personList = personList;
        this.activityList = new ArrayList<>();
    }

    /**
     * Returns a String object containing a summary of the state of activityList.
     * 
     * @return A String object containing a summary of all Activity objects in activityList,
     *         or a message stating that the activityList is empty if there are no Activity objects within.
     */
    private String getActivityListSummaryString() {
        if (activityList.isEmpty()) {
            return Message.ERROR_SESSION_EMPTY_ACTIVITY_LIST;
        }
        
        StringBuilder summaryString = new StringBuilder(ACTIVITY_LIST_HEADER);
        for (Activity activity : activityList) {
            summaryString.append("\n > ").append(activity.getActivitySummaryString());
        }
        return summaryString.toString();
    }

    /**
     * Returns a String object containing a summary of the state of personList.
     * 
     * @return A String object containing a summary of all Person objects in personList,
     *         or a message stating that the personList is empty if there are no Person objects within.
     */
    private String getPersonListSummaryString() {
        if (personList.isEmpty()) {
            return Message.ERROR_SESSION_EMPTY_PERSON_LIST;
        }
        
        StringBuilder summaryString = new StringBuilder(PERSON_LIST_HEADER);
        for (int i = 0; i < personList.size(); i++) {
            String personName = personList.get(i).getName();
            summaryString.append("\n ").append(i).append(". ").append(personName);
        }
        return summaryString.toString();
    }

    /**
     * Returns a String object summarising the state of the Session object.
     * 
     * @return A String object containing a summary of the Session object and its member attributes.
     */
    @Override
    public String toString() {
        return "Session --"
                + "Name: " + sessionName + '\n'
                + "Id:   " + sessionId + '\n'
                + "Date: " + dateCreated + '\n'
                + getActivityListSummaryString() + '\n'
                + getPersonListSummaryString();
    }
}
