package seedu.duke;

import java.util.Date;

public class TravelPackage {
    private final String name;
    private static int nextId = 1;
    private final int id;
    private Date[] period; //[startDate,endDate]
    private final String hotel;
    private final double price;
    private final String country;
    private final int maxParticipants;
    private final int numParticipants;

    public TravelPackage(String name, Date startDate, Date endDate,
                         String hotel, double price, String country, int maxParticipants) {
        this.name = name;
        // this.period = [startDate,endDate];
        this.hotel = hotel;
        this.price = price;
        this.country = country;
        this.maxParticipants = maxParticipants;
        this.numParticipants = 0;
        id = nextId++;
    }
    
    public boolean isFull() {
        return this.numParticipants >=  maxParticipants;
    }

    public String getID() {
        return "P" + this.id;
    }

    public String toString() {
        return "Here are the details for "
                + this.name + ", Travel Package ID of "
                + this.getID() + "\nCountry: "
                + this.country + "\nPrice: "
                +  this.price + "\nHotel: "
                + this.hotel;
    }


    


    




}