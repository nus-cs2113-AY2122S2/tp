package seedu.duke.commands;

public class CommandResult {

    private static final String STRING_RESULT = "String";
    private String commandResultType = "String";
    private Object result;
    public static int commandNumber = 0;

    public CommandResult(Object result) {
        this.result = result;
    }

    public CommandResult(Object result, String commandResultType) {
        this.result = result;
        this.commandResultType = commandResultType;
    }

    @Override
    public String toString() {
        switch (commandResultType) {
        case STRING_RESULT:
            return result.toString();
        default:
            throw new UnsupportedOperationException(result.toString());
        }
    }


}
