package seedu.duke;

public class Hotel {

    public static final String EXAMPLEHOTELID = "456";
    public static final String EXAMPLEHOTELNAME = "Hotel Schweizerhof Luzern";
    public static final String EXAMPLEHOTELCOUNTRY = "Switzerland";

    private int hotelID;
    private String hotelName;
    private String country;
    private int packageID;
    private double price;

    public Hotel(int hotelID, String hotelName, String country, double price, int packageID){
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.country = country;
        this.price = price;
        this.packageID = packageID;
    }

    public int getHotelID(){
        return hotelID;
    }

    public String getHotelName(){
        return hotelName;
    }

    public String getCountry(){
        return country;
    }

    public double getPrice(){
        return price;
    }

    public int getPackageID() {
        return packageID;
    }

    public String toString() {
        return  "Package        " + getPackageID() + "\n"
                + "ID            " + getHotelID() + "\n"
                + "Name    " + getHotelName() + "\n"
                + "Country            " + getCountry() + "\n"
                + "Price            " + getPrice() + "\n";
    }

    public String toSave() {
        return hotelID + "," + hotelName + "," + country + "," + price + "," + packageID;
    }
}
