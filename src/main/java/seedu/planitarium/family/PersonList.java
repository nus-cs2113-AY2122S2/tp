//@@author teanweijun

package seedu.planitarium.family;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.global.Constants;

import java.util.ArrayList;
import java.util.logging.Level;

public class PersonList {
    private final ArrayList<Person> personList;
    private int numberOfMembers;

    private static final String LOG_CLASS_NAME = PersonList.class.getSimpleName();
    private static final String LOG_FILE_PATH = LOG_CLASS_NAME + ".log";
    private static final ProjectLogger LOGGER = new ProjectLogger(LOG_CLASS_NAME, LOG_FILE_PATH);

    /**
     * Constructs a new PersonList object.
     */
    public PersonList() {
        personList = new ArrayList<>();
        numberOfMembers = 0;
        LOGGER.log(Level.INFO, Constants.PERSON_LIST_INIT_MESSAGE);
    }

    /**
     * Returns the array list containing persons added.
     *
     * @return The array list
     */
    public ArrayList<Person> getPersonList() {
        LOGGER.log(Level.INFO, Constants.GET_PERSON_LIST_CALL_MESSAGE);
        return personList;
    }

    public int getListIndex(int personIndex) {
        String infoString = "Entering getListIndex()";
        LOGGER.log(Level.INFO, infoString);
        assert (personIndex >= Constants.SINGULAR);
        assert (personIndex <= numberOfMembers);
        infoString = "Index assertions passed in getListIndex()";
        LOGGER.log(Level.INFO, infoString);
        return personIndex - 1;
    }

    /**
     * Returns the person specified by the given index.
     *
     * @param personIndex The index of the person
     * @return The person with the index
     */
    public Person getPerson(int personIndex) {
        LOGGER.log(Level.INFO, Constants.GET_PERSON_CALL_MESSAGE);
        int listIndex = getListIndex(personIndex);
        return personList.get(listIndex);
    }

    /**
     * Returns the number of members in the list.
     *
     * @return The number of person objects present in the array list
     */
    public int getNumberOfMembers() {
        LOGGER.log(Level.INFO, Constants.NUM_MEMBERS_CALL_MESSAGE);
        return numberOfMembers;
    }

    /**
     * Adds a person to the array list.
     *
     * @param name The name of the person to be added
     */
    public void addPerson(String name) {
        String infoString = "Entering addPerson()";
        LOGGER.log(Level.INFO, infoString);
        assert (name != null);
        infoString = "Non-null assertion passed in addPerson()";
        LOGGER.log(Level.INFO, infoString);
        Person person = new Person(name);
        personList.add(person);
        numberOfMembers++;
    }

