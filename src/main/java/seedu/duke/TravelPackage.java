package seedu.duke;

import java.util.ArrayList;

public class TravelPackage {
    public static final String EXAMPLENAME = "Switzerland - Conquer Summits";
    public static final int EXAMPLEID = 999;
    public static final String EXAMPLESTART = "";
    public static final String EXAMPLEEND = "";
    public static final String EXAMPLEHOTEL = "EXAMPLE HOTEL";
    public static final double EXAMPLEPRICE = 99.99;
    public static final String EXAMPLECOUNTRY = "Switzerland";
    public static final int EXAMPLEMAX = 10;

    private final String name;
    private final int id;
    private final String startDate;
    private final String endDate;
    private final String hotel;
    private final double price;
    private final String country;
    private final int maxParticipants;
    private int numParticipants;
    private Reservations reservationList;

    public TravelPackage(String name, int id, String startDate, String endDate,
            String hotel, double price, String country, int maxParticipants) {
        this.name = name;
        this.id = id;
        // this.period = [startDate,endDate];
        this.startDate = startDate;
        this.endDate = endDate;
        this.hotel = hotel;
        this.price = price;
        this.country = country;
        this.maxParticipants = maxParticipants;
        this.numParticipants = 0;
        this.reservationList = new Reservations();
    }

    public boolean isFull() {
        return this.numParticipants >= maxParticipants;
    }

    public int getID() {
        return this.id;
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

    public Reservations getReservationList() {
        return this.reservationList;
    };

    public void setReservationList(Reservations r) {
        this.reservationList = r;
    }

    public String toString() {
        return "Here are the details for "
                + this.name + ", Travel Package ID of "
                + this.getID() + "\nCountry: "
                + this.country + "\nPrice: "
                + this.price + "\nHotel: "
                + this.hotel;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        TravelPackage cur = (TravelPackage) obj;

        return cur.getID() == this.getID() && cur.getName().equals(this.getName())
                && cur.getStartDate().equals(this.getStartDate())
                && cur.getEndDate().equals(this.getEndDate()) && cur.getHotel().equals(this.getHotel())
                && cur.getPrice() == this.getPrice() &&
                cur.getCountry().equals(this.getCountry()) && cur.getMaxParticipants() == this.getMaxParticipants();

    }

    // public String toSave() {
    // return name + " | " + Integer.toString(nextId) + " | " + Integer.toString(id)
    // + " | " + // startDate, endDate
    // hotel + " | " + Double.toString(price) + " | " + country + " | " +
    // Integer.toString(maxParticipants)
    // + " | " + Integer.toString(numParticipants);
    // }

}