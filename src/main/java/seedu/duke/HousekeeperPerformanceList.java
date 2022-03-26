package seedu.duke;

import java.util.ArrayList;

/**
 * Represents a data structure containing all housekeeper performance ratings.
 */
public class HousekeeperPerformanceList {
    private ArrayList<HousekeeperPerformance> housekeeperPerformanceList;

    public HousekeeperPerformanceList() {
        ArrayList<HousekeeperPerformance> housekeeperPerformanceList = new ArrayList<>();
        setHousekeeperPerformanceList(housekeeperPerformanceList);
    }

    public void addHousekeeperPerformance(HousekeeperPerformance newHousekeeperPerformance) {
        getHousekeeperPerformanceList().add(newHousekeeperPerformance);
    }

    public ArrayList<HousekeeperPerformance> getHousekeeperPerformanceList() {
        return housekeeperPerformanceList;
    }

    public void setHousekeeperPerformanceList(ArrayList<HousekeeperPerformance> housekeeperPerformanceList) {
        this.housekeeperPerformanceList = housekeeperPerformanceList;
    }

    public int getSize() {
        return housekeeperPerformanceList.size();
    }

    public void viewPerformances() {
        int number = 1;
        if (getSize() == 0) {
            System.out.println("There are no recorded satisfactions.");
        }
        for (HousekeeperPerformance housekeeperPerformance : housekeeperPerformanceList) {
            System.out.println(Integer.toString(number) + ". " + housekeeperPerformance.getName() + " - "
                    + housekeeperPerformance.getRating());
            number += 1;
        }
    }

    // Method for sorting
    // Method for displaying
}
