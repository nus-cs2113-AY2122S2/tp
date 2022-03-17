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


    /**
     * Constructs a Group object.
     *
     * @param personList  A list of Person objects representing participants of the group.
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
     * Returns the group's Id.
     *
     * @return An integer object containing the Id of the group.
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
    
    public boolean isEmptyGroup() {
        return personList.isEmpty();
    }

    public void addPerson(Person person) {
        personList.add(person);
    }

    public boolean hasPerson(Person person) {
        if (personList.isEmpty()) {
            return false;
        }

        for (Person p : personList) {
            if (p.getName() == person.getName()) {
                return true;
            }
        }
        return false;
    }

    public void removePerson(Person person) throws InvalidDataException {
        if (personList.isEmpty()) {
            throw new InvalidDataException(Message.ERROR_GROUP_EMPTY_PERSON_LIST);
        }
        Person deleteTarget = null;
        for (Person p : personList) {
            if (p.getName() == person.getName()) {
                deleteTarget = p;
                break;
            }
        }
        if (deleteTarget == null) {
            throw new InvalidDataException(Message.ERROR_GROUP_PERSON_NOT_IN_LIST);
        }
    }

    @Override
    public String toString() {
        return "";
    }
}
