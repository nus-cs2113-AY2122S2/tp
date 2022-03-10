package seedu.planitarium.person;

import java.util.ArrayList;

public class PersonList {
    private static ArrayList<Person> personList;
    private static int numberOfMembers;

    /**
     * Constructs a new PersonList object.
     */
    public PersonList() {
        personList = new ArrayList<>();
        numberOfMembers = 0;
    }

    /**
     * Returns the number of members in the list.
     *
     * @return The number of person objects present in the array list
     */
    public static int getNumberOfMembers() {
        return numberOfMembers;
    }

    /**
     * Adds a person to the array list.
     *
     * @param name The name of the person to be added
     */
    public static void addPerson(String name) {
        Person person = new Person(name);
        personList.add(person);
        numberOfMembers++;
    }

    /**
     * Removes a person from the array list.
     *
     * @param index The index of the person to be removed
     */
    public static void removePerson(int index) {
        personList.remove(index - 1);
    }

    /**
     * Returns the total remaining disposable income of persons in the array list.
     *
     * @return The total remaining disposable income
     */
    public static double getRemain() {
        double sum = 0;
        for (Person person: personList) {
            sum += person.getDisposable();
        }
        return sum;
    }

    /**
     * Lists the names of everyone in the array list, followed by their list of income and expenditure.
     */
    public static void list() {
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
