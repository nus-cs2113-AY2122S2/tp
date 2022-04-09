package seedu.duke;

public class Hotel {

    public static final String EXAMPLEHOTELID = "456";
    public static final String EXAMPLEHOTELNAME = "Hotel Schweizerhof Luzern";
    public static final String EXAMPLEHOTELCOUNTRY = "Switzerland";

    private int hotelID;
    private String hotelName;
    private Country country;
    private int price;

    public Hotel(int hotelID, String hotelName, Country country, int price){
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.country = country;
        this.price = price;
    }

    public int getHotelID(){
        return hotelID;
    }

    public String getHotelName(){
        return hotelName;
    }

    public Country getCountry(){
        return country;
    }

    public int getPrice(){
        return price;
    }
}
