package seedu.duke.command;

import seedu.duke.Packages;
import seedu.duke.Reservations;

public class DeleteCommand extends Command {
    private final int id;

    public DeleteCommand(int id) {
        this.id = id;
    }

    public void execute(Packages packages) {
        int numberOfPackages = packages.getSize();
        for (int i = 0; i < numberOfPackages; i++) {
            if (packages.getPackage(i).getID() == (id)) {
                packages.removePackage(i);
                break;
            }
        }

    }
}
