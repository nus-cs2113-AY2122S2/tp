package seedu.duke.command;

import java.util.Date;

import seedu.duke.Packages;
import seedu.duke.TravelPackage;

public class AddCommand extends Command {
    private TravelPackage newPackage;

    public AddCommand(String name, int id, int date1, int date2, String hotel, double price, String country,
            int maxVacancies) {
        super(false);
        this.newPackage = new TravelPackage(name, id, new Date(date1), new Date(date2), hotel, price, country,
                maxVacancies);
    }

    public void execute(Packages packages) {
        packages.addPackage(newPackage);
    }
}
