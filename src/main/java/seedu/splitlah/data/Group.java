package seedu.splitlah.data;

import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.ui.Message;

import java.util.ArrayList;


public class Group {

    private ArrayList<Person> personList;
    private String groupName;
    private int groupId;

    public Group(ArrayList<Person> personList, String groupName, int groupId) {
        this.personList = personList;
        this.groupName = groupName;
        this.groupId = groupId;
    }

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
