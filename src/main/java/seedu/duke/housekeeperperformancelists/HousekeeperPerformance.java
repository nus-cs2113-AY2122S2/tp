package seedu.duke.housekeeperperformancelists;

/**
 * Records the name and performance rating of a Housekeeper. Performance rating is an integer from 1-5, inclusive.
 */
public class HousekeeperPerformance {
    private String name;
    private int rating;

    public HousekeeperPerformance(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "[ " + getName() + " ]: " + getRating();
    }
}
