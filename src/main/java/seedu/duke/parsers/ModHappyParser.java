package seedu.duke.parsers;

import seedu.duke.commands.AddCommand;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.MarkCommand;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.Command;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.UnknownCommandException;

import java.util.HashMap;
import java.util.HashSet;

public class ModHappyParser extends Parser {

    private static final String ARGUMENT = "arguments";
    private static final String COMMAND_WORD = "commandWord";
    private static final String EXIT_COMMAND_WORD = "exit";
    private static final String ADD_COMMAND_WORD = "add";
    private static final String DELETE_COMMAND_WORD = "del";
    private static final String LIST_COMMAND_WORD = "list";
    private static final String MARK_COMMAND_WORD = "mark";
    private static final String MOD_HAPPY_COMMAND_FORMAT = "(?<commandWord>\\S+)"
            + "\\s*(?<arguments>.*)";

    public ModHappyParser() {
        super();
        // See also https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
        this.commandFormat = MOD_HAPPY_COMMAND_FORMAT;
        groupNames = new HashSet<String>();
        parsedCommand = new HashMap<>();
        groupNames.add(COMMAND_WORD);
        groupNames.add(ARGUMENT);
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        try {
            HashMap<String, String> parsedCommand = parseString(userInput);
            switch (parsedCommand.get(COMMAND_WORD)) {
            case (EXIT_COMMAND_WORD):
                return new ExitCommand(parsedCommand.get(COMMAND_WORD));
            case (ADD_COMMAND_WORD):
                return new AddCommand(parsedCommand.get(ARGUMENT));
            case(DELETE_COMMAND_WORD):
                return new DeleteCommand(parsedCommand.get(ARGUMENT)).prepareDeleteCommand();
            case (LIST_COMMAND_WORD):
                return new ListCommand(parsedCommand.get(COMMAND_WORD));
            case (MARK_COMMAND_WORD):
                return new MarkCommand(parsedCommand.get(ARGUMENT));
            default:
                throw new UnknownCommandException(userInput);
            }
        } catch (ModHappyException e) {
            throw new UnknownCommandException(userInput);
        } catch (Exception e) {
            System.out.println(e);
            throw new UnknownCommandException(userInput);
        }
    }

}
