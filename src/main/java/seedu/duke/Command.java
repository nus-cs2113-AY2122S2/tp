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

    // MUST FIX SO THAT EXECUTION WORKS WITH ANY COMMAND (CURRENTLY SPECIALIZED FOR SATISFACTION-RELATED COMMANDS)
    public abstract void execute(SatisfactionList satisfactionlist, Ui ui);
}
