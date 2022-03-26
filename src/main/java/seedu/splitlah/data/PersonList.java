package seedu.splitlah.data;

import seedu.splitlah.ui.Message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a list of Person objects.
 *
 * @author Roy
 */
public class PersonList implements Serializable {
    private ArrayList<Person> personList;

    /**
     * Initializes a PersonList object.
     */
    public PersonList() {
        this.personList = new ArrayList<>();
    }

    /**
     * Returns the size of the ArrayList object of Person objects.
     *
     * @return An integer that presents the size of the ArrayList object of Person objects.
     */
    public int getSize() {
        return personList.size();
    }

    /**
     * Checks if ArrayList object of Person objects is empty.
     * @return true if ArrayList object of Person objects is empty,
     *         otherwise false.
     */
    public boolean isEmpty() {
        return personList.isEmpty();
    }

    /**
     * Returns the Person object in the ArrayList object of Person objects with the specified index.
     *
     * @param index An integer representing the index.
     * @return A Person object specified by its index in the ArrayList object.
     */
    public Person getPerson(int index) {
        return personList.get(index);
    }

    /**
     * Adds a Person object into the ArrayList object of Person objects if it does not exist,
     * otherwise Person object is not added.
     *
     * @param person A Person object to be added.
     */
    public void addPerson(Person person) {
        if (!personList.contains(person)) {
            personList.add(person);
        }
    }

    /**
     * Removes a Person object from the ArrayList object of Person objects if it exist,
     * otherwise Person object is not removed.
     *
     * @param person A Person object to be removed.
     */
    public void removePerson(Person person) {
        if (personList.contains(person)) {
            personList.remove(person);
        }
    }

    /**
     * Returns the ArrayList object of Person objects.
     *
     * @return An ArrayList object of Person objects.
     */
    public ArrayList<Person> getPersonList() {
        return personList;
    }

    /**
     * Converts a String array object of names to a list of Person objects.
     *
     * @param personNames An array of String objects of names.
     */
    public void convertToPersonList(String[] personNames) {
        for (String name : personNames) {
            Person newPerson = new Person(name);
            addPerson(newPerson);
        }
    }

    /**
     * Merges an ArrayList object of Person objects with existing ArrayList object of Person objects.
     *
     * @param groupPersonList An ArrayList object of Person objects.
     */
    public void mergeListOfPersons(ArrayList<Person> groupPersonList) {
        for (Person person : groupPersonList) {
            addPerson(person);
        }
    }

    /**
     * Checks if String array object of names has duplicated names.
     *
     * @param personNames An array of String objects of names.
     * @return true if it contains duplicates,
     *         false otherwise.
     */
    public static boolean hasNameDuplicates(String[] personNames) {
        Set<String> nameSet = new HashSet<>();
        for (String name : personNames) {
            String nameToBeAdded = name.toLowerCase();
            if (!nameSet.add(nameToBeAdded)) {
                return true;
            }
        }
        assert nameSet.size() == personNames.length :
                Message.ASSERT_PERSONLIST_NAME_DUPLICATE_EXISTS_BUT_NOT_DETECTED;
        return false;
    }
}
