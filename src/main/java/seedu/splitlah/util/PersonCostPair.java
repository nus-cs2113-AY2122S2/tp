package seedu.splitlah.util;

import seedu.splitlah.data.Person;

import java.util.Comparator;

/**
 * Represents a total cost that the person must pay or receive to settle the transactions for the session.
 * A negative value represents a debt to pay another person with a positive value.
 */
public class PersonCostPair implements Comparable<PersonCostPair> {

    private Person person;
    private double cost;
    private boolean isProcessed;

    public Person getPerson() {
        return person;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }

    public int compareTo(PersonCostPair personCostPair) {
        return Double.compare(cost, personCostPair.getCost());
    }

    public static Comparator<PersonCostPair> PersonCostPairComparator = new Comparator<PersonCostPair>() {
        @Override
        public int compare(PersonCostPair personCostPair1, PersonCostPair personCostPair2) {
            return personCostPair1.compareTo(personCostPair2);
        }
    };

    public PersonCostPair(Person person) {
        this.person = person;
        this.cost = person.getTotalCost();
        this.isProcessed = false;
    }
}
