package seedu.duke;

import java.util.Date;

public class TravelPackage {
    public static final String EXAMPLENAME = "Switzerland - Conquer Summits";
    public static final String EXAMPLESTART = "";
    public static final String EXAMPLEEND = "";
    public static final String EXAMPLEHOTEL = "EXAMPLE HOTEL";
    public static final double EXAMPLEPRICE = 99.99;
    public static final String EXAMPLECOUNTRY = "Switzerland";
    public static final int EXAMPLEMAX = 10;

    private final String name;
    private static int nextId = 1;
    private final int id;
    private Date[] period; // [startDate,endDate]
    private final String startDate;
    private final String endDate;
    private final String hotel;
    private final double price;
    private final String country;
    private final int maxParticipants;
    private final int numParticipants;

    public TravelPackage(String name, String startDate, String endDate,
            String hotel, double price, String country, int maxParticipants) {
        this.name = name;
        // this.period = [startDate,endDate];
        this.startDate = startDate;
        this.endDate = endDate;
        this.hotel = hotel;
        this.price = price;
        this.country = country;
        this.maxParticipants = maxParticipants;
        this.numParticipants = 0;
        id = nextId++;
    }

    public boolean isFull() {
        return this.numParticipants >= maxParticipants;
    }

    public String getID() {
        return "P" + this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public String getHotel() {
        return this.hotel;
    }

    public double getPrice() {
        return this.price;
    }

    public String getCountry() {
        return this.country;
    }

    public int getMaxParticipants() {
        return this.maxParticipants;
    }

    public String toString() {
        return "Here are the details for "
                + this.name + ", Travel Package ID of "
                + this.getID() + "\nCountry: "
                + this.country + "\nPrice: "
                + this.price + "\nHotel: "
                + this.hotel;
    }

    public String toSave() {
        return name + " | " + Integer.toString(nextId) + " | " + Integer.toString(id) + " | " + // startDate, endDate
                hotel + " | " + Double.toString(price) + " | " + country + " | " + Integer.toString(maxParticipants)
                + " | " + Integer.toString(numParticipants);
    }

}