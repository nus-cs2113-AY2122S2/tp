//@@author teanweijun

package seedu.planitarium.person;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.category.Category;
import seedu.planitarium.global.Constants;

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
        logger.log(Level.INFO, infoString);
    }

    /**
     * Returns the array list containing persons added.
     *
     * @return The array list
     */
    public ArrayList<Person> getPersonList() {
        String infoString = "Method getPersonList() called";
        logger.log(Level.INFO, infoString);
        return personList;
    }

    public int getListIndex(int personIndex) {
        String infoString = "Entering getListIndex()";
        logger.log(Level.INFO, infoString);
        assert (personIndex >= SINGULAR);
        assert (personIndex <= numberOfMembers);
        infoString = "Index assertions passed in getListIndex()";
        return personIndex - 1;
    }

    /**
     * Returns the person specified by the given index.
     *
     * @param personIndex The index of the person
     * @return The person with the index
     */
    public Person getPerson(int personIndex) {
        String infoString = "Method getPerson() called";
        logger.log(Level.INFO, infoString);
        int listIndex = getListIndex(personIndex);
        return personList.get(listIndex);
    }

    /**
     * Returns the number of members in the list.
     *
     * @return The number of person objects present in the array list
     */
    public int getNumberOfMembers() {
        String infoString = "Method getNumberOfMembers() called";
        logger.log(Level.INFO, infoString);
        return numberOfMembers;
    }

    /**
     * Adds a person to the array list.
     *
     * @param name The name of the person to be added
     * @param isSilent Whether to print confirmation
     */
    public void addPerson(String name, boolean isSilent) {
        String infoString = "Entering addPerson()";
        logger.log(Level.INFO, infoString);
        assert (name != null);
        infoString = "Non-null assertion passed in addPerson()";
        logger.log(Level.INFO, infoString);
        Person person = new Person(name);
        personList.add(person);
        numberOfMembers++;
        if (isSilent) {
            return;
        }
        System.out.println(name + " has been successfully added");
    }

    /**
     * Removes a person from the array list.
     *
     * @param personIndex The index of the person to be removed
     */
    public void deletePerson(int personIndex) {
        String infoString = "Method deletePerson() called";
        logger.log(Level.INFO, infoString);
        String name = getPerson(personIndex).getName();
        int listIndex = getListIndex(personIndex);
        personList.remove(listIndex);
        numberOfMembers--;
        System.out.println(name + " has been successfully removed");
    }

    /**
     * Returns the total remaining disposable income of persons in the array list.
     *
     * @return The total disposable income
     */
    public double getRemain() {
        String infoString = "Method getRemain() called";
        logger.log(Level.INFO, infoString);
        double sum = 0;
        for (Person person: getPersonList()) {
            sum += person.getDisposable();
        }
        return sum;
    }

    public double getTotalIncome() {
        String infoString = "Method getTotalIncome() called";
        logger.log(Level.INFO, infoString);
        double sum = 0;
        for (Person person: getPersonList()) {
            sum += person.getTotalIncome();
        }
        return sum;
    }

    public double getTotalExpenditure() {
        String infoString = "Method getTotalExpend() called";
        logger.log(Level.INFO, infoString);
        double sum = 0;
        for (Person person: getPersonList()) {
            sum += person.getTotalExpenditure();
        }
        return sum;
    }

    /**
     * Lists the names of everyone in the array list and their list of income and expenditure.
     */
    public void list() {
        String infoString = "Method list() called";
        logger.log(Level.INFO, infoString);
        for (int i = 1; i <= numberOfMembers; i++) {
            Person person = getPerson(i);
            System.out.println(i + ". " + person.getName());
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
        logger.log(Level.INFO, infoString);
        getPerson(personIndex).addIncome(description, amount, isPermanent, Constants.FOR_USER);
    }

    /**
     * Removes an income from the list of incomes of the specified person.
     *
     * @param personIndex The index of the person
     * @param incomeIndex The index of the income to be removed
     */
    public void deleteIncome(int personIndex, int incomeIndex) {
        String infoString = "Method deleteIncome() called";
        logger.log(Level.INFO, infoString);
        getPerson(personIndex).deleteIncome(incomeIndex);
    }

    /**
     * Adds an expenditure to the list of expenditures of the specified person.
     *
     * @param personIndex The index of the person
     * @param description The reason for the expenditure
     * @param amount The value of the expenditure
     * @param isPermanent Whether the expenditure is recurring
     * @param category The category of the expenditure
     */
    public void addExpend(int personIndex, String description, double amount, boolean isPermanent, int category) {
        String infoString = "Method addExpend() called";
        logger.log(Level.INFO, infoString);
        getPerson(personIndex).addExpend(description, amount, isPermanent, Constants.FOR_USER, category);
    }

    /**
     * Removes an expenditure from the list of expenditures of the specified person.
     *
     * @param personIndex The index of the person
     * @param expendIndex The index of the income to be removed
     */
    public void deleteExpend(int personIndex, int expendIndex) {
        String infoString = "Method deleteIncome() called";
        logger.log(Level.INFO, infoString);
        getPerson(personIndex).deleteIncome(expendIndex);
    }

    /**
     * Returns the number of incomes of the specified person.
     *
     * @param personIndex The index of the person
     * @return The number of incomes of the person
     */
    public int getNumberOfIncomes(int personIndex) {
        String infoString = "Method getNumberOfIncomes() called";
        logger.log(Level.INFO, infoString);
        return getPerson(personIndex).getNumberOfIncomes();
    }

    /**
     * Returns the number of expenditures of the specified person.
     *
     * @param personIndex The index of the person
     * @return The number of incomes of the person
     */
    public int getNumberOfExpenditures(int personIndex) {
        String infoString = "Method deleteIncome() called";
        logger.log(Level.INFO, infoString);
        return getPerson(personIndex).getNumberOfExpenditures();
    }

    /**
     *  Edits an income in the list of incomes of the specified person.
     *
     * @param personIndex The index of the person
     * @param incomeIndex The index of the income
     * @param description The source of the income
     * @param amount The value of the income
     * @param isPermanent Whether the income is recurring
     */
    public void editIncome(int personIndex, int incomeIndex, String description, double amount, boolean isPermanent) {
        String infoString = "Method editIncome() called";
        logger.log(Level.INFO, infoString);
        getPerson(personIndex).editIncome(incomeIndex, description, amount, isPermanent);
    }

    /**
     *  Edits an expenditure in the list of expenditures of the specified person.
     *
     * @param personIndex The index of the person
     * @param expendIndex The index of the expenditure
     * @param description The reason for the expenditure
     * @param amount The value of the expenditure
     * @param category The category of the expenditure
     * @param isPermanent Whether the expenditure is recurring
     */
    public void editExpend(int personIndex, int expendIndex, String description, double amount, int category,
                           boolean isPermanent) {
        String infoString = "Method editExpend() called";
        logger.log(Level.INFO, infoString);
        getPerson(personIndex).editExpend(expendIndex, description, amount, category, isPermanent);
    }
}
