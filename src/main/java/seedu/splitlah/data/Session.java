package seedu.splitlah.data;

import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.ui.Message;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a group outing session which contains a list of activities and is participated by a group of participants.
 *
 * @author Warren
 */
public class Session {

    private String sessionName;
    private int sessionId;
    private LocalDate dateCreated;
    private ArrayList<Activity> activityList;
    private PersonList personList;
    private Group group;

    // CONSTANTS
    private static final String ACTIVITY_LIST_HEADER =
            "Id | Activity Name | Cost | Payee";
    private static final String PERSON_LIST_HEADER =
            "Participants";
    private static final String SUMMARY_STRING_SEPARATOR = " | ";
    private static final int ZERO_INDEXING_OFFSET = 1;
    
    /**
     * Constructs a Session object with the specified information as a new session.
     *
     * @param sessionName The name of the session.
     * @param sessionId   A unique identifier for the session.
     * @param dateCreated A LocalDate object storing the date that the session occurs on.
     * @param personList  An ArrayList of Person objects representing participants of the session.
     * @see Profile#getNewSessionId() for issuing a unique sessionId
     */
    public Session(String sessionName, int sessionId, LocalDate dateCreated, PersonList personList) {
        assert personList != null : Message.ASSERT_SESSION_PERSON_LIST_EMPTY;
        assert personList.getSize() != 0 : Message.ASSERT_SESSION_PERSON_LIST_EMPTY;
        this.sessionName = sessionName;
        this.sessionId = sessionId;
        this.dateCreated = dateCreated;
        this.personList = personList;
        this.activityList = new ArrayList<>();
        this.group = null;
    }

    /**
     * Constructs a Session object with the specified information as a new session.
     *
     * @param sessionName The name of the session.
     * @param sessionId   A unique identifier for the session.
     * @param dateCreated A LocalDate object storing the date that the session occurs on.
     * @param personList  An ArrayList of Person objects representing participants of the session.
     * @param group       A Group object representing a group of persons participating in the session.
     * @see Profile#getNewSessionId() for issuing a unique sessionId
     */
    public Session(String sessionName, int sessionId, LocalDate dateCreated, PersonList personList,
                   Group group) {
        assert personList != null : Message.ASSERT_SESSION_PERSON_LIST_EMPTY;
        assert personList.getSize() != 0 : Message.ASSERT_SESSION_PERSON_LIST_EMPTY;
        this.sessionName = sessionName;
        this.sessionId = sessionId;
        this.dateCreated = dateCreated;
        this.personList = personList;
        this.activityList = new ArrayList<>();
        this.group = group;
    }
    
    /**
     * Returns the session's name.
     *
     * @return A String object containing the name of the session.
     */
    public String getSessionName() {
        return sessionName;
    }

    /**
     * Returns the session's unique identifier.
     * Assumption: The identifier number is unique across all session identifiers in the profile.
     *
     * @return An integer representing the session's identifier.
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
     * Returns a list of Activity objects representing the activities that occurred in that session.
     *
     * @return An ArrayList object containing Activity objects that are part of the session.
     */
    public ArrayList<Activity> getActivityList() {
        return activityList;
    }

    /**
     * Returns a list of Person objects representing the participants in the session.
     *
     * @return An ArrayList object containing Person objects that are part of the session.
     */
    public ArrayList<Person> getPersonList() {
        return personList.getPersonList();
    }

