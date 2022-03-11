package seedu.duke.command;

import seedu.duke.Packages;


public class DeleteCommand extends Command {
    private int id;

    public DeleteCommand(int id) {
        super(false);
        this.id = id;
    }

    public void execute(Packages packages) {
        for (int i = 0; i < packages.getSize(); i++) {
            if (packages.getPackage(i).getID() == id) {
                packages.removePackage(i);
                break;
            }
        }
    }
}