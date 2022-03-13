package seedu.duke;

public class CommandParser {
    static final public String BYE = "bye";

    public Command parse(String commandString) throws WrongCommandException {
        if (commandString.equals(BYE)) {
            return new ExitCommand();
        }
        throw new WrongCommandException("Invalid Command");
    }


}
