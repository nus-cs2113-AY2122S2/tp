package seedu.duke.commands;

public class CommandResult {
    private String resultString;

    public CommandResult(String result) {
        this.resultString = result;
    }

    @Override
    public String toString() {
        return resultString;
    }
}
