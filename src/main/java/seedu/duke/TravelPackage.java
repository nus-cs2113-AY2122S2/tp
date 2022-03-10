package seedu.duke;
import java.util.Date;

public class TravelPackage {
    private String name; 
    private int id; 
    private Date[] period; //[startDate,endDate]
    private String hotel; 
    private double price; 
    private String country; 
    private int maxParticipants; 
    private int numParticipants;

    public TravelPackage(String name, int id, Date startDate, Date endDate, String hotel, double price, String country, int maxParticipants) {
        this.name = name;
        this.id = id;
        // this.period = [startDate,endDate];
        this.hotel = hotel;
        this.price = price;
        this.country = country;
        this.maxParticipants = maxParticipants;
        this.numParticipants = 0; 

    }
    
    public boolean isFull() { 
        if (this.numParticipants < maxParticipants) { 
            return false;
        }
        else {
            return true; 
        }
    }

    public int getID() {
        return this.id;
    }
    
    public void printInfo() {
        System.out.println("Here are the details for " + this.name + ", Travel Package ID of "+ this.id);
        System.out.println("Country: "+ this.country);
        System.out.println("Price: " +  this.price);
        System.out.println("Hotel: " + this.hotel);
    }

    


    




}