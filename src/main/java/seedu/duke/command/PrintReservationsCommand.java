package seedu.duke.command;

import seedu.duke.Packages;
import seedu.duke.TravelPackage;

public class PrintReservationsCommand extends Command {
    private int packageID;

    public PrintReservationsCommand(int packageID) {
        this.packageID = packageID;
    }

    public void execute(Packages p) {
        TravelPackage tp = p.getPackageByID(packageID);
        if (tp != null) {
            tp.getReservationList().printAllReservations();
        } else {
            System.out.println("This package does not exist!");
        }
    }
}