    /**
     * Returns a Group object representing a group of persons participating in the session.
     * 
     * @return A Group object containing Person objects that are participating in the session.
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Checks whether the Session object has an Activity object with the queried unique identifier.
     * 
     * @param activityId An integer that uniquely identifies an Activity object in the profile.
     * @return true if the Session object has an Activity object with a unique identifier matching the query,
     *         false otherwise.
     */
    public boolean hasActivity(int activityId) {
        if (activityList.isEmpty()) {
            return false;
        }
        
        for (Activity activity : activityList) {
            if (activity.getActivityId() == activityId) {
                return true;
            }
        }
        return false;
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
     * from the Session. Additionally, removes all ActivityCost objects with the same activityId from all Person
     * objects involved in the activity.
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

        ArrayList<Person> involvedPersonList = deleteTarget.getInvolvedPersonList();
        if (involvedPersonList != null) {
            for (Person person : involvedPersonList) {
                person.removeActivityCost(activityId);
            }
        }
        activityList.remove(deleteTarget);
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
            return personList.getPerson(index - ZERO_INDEXING_OFFSET);
        } catch (IndexOutOfBoundsException exception) {
            throw new InvalidDataException(Message.ERROR_SESSION_INDEX_OUT_OF_RANGE_PERSON_LIST + personList.getSize());
        }
    }

    /**
     * Returns a Person object with a name that matches the queried name.
     *
     * @param name A query name used to search for a Person object.
     * @return A Person object in the Session class that has a matching name.
     * @throws InvalidDataException If personList is empty or 
     *                              if no Person object in personList matches queried name.
     */
    public Person getPersonByName(String name) throws InvalidDataException {
        if (personList.isEmpty()) {
            throw new InvalidDataException(Message.ERROR_SESSION_EMPTY_PERSON_LIST);
        }

        for (Person person : personList.getPersonList()) {
            if (person.getName().equalsIgnoreCase(name)) {
                return person;
            }
        }
        throw new InvalidDataException(Message.ERROR_SESSION_PERSON_NOT_IN_LIST);
    }

    /**
     * Returns an ArrayList of Person objects with names that match the provided name list.
     *
     * @param nameList An array of String objects that represent names of people in the session.
     * @return An ArrayList object containing Person objects with matching names.
     * @throws InvalidDataException if personList is empty or
     *                              if any name in nameList does not match a Person object in personList.
     */
    public ArrayList<Person> getPersonListByName(String[] nameList) throws InvalidDataException {
        ArrayList<Person> personList = new ArrayList<>();
        for (String name : nameList) {
            Person newPerson = getPersonByName(name);
            personList.add(newPerson);
        }
        return personList;
    }

    /**
     * Adds a Person object to the session.
     *
     * @param person A Person object representing a participant of the session.
     */
    public void addPerson(Person person) {
        personList.addPerson(person);
    }

    /**
     * Returns a String object containing a summary of the state of the member attribute activityList.
     *
     * @return A String object containing a summary of all Activity objects in activityList, or
     *         a message stating that the activityList is empty if there are no Activity objects within.
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
     * Returns a String object containing a summary of the state of the member attribute personList.
     *
     * @return A String object containing a summary of all Person objects in personList, or
     *         a message stating that the personList is empty if there are no Person objects within.
     */
    private String getPersonListSummaryString() {
        if (personList.isEmpty()) {
            return Message.ERROR_SESSION_EMPTY_PERSON_LIST;
        }

        StringBuilder summaryString = new StringBuilder(PERSON_LIST_HEADER);
        for (int i = 0; i < personList.getSize(); i++) {
            String personName = personList.getPerson(i).getName();
            summaryString.append("\n ").append(i + ZERO_INDEXING_OFFSET).append(". ").append(personName);
        }
        return summaryString.toString();
    }

    /**
     * Returns a String object containing a summary of the state of the member attribute group.
     *
     * @return A String object containing the name of the Group object if group is not null, or
     *         a message stating that there is no group in the session, if group is null.
     */
    private String getGroupSummaryString() {
        if (group == null) {
            return "Group: None";
        } else {
            return "Group: " + group.getGroupName();
        }
    }

    /**
     * Returns a String object summarising the state of the Session object.
     * 
     * @return A String object containing a summary of the Session object.
     */
    public String getSessionSimplifiedString() {
        return sessionId + SUMMARY_STRING_SEPARATOR + sessionName + "\n "
                + SUMMARY_STRING_SEPARATOR + dateCreated.format(Parser.DATE_FORMAT)
                + SUMMARY_STRING_SEPARATOR + personList.getSize() + " participants"
                + SUMMARY_STRING_SEPARATOR + activityList.size() + " activities"
                + SUMMARY_STRING_SEPARATOR + getGroupSummaryString();
    }

    /**
     * Returns a String object describing the state of the Session object.
     *
     * @return A String object containing a description of the Session object and its member attributes.
     */
    @Override
    public String toString() {
        return "Session Id #" + sessionId + " --\n"
                + "Name: " + sessionName + '\n'
                + "Date: " + dateCreated.format(Parser.DATE_FORMAT) + '\n'
                + getActivityListSummaryString() + '\n'
                + getPersonListSummaryString() + '\n'
                + getGroupSummaryString();
    }
}
