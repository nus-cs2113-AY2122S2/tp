package seedu.duke.command;

import seedu.duke.Hotel;
import seedu.duke.Packages;

public class AddHotelCommand extends Command{
    public static final String COMMAND_WORD = "addHotel";
    private final Hotel newHotel;

    public AddHotelCommand(int hotelID, String hotelName, String country, double price, int packageID) {
        this.newHotel = new Hotel(hotelID, hotelName, country, price, packageID);
    }

    public void execute(Packages packages) {
        boolean isFoundID = false;
        for (int i = 0; i < packages.getSize(); i++) {
            if (packages.getPackage(i).getID() == newHotel.getPackageID()) {
                packages.getPackage(i).getHotelsList().addHotel(this.newHotel);
                isFoundID = true;
                break;
            }
        }
        if (!isFoundID) {
            System.out.println("Hotel ID not found. Please try again.");
        }

    }

}



