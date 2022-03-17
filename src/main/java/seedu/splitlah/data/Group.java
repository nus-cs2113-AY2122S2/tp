package seedu.splitlah.data;

import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.ui.Message;

import java.util.ArrayList;

/**
 * Represents a group of people.
 *
 * @author Tianle
 */
public class Group {

    private ArrayList<Person> personList;
    private String groupName;
    private int groupId;

    private static final String PERSON_LIST_HEADER = "Participants";
    private static final int OFFSET = 1;

    /**
     * Constructs a Group object.
     *
     * @param personList  An ArrayList object containing Person objects representing
     *                    participants of the group.
     * @param groupName   A String object that represents the group's name.
     * @param groupId     An integer that uniquely identifies a group.
     */
    public Group(ArrayList<Person> personList, String groupName, int groupId) {
        this.personList = personList;
        this.groupName = groupName;
        this.groupId = groupId;
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
     * Returns a list of Person objects representing the participants in the group.
     *
     * @return An ArrayList object containing Person objects in the group.
     */
    public ArrayList<Person> getPersonList() {
        return personList;
    }

    /**
     * Checks whether the Group object has at least one participant.
     *
     * @return  true if the Group object has at least one participant,
     *          false otherwise.
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
        personList.add(person);
    }

    /**
     * Checks if a Person object is contained in the Group object.
     *
     * @param person  A Person object.
     * @return  true if a particular Person object is in the Group object,
     *          false otherwise.
     */
    public boolean hasPerson(Person person) {
        if (personList.isEmpty()) {
            return false;
        }

        for (Person personInGroup : personList) {
            if (personInGroup.getName().equals(person.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a Person object from the Group object.
     *
     * @param person A Person object.
     * @throws InvalidDataException If the Person object is not in the Group object.
     */
    public void removePerson(Person person) throws InvalidDataException {
        if (personList.isEmpty()) {
            throw new InvalidDataException(Message.ERROR_GROUP_EMPTY_PERSON_LIST);
        }
        Person deleteTarget = null;
        for (Person personInGroup : personList) {
            if (personInGroup.getName().equals(person.getName())) {
                deleteTarget = personInGroup;
                break;
            }
        }
        if (deleteTarget == null) {
            throw new InvalidDataException(Message.ERROR_GROUP_PERSON_NOT_IN_LIST);
        }
        personList.remove(deleteTarget);
    }

    /**
     * Returns the number of participants in the group.
     *
     * @return  An integer object that represents that total number of participants in the group.
     */
    public int getPersonCount() {
        return personList.size();
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

        StringBuilder outputString = new StringBuilder(PERSON_LIST_HEADER);
        for (int i = 0; i < personList.size(); i++) {
            String personName = personList.get(i).getName();
            outputString.append("\n ").append(i + OFFSET).append(". ").append(personName);
        }
        return outputString.toString();
    }

}
