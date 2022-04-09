package seedu.duke;

import java.util.ArrayList;

public class Country {
    private String countryName;
    private final ArrayList<Hotel> hotels;

    public Country(String countryName){
        this.countryName = countryName;
        this.hotels = new ArrayList<>();
    }
    public Country(ArrayList<Hotel> h) {
        this.hotels = h;
    }

    public String getCountryName(){
        return countryName;
    }

    public void addHotel(Hotel newHotel) {
        hotels.add(newHotel);
    }

    public void removePackage(int index) {
        hotels.remove(index);
    }

    public boolean idExists(int id) {
        for (Hotel aHotel : hotels) {
            if (aHotel.getHotelID() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean isContainSameHotel(Hotel newHotel) {
        for (Hotel aHotel : hotels) {
            if (aHotel.getHotelID() == newHotel.getHotelID() &&
                    aHotel.getHotelName().equals(newHotel.getHotelName()) &&
                    aHotel.getCountry().equals((newHotel.getCountry())) &&
                    aHotel.getPrice() == newHotel.getPrice()
                ) {
                return true;
            }
        }
        return false;
    }
}
