package seedu.duke.satisfactionlists;


import java.util.ArrayList;



/**
 * Represents a data structure containing multiple Satisfaction objects.
 * Uses an ArrayList to store the Satisfaction objects.
 */

public class SatisfactionList {
    private ArrayList<Satisfaction> satisfactionList;

    public SatisfactionList() {
        ArrayList<Satisfaction> satisfactionList = new ArrayList<>();
        setSatisfactionList(satisfactionList);
    }

    public void addSatisfaction(Satisfaction newSatisfaction) {
        getSatisfactionList().add(newSatisfaction);
    }

    public ArrayList<Satisfaction> getSatisfactionList() {
        return satisfactionList;
    }

    public void setSatisfactionList(ArrayList<Satisfaction> satisfactionList) {
        this.satisfactionList = satisfactionList;
    }

    public int getSize() {
        return satisfactionList.size();
    }

    /**
     * Returns a specific satisfaction within the satisfaction list.
     *
     * @param index The index of the satisfaction within the list that would be returned.
     * @return The satisfaction at the given index in the satisfaction list.
     */
    public Satisfaction getSatisfaction(int index) {
        ArrayList<Satisfaction> satisfactionList = getSatisfactionList();
        Satisfaction satisfaction = satisfactionList.get(index);
        return satisfaction;
    }

    /**
     * Helper for ViewSatisfactionCommand class.
     * Prints out the information of the Satisfaction objects
     * in this satisfactionList (i.e. prints out each Satisfaction object's
     * corresponding customer name and satisfaction value).
     */
    public void viewSatisfactions() {
        int number = 1;
        if (getSize() == 0) {
            System.out.println("There are no recorded satisfactions.");
        } else {
            System.out.println("======== Customer Satisfaction List ========");
            for (Satisfaction satisfaction : satisfactionList) {
                System.out.println(Integer.toString(number) + ". [ " + satisfaction.getCustomerName().toUpperCase()
                        + " ]: " + satisfaction.getSatisfactionValue());
                number += 1;
            }
            System.out.println("============= End of the list =============");
        }
    }

    /**
     * Helper for AddSatisfactionCommand class.
     * Check if the given customer already has a
     * corresponding Satisfaction object in this satisfactionList.
     * @param customerName The name of the given customer.
     * @return A boolean value (true if the customer already has corresponding Satisfaction object, false otherwise).
     */
    public boolean isCustomerInSatisfactionList(String customerName) {
        for (Satisfaction satisfaction : satisfactionList) {
            if (customerName.equals(satisfaction.getCustomerName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper for AverageSatisfactionCommand class.
     * Calculates the average customer satisfaction rating of
     * the Satisfaction objects in this satisfactionList.
     * @return The average customer satisfaction rating.
     */
    public double calculateAverageSatisfaction() {
        int satisfactionSum = 0;
        for (Satisfaction satisfaction : satisfactionList) {
            satisfactionSum += satisfaction.getSatisfactionValue();
        }
        double satisfactionAverage = (double) satisfactionSum / (double) getSize();
        return satisfactionAverage;
    }

    /**
     * Clears the list of satisfactions.
     */
    public void clearSatisfactionList() {
        satisfactionList.clear();
    }
}
