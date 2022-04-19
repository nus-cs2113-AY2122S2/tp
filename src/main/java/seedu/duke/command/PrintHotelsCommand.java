package seedu.duke.command;

import seedu.duke.Packages;
import seedu.duke.TravelPackage;

public class PrintHotelsCommand extends Command{

    public static final String COMMAND_WORD = "hotels";
    private final int packageID;

    public PrintHotelsCommand(int packageID) {
        this.packageID = packageID;
    }

    public void execute(Packages p) {
        TravelPackage tp = p.getPackageByID(packageID);
        if (tp != null) {
            System.out.println("Hotels under this package: ");
            tp.getHotelsList().printAllHotels();
        } else {
            System.out.println("This package does not exist!");
        }
    }

}






