package ARCS.data;

public class Route {
    private int flightID;
    private String date;
    private String time;
    private String from;
    private String to;
    private int capacity;
    private int sold;

    public Route(int flightID, String date, String time, String from, String to, int capacity) {
        this.flightID = flightID;
        this.date = date;
        this.time = time;
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        sold = 0;
    }

    public int getFlightID() {
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

    public String getFlightInfo() {
        String flightInfo = "Flight ID: " + flightID + System.lineSeparator()
                + "Departure Date: " + date + System.lineSeparator()
                + "Time: " + time + System.lineSeparator()
                + "From: " + from + System.lineSeparator()
                + "To: " + to + System.lineSeparator()
                + "Seats available: " + (capacity - sold);

        return flightInfo;
    }
}
