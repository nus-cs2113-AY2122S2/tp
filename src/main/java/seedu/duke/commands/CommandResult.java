package seedu.duke.commands;

//@@author  Ch40gRv1-Mu
public class CommandResult {
    private final String resultString;

    public CommandResult(String result) {
        this.resultString = result;
    }

    @Override
    public String toString() {
        return resultString;
    }
}
