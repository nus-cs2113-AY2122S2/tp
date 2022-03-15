package seedu.duke;

import java.util.ArrayList;

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


}
