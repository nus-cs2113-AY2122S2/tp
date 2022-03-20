package seedu.splitlah.data;

import java.util.ArrayList;

/**
 * Represents a list of Person objects.
 *
 * @author Roy
 */
public class PersonList {
    private ArrayList<Person> personList;

    public PersonList() {
        this.personList = new ArrayList<>();
    }

    /**
     * Returns the size of the ArrayList of Person objects.
     *
     * @return An integer that presents the size of the ArrayList of Person objects.
     */
    public int getSize() {
        return personList.size();
    }

    /**
     * Returns true if the ArrayList of Person objects is empty,
     * otherwise false.
     * @return
     */
    public boolean isEmpty() {
        return personList.isEmpty();
    }

    /**
     * Returns the Person object in the ArrayList of Person objects with the specified index.
     *
     * @param index An integer representing the index.
     * @return A Person object.
     */
    public Person getPerson(int index) {
        return personList.get(index);
    }

    /**
     * Adds a Person object into the ArrayList of Person objects on if it does not exist,
     * otherwise Person object is not added.
     *
     * @param person A Person object.
     */
    public void addPerson(Person person) {
        if (!personList.contains(person)) {
            personList.add(person);
        }
    }

    /**
     * Returns the ArrayList of Person objects.
     *
     * @return An ArrayList of Person objects.
     */
    public ArrayList<Person> getPersonList() {
        return personList;
    }

    /**
     * Converts a String object array of names to a list of Person objects.
     *
     * @param personNames An ArrayList of Person objects.
     */
    public void convertToPersonList(String[] personNames) {
        for (String name : personNames) {
            Person newPerson = new Person(name);
            addPerson(newPerson);
        }
    }

    /**
     * Merges an ArrayList of Person objects with existing ArrayList of Person objects.
     *
     * @param groupPersonList An ArrayList of Person objects.
     */
    public void mergeListOfPersons(ArrayList<Person> groupPersonList) {
        for (Person person : groupPersonList) {
            addPerson(person);
        }
    }
}
