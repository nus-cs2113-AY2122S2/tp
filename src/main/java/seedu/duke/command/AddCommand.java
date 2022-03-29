package seedu.duke.command;

import java.util.Date;

import seedu.duke.Packages;
import seedu.duke.Reservations;
import seedu.duke.TravelPackage;


//creates a TravelPackage and adds to packages
public class AddCommand extends Command {
    private TravelPackage newPackage;

    public AddCommand(String name, int id,String date1, String date2, String hotel, double price, String country,
            int maxVacancies) {
        this.newPackage = new TravelPackage(name, id,date1, date2, hotel, price, country,
                maxVacancies);
    }

    public TravelPackage getPackage() {
        return this.newPackage;
    }

    public void execute(Packages packages) {
        packages.addPackage(newPackage);
    }
}
