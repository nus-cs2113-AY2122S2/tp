package seedu.duke.housekeeperperformancelists;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        sortHousekeeperPerformances();
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

    /**
     * Returns a performance within the list of housekeeper performances at the given index.
     *
     * @param index The index of the item within the list that would be returned.
     * @return The index returned by the .
     */
    public HousekeeperPerformance getPerformance(int index) {
        ArrayList<HousekeeperPerformance> performanceList = getHousekeeperPerformanceList();
        HousekeeperPerformance performance = performanceList.get(index);
        return performance;
    }

    /**
     * Checks if the housekeeper with the specified name already has a recorded performance rating.
     * @param housekeeperName The name of the specified housekeeper.
     * @return true if the Housekeeper already has a recorded performance rating, false otherwise.
     */
    public boolean isHousekeeperInPerformanceList(String housekeeperName) {
        for (HousekeeperPerformance housekeeperPerformance : housekeeperPerformanceList) {
            if (housekeeperName.equals(housekeeperPerformance.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Pretty-prints the list of HousekeeperPerformance objects. Displays the name and rating associated with
     * each HousekeeperPerformance object.
     */
    public void viewPerformances() {
        if (getSize() == 0) {
            System.out.println("There have not been any housekeeper performances recorded yet.");
        } else {
            System.out.println("======== Housekeeper Performance List ========");
            int number = 1;
            for (HousekeeperPerformance housekeeperPerformance : housekeeperPerformanceList) {
                System.out.println(Integer.toString(number) + ". [ " + housekeeperPerformance.getName().toUpperCase()
                        + " ]: " + housekeeperPerformance.getRating());
                number += 1;
            }
            System.out.println("============= End of the list =============");
        }
    }

    /**
     * Sorts the HousekeeperPerformance objects based on rating in descending order.
     */
    public void sortHousekeeperPerformances() {
        Collections.sort(housekeeperPerformanceList, new Comparator<HousekeeperPerformance>() {
            @Override
            public int compare(HousekeeperPerformance a, HousekeeperPerformance b) {
                return a.getRating() > b.getRating() ? -1 : (a.getRating() > b.getRating() ? 1 : 0);
            }
        });
    }

    public void clearHousekeeperPerformanceList() {
        housekeeperPerformanceList.clear();
    }
}
