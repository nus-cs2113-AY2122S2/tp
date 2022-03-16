package seedu.duke;

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


}
