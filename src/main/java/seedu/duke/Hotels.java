package seedu.duke;

import java.util.ArrayList;

public class Hotels {

    private final ArrayList<Hotel> hotels;

    public Hotels() {
        this.hotels = new ArrayList<>();
    }

    public Hotels(ArrayList<Hotel> h) {
        this.hotels = h;
    }

    public int getSize() {
        return hotels.size();
    }

    public Hotel getHotel(int index) {
        return hotels.get(index);
    }

    public Hotel getHotelByID(int id) {
        for (Hotel hotel : hotels) {
            if (hotel.getHotelID() == id) {
                return hotel;
            }
        }
        return null;
    }

    public void addHotel(Hotel newHotel) {
        if (isContainSameHotel(newHotel)) {
            System.out.println("This Hotel already exists! Please try again.");
        } else {
            hotels.add(newHotel);
            System.out.println("HOTEL ADDED");
        }
    }

    public void removeHotel(int index) {
        hotels.remove(index);
    }

    public void printAllHotels() {
        if (hotels.size() == 0) {
            System.out.println("No hotels added yet!");
        }
        else {
            for (int i = 0; i < hotels.size(); i++) {
                System.out.println(i + 1 + ". " + hotels.get(i).toString());
            }
        }
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

    public void initHotel(Hotel newHotel) {
        hotels.add(newHotel);
    }

}
