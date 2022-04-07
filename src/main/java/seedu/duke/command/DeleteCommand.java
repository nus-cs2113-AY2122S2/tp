package seedu.duke.command;

import seedu.duke.Packages;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    private final int id;

    public DeleteCommand(int id) {
        this.id = id;
    }

    public void execute(Packages packages) {
        int numberOfPackages = packages.getSize();
        for (int i = 0; i < numberOfPackages; i++) {
            if (packages.getPackage(i).getID() == (id)) {
                packages.removePackage(i);
                System.out.println("Travel package deleted!");
                return;
            }
        }
        System.out.println("Package not found!");
    }
}
