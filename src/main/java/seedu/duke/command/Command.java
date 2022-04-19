package seedu.duke.command;

import seedu.duke.Packages;
import seedu.duke.Reservations;

public abstract class Command {
    private boolean isExit = false;

    public boolean getIsExit() {
        return this.isExit;
    }

    public void setIsExit(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(Packages packages);

}
