package seedu.duke.command;

import seedu.duke.Packages;
import seedu.duke.TravelPackage;

public class InfoCommand extends Command {

    public static final String COMMAND_WORD = "info";

    private final int travelPackageID;

    public InfoCommand(int id) {
        this.travelPackageID = id;
    }

    public void execute(Packages packages) {
        if (packages.getSize() == 0) {
            System.out.println("No packages added yet!");
        }
        for (int i=0; i<packages.getSize(); i++) {
            TravelPackage currentPackage = packages.getPackage(i);
            if (travelPackageID == currentPackage.getID()){
                System.out.println("Package " + travelPackageID + " found: ");
                System.out.println(currentPackage);
                return;
            }
        }
        System.out.println("Package not found.");
    }
}
