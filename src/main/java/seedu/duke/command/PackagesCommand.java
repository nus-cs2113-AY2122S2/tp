package seedu.duke.command;

import seedu.duke.Packages;
import seedu.duke.Reservations;

public class PackagesCommand extends Command {
    public void execute(Packages packages) {
        for (int i = 0; i < packages.getSize(); i++) {
            System.out.println(i+1 + ". " + packages.getPackage(i).getCountry() + " - " + packages.getPackage(i).getName());
            //System.out.println(packages.getPackage(i));
        }
    }
}