    /**
     * Removes a person from the array list.
     *
     * @param personIndex The index of the person to be removed
     */
    public void deletePerson(int personIndex) {
        LOGGER.log(Level.INFO, Constants.DELETE_PERSON_CALL_MESSAGE);
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
    public Double getRemain() {
        LOGGER.log(Level.INFO, Constants.GET_REMAIN_CALL_MESSAGE);
        Double sum = 0.0;
        for (Person person: personList) {
            sum += person.getDisposable();
        }
        return sum;
    }

    public Double getTotalIncome() {
        LOGGER.log(Level.INFO, Constants.GET_TOTAL_INCOME_CALL_MESSAGE);
        Double sum = 0.0;
        for (Person person: personList) {
            sum += person.getTotalIncome();
        }
        return sum;
    }

    public Double getTotalExpenditure() {
        LOGGER.log(Level.INFO, Constants.GET_TOTAL_EXPEND_CALL_MESSAGE);
        Double sum = 0.0;
        for (Person person: personList) {
            sum += person.getTotalExpenditure();
        }
        return sum;
    }

    /**
     * Lists the names of everyone in the array list and their list of income and expenditure.
     */
    public void list() {
        LOGGER.log(Level.INFO, Constants.LIST_CALL_MESSAGE);
        for (int i = 1; i <= numberOfMembers; i++) {
            Person person = getPerson(i);
            System.out.println(i + ". " + person.getName());
            person.listIncome();
            person.listExpenditure();
            // Print newline between persons
            if (i != numberOfMembers) {
                System.out.println(Constants.EMPTY_STRING);
            }
        }
    }

    /**
     * Adds an income to the list of incomes of the specified person.
     *
     * @param personIndex The index of the person
     * @param description The source of the income
     * @param amount The value of the income
     * @param isPermanent Whether the income is recurring
     * @param isSilent Whether to print confirmation
     */
    public void addIncome(int personIndex, String description, Double amount, Boolean isPermanent, Boolean isSilent) {
        LOGGER.log(Level.INFO, Constants.ADD_INCOME_CALL_MESSAGE);
        getPerson(personIndex).addIncome(description, amount, isPermanent, isSilent);
    }

    /**
     * Removes an income from the list of incomes of the specified person.
     *
     * @param personIndex The index of the person
     * @param incomeIndex The index of the income to be removed
     */
    public void deleteIncome(int personIndex, int incomeIndex) {
        LOGGER.log(Level.INFO, Constants.DELETE_PERSON_CALL_MESSAGE);
        getPerson(personIndex).deleteIncome(incomeIndex);
    }

    /**
     * Adds an expenditure to the list of expenditures of the specified person.
     *
     * @param personIndex The index of the person
     * @param description The reason for the expenditure
     * @param amount The value of the expenditure
     * @param category The category of the expenditure
     * @param isPermanent Whether the expenditure is recurring
     * @param isSilent Whether to print confirmation
     */
    public void addExpend(int personIndex, String description, Double amount, Integer category, Boolean isPermanent,
                          Boolean isSilent) {
        LOGGER.log(Level.INFO, Constants.ADD_EXPEND_CALL_MESSAGE);
        getPerson(personIndex).addExpend(description, amount, category, isPermanent, isSilent);
    }

    /**
     * Removes an expenditure from the list of expenditures of the specified person.
     *
     * @param personIndex The index of the person
     * @param expendIndex The index of the income to be removed
     */
    public void deleteExpend(int personIndex, int expendIndex) {
        LOGGER.log(Level.INFO, Constants.DELETE_EXPEND_CALL_MESSAGE);
        getPerson(personIndex).deleteExpend(expendIndex);
    }

    /**
     * Returns the number of incomes of the specified person.
     *
     * @param personIndex The index of the person
     * @return The number of incomes of the person
     */
    public int getNumberOfIncomes(int personIndex) {
        LOGGER.log(Level.INFO, Constants.NUM_INCOMES_CALL_MESSAGE);
        return getPerson(personIndex).getNumberOfIncomes();
    }

    /**
     * Returns the number of expenditures of the specified person.
     *
     * @param personIndex The index of the person
     * @return The number of incomes of the person
     */
    public int getNumberOfExpenditures(int personIndex) {
        LOGGER.log(Level.INFO, Constants.NUM_EXPENDS_CALL_MESSAGE);
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
    public void editIncome(int personIndex, int incomeIndex, String description, Double amount, Boolean isPermanent) {
        LOGGER.log(Level.INFO, Constants.EDIT_INCOME_CALL_MESSAGE);
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
    public void editExpend(int personIndex, int expendIndex, String description, Double amount, Integer category,
                           Boolean isPermanent) {
        LOGGER.log(Level.INFO, Constants.EDIT_EXPEND_CALL_MESSAGE);
        getPerson(personIndex).editExpend(expendIndex, description, amount, category, isPermanent);
    }

    /**
     * Prints entries found in the category provided containing the stated description.
     *
     * @param description The string to look for
     * @param category The category of the entry
     */
    public void find(String description, Integer category) {
        LOGGER.log(Level.INFO, Constants.FIND_CALL_MESSAGE);
        for (Person person : personList) {
            person.find(description, category);
        }
    }
}
