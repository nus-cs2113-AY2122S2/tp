package seedu.duke;

import java.util.ArrayList;
import java.util.Collections;

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
        if (getSize() == 0) {
            System.out.println("There have not been any housekeeper performances recorded yet.");
        } else {
            System.out.println("======== Housekeeper Performance List ========");
            int number = 1;
            for (HousekeeperPerformance housekeeperPerformance : housekeeperPerformanceList) {
                System.out.println(Integer.toString(number) + ". [ " + housekeeperPerformance.getName()
                        + " ]: " + housekeeperPerformance.getRating());
                number += 1;
            }
            System.out.println("============= End of the list =============");
        }
    }

    public void sortHousekeeperPerformances() {
        //Collections.sort(housekeeperPerformanceList, (firstPerformance, secondPerformance) -> firstPerformance.getRating() > secondPerformance.getRating());
    }
}
