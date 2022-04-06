package arcs.data.route;

import arcs.data.exception.ArcsException;

public class Route {
    private String flightID;
    private String date;
    private String time;
    private String from;
    private String to;
    private int capacity;
    private int sold;

    public Route(String flightID, String date, String time, String from, String to, int capacity) {
        this.flightID = flightID;
        this.date = date;
        this.time = time;
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        sold = 0;
    }

    public String getFlightID() {
        return flightID;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    /**
     * Gets the number of empty seats of this route.
     *
     * @return the number of empty seats.
     */
    public int getEmptySeats() {
        return capacity - sold;
    }

    /**
     * Increments the number of sold seats by 1.
     *
     * @throws ArcsException if the flight has zero or negative empty seats.
     */
    public void incrementSold() throws ArcsException {
        if (getEmptySeats() <= 0) {
            throw new ArcsException("No empty seats available. Error in incrementing seats.");
        }
        sold++;
    }

    /**
     * Decrement the number of sold seats.
     */
    public void decrementSold() {
        sold--;
        assert sold >= 0 : "Sold is less than 0.";
    }

    /**
     * Gets the information of a flight route.
     * @return a string describing the flight route information.
     */
    public String getFlightInfo() {
        String flightInfo = "Flight ID: " + flightID + System.lineSeparator()
                + "Departure Date: " + date + System.lineSeparator()
                + "Time: " + time + System.lineSeparator()
                + "From: " + from + System.lineSeparator()
                + "To: " + to + System.lineSeparator()
                + "Seats available: " + (capacity - sold);

        return flightInfo;
    }

    @Override
    public String toString() {
        String str = flightID + "/" + date + "/"
                + time + "/" + from + "/"
                + to + "/" + capacity;
        return str;
    }
}
