//author @@hlwang56

package seedu.planitarium.commands;

import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.exceptions.UnknownInputException;
import seedu.planitarium.global.Constants;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.family.Family;
import seedu.planitarium.ProjectLogger;

import java.util.logging.Level;

/**
 * Generates different command classes for execution according to user instruction.
 */
public class CommandFactory {
    private static final String className = CommandFactory.class.getSimpleName();
    private static final String fileName = className + ".log";
    protected static final ProjectLogger logger = new ProjectLogger(className, fileName);

    protected static final String CMD_NOT_NULL = "Command should not be null";

    protected static final String ADD_PERSON_CMD = "add";
    protected static final String DELETE_PERSON_CMD = "delete";
    protected static final String ADD_INCOME_CMD = "addin";
    protected static final String DELETE_INCOME_CMD = "deletein";
    protected static final String EDIT_INCOME_CMD = "editin";
    protected static final String ADD_SPENT_CMD = "addout";
    protected static final String DELETE_SPEND_CMD = "deleteout";
    protected static final String EDIT_SPEND_CMD = "editout";
    protected static final String LIST_CMD = "list";
    protected static final String OVERVIEW_CMD = "overview";
    protected static final String HELP_CMD = "help";
    protected static final String LIST_CAT_CMD = "listcat";
    protected static final String EXIT = "bye";
    protected static final String SEARCH_CMD = "find";
    protected Command newCommand = null;

    protected static final String LOG_RETURN_CMD = "'%s' command is returned.";
    protected static final String LOG_ERROR_MSG = "Invalid input: '%s'.";

    public CommandFactory() {
    }

    public Command getCommand(String userInput, Family family) throws PlanITariumException {
        assert (userInput != null) : Constants.INPUT_NOT_NULL;
        assert (family != null) : Constants.FAMILY_NOT_NULL;
        switch (Parser.parseCommandType(userInput)) {
        case ADD_PERSON_CMD:
            newCommand = new AddPersonCommand(userInput, family);
            break;
        case DELETE_PERSON_CMD:
            newCommand = new DeletePersonCommand(userInput, family);
            break;
        case ADD_INCOME_CMD:
        case ADD_SPENT_CMD:
            newCommand = new AddRecordCommand(userInput, family);
            break;
        case DELETE_INCOME_CMD:
        case DELETE_SPEND_CMD:
            newCommand = new DeleteRecordCommand(userInput, family);
            break;
        case LIST_CMD:
            newCommand = new ListCommand(userInput, family);
            break;
        case EDIT_INCOME_CMD:
        case EDIT_SPEND_CMD:
            newCommand = new EditRecordCommand(userInput, family);
            break;
        case LIST_CAT_CMD:
            newCommand = new ListCategoriesCommand(userInput, family);
            break;
        case OVERVIEW_CMD:
            newCommand = new OverviewCommand(userInput, family);
            break;
        case HELP_CMD:
            newCommand = new HelpCommand(userInput, family);
            break;
        case SEARCH_CMD:
            newCommand = new SearchCommand(userInput,family);
            break;
        case EXIT:
            newCommand = new ExitCommand(userInput, family);
            break;
        default:
            logger.log(Level.WARNING, String.format(LOG_ERROR_MSG, userInput));
            throw new UnknownInputException(CommandFactory.class.getSimpleName());
        }
        logger.log(Level.INFO, String.format(LOG_RETURN_CMD, newCommand.getType()));
        assert (newCommand != null) : CMD_NOT_NULL;
        return newCommand;
    }

}
