package seedu.duke.command;

import seedu.duke.Packages;

public class ErrorCommand extends Command{
    private String input;

    public ErrorCommand(String input)  {
        this.input = input;
    }

    public void execute(Packages packages) {
        System.out.println("Input not recognized: " + this.input);
        System.out.println("Use the help command to find out the valid commands.");

    }

}
