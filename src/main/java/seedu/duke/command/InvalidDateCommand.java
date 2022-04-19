package seedu.duke.command;

import seedu.duke.Packages;

public class InvalidDateCommand extends Command {

    private final String feedback;

    public InvalidDateCommand(String feedback) {
        this.feedback = feedback;
    }

    public String getFeedback() {
        return this.feedback;
    }

    public void execute(Packages packages) {
        System.out.println(this.feedback);
        System.out.println("Use the help command to find out the valid commands.");

    }

}
