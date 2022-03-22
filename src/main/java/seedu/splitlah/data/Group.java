package seedu.splitlah.data;

import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.ui.Message;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a group of people.
 *
 * @author Tianle
 */
public class Group implements Serializable {

    private PersonList personList;
    private String groupName;
    private int groupId;

    private static final int OFFSET = 1;
    private static final String SEPARATOR = " | ";

    /**
     * Initializes a Group object.
     *
     * @param groupName  A String object that represents the group's name.
     * @param groupId    An integer that uniquely identifies a group.
     * @param personList An ArrayList object containing Person objects
     */
    public Group(String groupName, int groupId, PersonList personList) {
        this.groupName = groupName;
        this.groupId = groupId;
        this.personList = personList;
    }

    /**
     * Returns the group's name.
     *
     * @return A String object containing the name of the group.
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Returns the group's unique identifier.
     *
     * @return An integer object containing the id of the group.
     */
    public int getGroupId() {
        return groupId;
    }

    /**
     * Returns an ArrayList object containing Person objects representing the participants in the group.
     *
     * @return An ArrayList object containing Person objects in the group.
     */
    public ArrayList<Person> getPersonList() {
        return personList.getPersonList();
    }

    /**
     * Checks whether the Group object has at least one participant.
     *
     * @return true if the Group object has at least one participant,
     *         false otherwise.
     */
    public boolean isGroupEmpty() {
        return personList.isEmpty();
    }

    /**
     * Adds a Person object to the group.
     *
     * @param person A Person object.
     */
    public void addPerson(Person person) {
        personList.addPerson(person);
    }

    /**
     * Checks if a Person object is contained in the Group object.
     *
     * @param person A Person object.
     * @return true if a particular Person object is in the Group object,
     *         false otherwise.
     */
    public boolean hasPerson(Person person) {
        if (personList.isEmpty()) {
            return false;
        }

        for (Person personInGroup : personList.getPersonList()) {
            if (personInGroup.getName().equalsIgnoreCase(person.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a Person object represented by the name.
     *
     * @param personName A String object that represents the person's name.
     * @return A Person object represented by its name.
     * @throws InvalidDataException if the person is not in the Group object.
     */
    public Person getPersonFromGroup(String personName) throws InvalidDataException {
        if (personList.isEmpty()) {
            throw new InvalidDataException(Message.ERROR_GROUP_EMPTY_PERSON_LIST);
        }
        for (Person personInGroup : personList.getPersonList()) {
            if (personInGroup.getName().equalsIgnoreCase(personName)) {
                return personInGroup;
            }
        }
        throw new InvalidDataException(Message.ERROR_GROUP_PERSON_NOT_IN_LIST);
    }

    /**
     * Removes a Person object from the Group object.
     *
     * @param personName A String object that represents the person's name.
     * @throws InvalidDataException If the Person is not in the Group object.
     */
    public void removePerson(String personName) throws InvalidDataException {
        Person personToBeRemoved = getPersonFromGroup(personName);
        personList.removePerson(personToBeRemoved);
    }

    /**
     * Returns the number of participants in the group.
     *
     * @return An integer object that represents that total number of participants in the group.
     */
    public int getPersonCount() {
        return personList.getSize();
    }

    /**
     * Returns a String object that contains groupId, groupName, and the number of persons involved in the group.
     *
     * @return A String object which summarises the group.
     */
    public String getGroupSummary() {
        return groupId + SEPARATOR + groupName + SEPARATOR + getPersonCount()
            + (getPersonCount() > 1 ? " persons" : " person");
    }

    /**
     * Returns a String object describing the Group object.
     *
     * @return A String object containing a description of the Group object.
     */
    @Override
    public String toString() {
        if (personList.isEmpty()) {
            return Message.ERROR_GROUP_EMPTY_PERSON_LIST;
        }

        StringBuilder outputString = new StringBuilder("Group Id ");
        outputString.append("#").append(groupId).append("  --").append("\n").append("Participants: ");
        for (int i = 0; i < personList.getSize(); i++) {
            String personName = personList.getPerson(i).getName();
            outputString.append("\n ").append(i + OFFSET).append(". ").append(personName);
        }
        return outputString.toString();
    }

}
