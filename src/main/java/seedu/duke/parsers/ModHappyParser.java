package seedu.duke.parsers;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.ParseException;
import seedu.duke.exceptions.UnknownCommandException;

import java.util.HashMap;
import java.util.HashSet;

public class ModHappyParser extends Parser {

    private static final String ARGUMENT = "arguments";
    private static final String COMMAND_WORD = "commandWord";
    private static final String MOD_HAPPY_COMMAND_FORMAT = "(?<commandWord>\\S+)"
            + "\\s*(?<arguments>.*)";

    public ModHappyParser() {
        super();
        // See also https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
        this.commandFormat = MOD_HAPPY_COMMAND_FORMAT;
        groupNames = new HashSet<>();
        parsedCommand = new HashMap<>();
        groupNames.add(COMMAND_WORD);
        groupNames.add(ARGUMENT);
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        try {
            HashMap<String, String> parsedCommand = parseString(userInput);
            Parser commandParser = getCommandParser(parsedCommand.get(COMMAND_WORD));
            return commandParser.parseCommand(parsedCommand.get(ARGUMENT));
        } catch (ParseException e) {
            throw new ParseException();
        } catch (ModHappyException e) {
            throw new UnknownCommandException(userInput);
        } catch (Exception e) {
            System.out.println(e);
            throw new UnknownCommandException(userInput);
        }
    }

    /**
     * Returns the Parser object for parsing commands associated with the given command word.
     * If the command takes no arguments, null is returned.
     * @param commandWord the command word (e.g. "add")
     * @return an instance of the relevant Parser object or null.
     * @throws UnknownCommandException if commandWord does not correspond with any command.
     */
    private Parser getCommandParser(String commandWord) throws UnknownCommandException {
        switch (commandWord) {
        case (EXIT_COMMAND_WORD):
        case (LIST_COMMAND_WORD):
            // Intentional fallthrough
            return new NoArgumentParser(commandWord);
        case (ADD_COMMAND_WORD):
            return new AddParser();
        case (MARK_COMMAND_WORD):
            return new MarkParser();
        default:
            throw new UnknownCommandException();
        }
    }

}
