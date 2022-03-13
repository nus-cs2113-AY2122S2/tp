package seedu.duke;

import java.util.ArrayList;

public abstract class Command {
    protected boolean isExit;

    protected Command() {
        this.isExit = false;
    }

    public abstract void execute() throws WrongCommandException;

    public boolean isExit() {
        return this.isExit;
    }
}
