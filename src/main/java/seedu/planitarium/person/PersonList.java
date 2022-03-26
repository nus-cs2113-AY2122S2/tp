//@@author teanweijun

package seedu.planitarium.person;

import seedu.planitarium.ProjectLogger;

import java.util.ArrayList;
import java.util.logging.Level;

public class PersonList {
    private ArrayList<Person> personList;
    private int numberOfMembers;
    private static ProjectLogger logger = new ProjectLogger(PersonList.class.getName(), "PersonList.log");
    private static final int SINGULAR = 1;

    /**
     * Constructs a new PersonList object.
     */
    public PersonList() {
        personList = new ArrayList<>();
        numberOfMembers = 0;
        String infoString = "New PersonList constructed";
        logger.getLogger().log(Level.INFO, infoString);
    }

    /**
     * Returns the array list containing persons added.
     *
     * @return The array list
     */
    public ArrayList<Person> getPersonList() {
        String infoString = "Method getPersonList() called";
        logger.getLogger().log(Level.INFO, infoString);
        return personList;
    }

    /**
     * Returns the person specified by the given index.
     *
     * @param index The index of the person
     * @return The person with the index
     */
    public Person getPerson(int index) {
        String infoString = "Entering getPerson()";
        logger.getLogger().log(Level.INFO, infoString);
        assert (index >= SINGULAR);
        assert (index <= numberOfMembers);
        infoString = "Index assertions passed in getPerson()";
        logger.getLogger().log(Level.INFO, infoString);
        int listIndex = index - 1;
        return personList.get(listIndex);
    }

    /**
     * Returns the number of members in the list.
     *
     * @return The number of person objects present in the array list
     */
    public int getNumberOfMembers() {
        String infoString = "Method getNumberOfMembers() called";
        logger.getLogger().log(Level.INFO, infoString);
        return numberOfMembers;
    }

    /**
     * Adds a person to the array list.
     *
     * @param name The name of the person to be added
     */
    public void addPerson(String name) {
        String infoString = "Entering addPerson()";
        logger.getLogger().log(Level.INFO, infoString);
        assert (name != null);
        infoString = "Non-null assertion passed in addPerson()";
        logger.getLogger().log(Level.INFO, infoString);
        Person person = new Person(name);
        personList.add(person);
        numberOfMembers++;
        System.out.println(name + " has been successfully added");
    }

    /**
     * Removes a person from the array list.
     *
     * @param index The index of the person to be removed
     */
    public void deletePerson(int index) {
        String infoString = "Entering removePerson()";
        logger.getLogger().log(Level.INFO, infoString);
        assert (index >= SINGULAR);
        assert (index <= numberOfMembers);
        infoString = "Index assertions passed in removePerson()";
        logger.getLogger().log(Level.INFO, infoString);
        int listIndex = index - 1;
        String name = personList.get(listIndex).getName();
        personList.remove(listIndex);
        numberOfMembers--;
        System.out.println(name + " has been successfully removed");
    }

    /**
     * Returns the total remaining disposable income of persons in the array list.
     *
     * @return Total disposable income
     */
    public double getRemain() {
        String infoString = "Method getRemain() called";
        logger.getLogger().log(Level.INFO, infoString);
        double sum = 0;
        for (Person person: this.getPersonList()) {
            sum += person.getDisposable();
        }
        return sum;
    }

    /**
     * Lists the names of everyone in the array list and their list of income and expenditure.
     */
    public void list() {
        String infoString = "Method list() called";
        logger.getLogger().log(Level.INFO, infoString);
        for (int i = 0; i < numberOfMembers; i++) {
            Person person = personList.get(i);
            System.out.println((i + 1) + ". " + person.getName());
            person.listIncome();
            person.listExpenditure();
        }
    }

    /**
     * Adds an income to the list of incomes of the specified person.
     *
     * @param personIndex The index of the person
     * @param description The source of the income
     * @param amount The value of the income
     * @param isPermanent Whether the income is recurring
     */
    public void addIncome(int personIndex, String description, double amount, boolean isPermanent) {
        String infoString = "Method addIncome() called";
        logger.getLogger().log(Level.INFO, infoString);
        getPerson(personIndex).addIncome(description, amount, isPermanent);
    }

    /**
     * Removes an income from the list of incomes of the specified person.
     *
     * @param personIndex The index of the person
     * @param incomeIndex The index of the income to be removed
     */
    public void deleteIncome(int personIndex, int incomeIndex) {
        String infoString = "Method deleteIncome() called";
        logger.getLogger().log(Level.INFO, infoString);
        getPerson(personIndex).deleteIncome(incomeIndex);
    }

    /**
     * Adds an expenditure to the list of expenditures of the specified person.
     *
     * @param personIndex The index of the person
     * @param description The reason for the expenditure
     * @param amount The value of the expenditure
     * @param isPermanent Whether the expenditure is recurring
     */
    public void addExpend(int personIndex, String description, double amount, boolean isPermanent) {
        String infoString = "Method addIncome() called";
        logger.getLogger().log(Level.INFO, infoString);
        getPerson(personIndex).addIncome(description, amount, isPermanent);
    }

    /**
     * Removes an expenditure from the list of expenditures of the specified person.
     *
     * @param personIndex The index of the person
     * @param expendIndex The index of the income to be removed
     */
    public void deleteExpend(int personIndex, int expendIndex) {
        String infoString = "Method deleteIncome() called";
        logger.getLogger().log(Level.INFO, infoString);
        getPerson(personIndex).deleteIncome(expendIndex);
    }
}
