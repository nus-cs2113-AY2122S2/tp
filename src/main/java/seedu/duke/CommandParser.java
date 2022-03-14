package seedu.duke;

public class CommandParser {
    public static final String BYE = "bye";

    public Command parse(String commandString) throws WrongCommandException {
        Command userCommand = null;
        if (commandString.equals(BYE)) {
            userCommand = new ExitCommand();
        } else {
            throw new WrongCommandException("Invalid Command");
        }
        return userCommand;
    }

}
