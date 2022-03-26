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

    public ArrayList<HousekeeperPerformance> getHousekeeperPerformanceList() {
        return housekeeperPerformanceList;
    }

    public void setHousekeeperPerformanceList(ArrayList<HousekeeperPerformance> housekeeperPerformanceList) {
        this.housekeeperPerformanceList = housekeeperPerformanceList;
    }

    // Method for sorting
    // Method for displaying
}
