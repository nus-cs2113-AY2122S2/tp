package seedu.duke.command;

import seedu.duke.Packages;

public class InfoCommand extends Command {

    public static final String COMMAND_WORD = "info";

    private final int travelPackageID;

    public InfoCommand(int id) {
        this.travelPackageID = id;
    }

    public void execute(Packages packages) {
        if (travelPackageID - 1 < packages.getSize()) {
            System.out.println("Package " + travelPackageID + " found: ");
            // System.out.println(packages.getPackage(travelPackageID-1).getCountry() + " -
            // " + packages.getPackage(travelPackageID-1).getName());
            System.out.println(packages.getPackage(travelPackageID - 1));
        } else {
            System.out.println("Package not found.");
        }
    }
}
