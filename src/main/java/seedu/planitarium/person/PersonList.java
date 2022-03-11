package seedu.planitarium.person;

import java.util.ArrayList;

public class PersonList {
    private ArrayList<Person> personList;
    private int numberOfMembers;

    /**
     * Constructs a new PersonList object.
     */
    public PersonList() {
        personList = new ArrayList<>();
        numberOfMembers = 0;
    }

    /**
     * Returns the array list containing persons added.
     *
     * @return The array list
     */
    public ArrayList<Person> getPersonList() {
        return personList;
    }

    /**
     * Returns the person specified by the given index.
     *
     * @param index The index of the person
     * @return The person with the index
     */
    public Person getPerson(int index) {
        return personList.get(index - 1);
    }

    /**
     * Returns the number of members in the list.
     *
     * @return The number of person objects present in the array list
     */
    public int getNumberOfMembers() {
        return numberOfMembers;
    }

    /**
     * Adds a person to the array list.
     *
     * @param name The name of the person to be added
     */
    public void addPerson(String name) {
        Person person = new Person(name);
        personList.add(person);
        numberOfMembers++;
    }

    /**
     * Removes a person from the array list.
     *
     * @param index The index of the person to be removed
     */
    public void removePerson(int index) {
        personList.remove(index - 1);
        numberOfMembers--;
    }

    /**
     * Returns the total remaining disposable income of persons in the array list.
     *
     * @return The total remaining disposable income
     */
    public double getRemain(PersonList personList) {
        double sum = 0;
        for (Person person: personList.getPersonList()) {
            sum += person.getDisposable();
        }
        return sum;
    }

    /**
     * Lists the names of everyone in the array list, followed by their list of income and expenditure.
     */
    public void list() {
        for (int i = 0; i < numberOfMembers; i++) {
            Person person = personList.get(i);
            System.out.println((i+1) + ". " + person.getName());
            System.out.println("Income: ");
            person.listIncome();
            System.out.println("Expenditure: ");
            person.listExpenditure();
        }
    }
}
