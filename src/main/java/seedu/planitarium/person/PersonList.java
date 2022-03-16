package seedu.planitarium.person;

import seedu.planitarium.ProjectLogger;

import java.util.ArrayList;
import java.util.logging.Level;

public class PersonList {
    private ArrayList<Person> personList;
    private int numberOfMembers;
    private ProjectLogger logger;
    private static final int SINGULAR = 1;

    /**
     * Constructs a new PersonList object.
     */
    public PersonList() {
        personList = new ArrayList<>();
        numberOfMembers = 0;
        logger = new ProjectLogger(PersonList.class.getName(), "PersonList.log");
        String infoString = "Logger for PersonList initialised";
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
    public void removePerson(int index) {
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
     * Prints the total remaining disposable income of persons in the array list.
     */
    public void printRemain() {
        String infoString = "Method getRemain() called";
        logger.getLogger().log(Level.INFO, infoString);
        double sum = 0;
        for (Person person: this.getPersonList()) {
            sum += person.getDisposable();
        }
        System.out.println("Your family has a remaining balance of $" + sum);
    }

    /**
     * Lists the names of everyone in the array list, followed by their list of income and expenditure.
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
}
