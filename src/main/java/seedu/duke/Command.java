package seedu.duke;

import java.util.ArrayList;

public abstract class Command {
    protected boolean isExit;

    protected Command() {
        this.isExit = false;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public abstract void execute();

    public abstract void execute(SatisfactionList satisfactionList);

}
