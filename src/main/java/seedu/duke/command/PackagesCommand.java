package seedu.duke.command;

import seedu.duke.Packages;

public class PackagesCommand extends Command {

    public static final String COMMAND_WORD = "packages";

    public void execute(Packages packages) {
        if (packages.getSize() == 0) {
            System.out.println("No packages found!");
        }
        for (int i = 0; i < packages.getSize(); i++) {
            System.out.println(
                    i + 1 + ". PackageID-" + packages.getPackage(i).getID() + " | " + packages.getPackage(i).getCountry() + " - " + packages.getPackage(i).getName());
        }
    }
}
