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

    public int getEmptySeats() {
        return capacity - sold;
    }

    public void incrementSold() throws ArcsException {
        if (getEmptySeats() <= 0) {
            throw new ArcsException("No empty seats available. Error in incrementing seats.");
        }
        sold++;
    }

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
