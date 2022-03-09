package seedu.duke;

public abstract class Command {

    public abstract Event execute(String[] args);
}
