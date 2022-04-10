//author @@hlwang56

package seedu.planitarium.commands;

import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.exceptions.UnknownInputException;
import seedu.planitarium.global.Constants;
import seedu.planitarium.parser.Parser;
import seedu.planitarium.family.Family;
import seedu.planitarium.ProjectLogger;

import java.util.Objects;
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
    protected static final String EXIT_CMD = "bye";
    protected static final String SEARCH_CMD = "find";
    protected Command newCommand = null;

    protected static final String LOG_RETURN_CMD = "'%s' command is returned.";
    protected static final String LOG_ERROR_MSG = "Invalid input: '%s'.";

    public CommandFactory() {
    }

    /**
     * Checks if the keyword of user instruction refers to AddRecordCommand.
     *
     * @param keyword Keyword of user instruction parsed by Parser
     * @return True if the keyword is referring to AddRecordCommand
     */
    private boolean isAddRecordCommand(String keyword) {
        return Objects.equals(keyword, ADD_INCOME_CMD) || Objects.equals(keyword, ADD_SPENT_CMD);
    }

    /**
     * Checks if the keyword of user instruction refers to AddRecordCommand.
     *
     * @param keyword Keyword of user instruction parsed by Parser
     * @return True if the keyword is referring to AddRecordCommand
     */
    private boolean isDeleteRecordCommand(String keyword) {
        return Objects.equals(keyword, DELETE_INCOME_CMD) || Objects.equals(keyword, DELETE_SPEND_CMD);
    }

    /**
     * Checks if the keyword of user instruction refers to AddRecordCommand.
     *
     * @param keyword Keyword of user instruction parsed by Parser
     * @return True if the keyword is referring to AddRecordCommand
     */
    private boolean isAddPersonCommand(String keyword) {
        return Objects.equals(keyword, ADD_PERSON_CMD);
    }

    /**
     * Checks if the keyword of user instruction refers to AddRecordCommand.
     *
     * @param keyword Keyword of user instruction parsed by Parser
     * @return True if the keyword is referring to AddRecordCommand
     */
    private boolean isDeletePersonCommand(String keyword) {
        return Objects.equals(keyword, DELETE_PERSON_CMD);
    }

    /**
     * Checks if the keyword of user instruction refers to AddRecordCommand.
     *
     * @param keyword Keyword of user instruction parsed by Parser
     * @return True if the keyword is referring to AddRecordCommand
     */
    private boolean isListCommand(String keyword) {
        return Objects.equals(keyword, LIST_CMD);
    }

    /**
     * Checks if the keyword of user instruction refers to AddRecordCommand.
     *
     * @param keyword Keyword of user instruction parsed by Parser
     * @return True if the keyword is referring to AddRecordCommand
     */
    private boolean isEditRecordCommand(String keyword) {
        return Objects.equals(keyword, EDIT_INCOME_CMD) || Objects.equals(keyword, EDIT_SPEND_CMD);
    }

    /**
     * Checks if the keyword of user instruction refers to AddRecordCommand.
     *
     * @param keyword Keyword of user instruction parsed by Parser
     * @return True if the keyword is referring to AddRecordCommand
     */
    private boolean isListCatCommand(String keyword) {
        return Objects.equals(keyword, LIST_CAT_CMD);
    }

    /**
     * Checks if the keyword of user instruction refers to AddRecordCommand.
     *
     * @param keyword Keyword of user instruction parsed by Parser
     * @return True if the keyword is referring to AddRecordCommand
     */
    private boolean isOverviewCommand(String keyword) {
        return Objects.equals(keyword, OVERVIEW_CMD);
    }

    /**
     * Checks if the keyword of user instruction refers to AddRecordCommand.
     *
     * @param keyword Keyword of user instruction parsed by Parser
     * @return True if the keyword is referring to AddRecordCommand
     */
    private boolean isHelpCommand(String keyword) {
        return Objects.equals(keyword, HELP_CMD);
    }

    /**
     * Checks if the keyword of user instruction refers to AddRecordCommand.
     *
     * @param keyword Keyword of user instruction parsed by Parser
     * @return True if the keyword is referring to AddRecordCommand
     */
    private boolean isSearchCommand(String keyword) {
        return Objects.equals(keyword, SEARCH_CMD);
    }

    /**
     * Checks if the keyword of user instruction refers to AddRecordCommand.
     *
     * @param keyword Keyword of user instruction parsed by Parser
     * @return True if the keyword is referring to AddRecordCommand
     */
    private boolean isExitCommand(String keyword) {
        return Objects.equals(keyword, EXIT_CMD);
    }

    /**
     * Returns a command according to the keyword of user instruction.
     *
     * @param userInput Input from user in String format.
     * @param family A Family object that stores all the records.
     * @return XYZCommand according to user instruction.
     * @throws PlanITariumException if the keyword of user instruction is wrong.
     */
    public Command getCommand(String userInput, Family family) throws PlanITariumException {
        assert (userInput != null) : Constants.INPUT_NOT_NULL;
        assert (family != null) : Constants.FAMILY_NOT_NULL;
        String keyword = Parser.parseCommandType(userInput);
        if (isAddPersonCommand(keyword)) {
            newCommand = new AddPersonCommand(userInput, family);
        } else if (isDeletePersonCommand(keyword)) {
            newCommand = new DeletePersonCommand(userInput, family);
        } else if (isAddRecordCommand(keyword)) {
            newCommand = new AddRecordCommand(userInput, family);
        } else if (isDeleteRecordCommand(keyword)) {
            newCommand = new DeleteRecordCommand(userInput, family);
        } else if (isListCommand(keyword)) {
            newCommand = new ListCommand(userInput, family);
        } else if (isEditRecordCommand(keyword)) {
            newCommand = new EditRecordCommand(userInput, family);
        } else if (isListCatCommand(keyword)) {
            newCommand = new ListCategoriesCommand(userInput, family);
        } else if (isOverviewCommand(keyword)) {
            newCommand = new OverviewCommand(userInput, family);
        } else if (isHelpCommand(keyword)) {
            newCommand = new HelpCommand(userInput, family);
        } else if (isSearchCommand(keyword)) {
            newCommand = new SearchCommand(userInput, family);
        } else if (isExitCommand(keyword)) {
            newCommand = new ExitCommand(userInput, family);
        } else {
            logger.log(Level.WARNING, String.format(LOG_ERROR_MSG, userInput));
            throw new UnknownInputException(CommandFactory.class.getSimpleName());
        }
        logger.log(Level.INFO, String.format(LOG_RETURN_CMD, newCommand.getType()));
        assert (newCommand != null) : CMD_NOT_NULL;
        return newCommand;
    }

}
