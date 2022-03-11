package seedu.duke.command;

import seedu.duke.Packages;


public class PackagesCommand extends Command {
    public PackagesCommand() {
        super(false);
    }

    public void execute(Packages packages) {
        for (int i = 0; i < packages.getSize(); i++) {
            packages.getPackage(i).printInfo();
        }
    }
}