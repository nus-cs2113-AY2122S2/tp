package seedu.duke.commands;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.data.ModuleList;
import seedu.duke.util.Configuration;
import seedu.duke.util.StringConstants;

import static seedu.duke.util.StringConstants.OPTION_COMMAND_WORD;
import static seedu.duke.util.StringConstants.OPTION_HELP;

public class HelpCommand extends Command {
    protected static final String EXIT_COMMAND_WORD = StringConstants.EXIT_COMMAND_WORD;
    protected static final String ADD_COMMAND_WORD = StringConstants.ADD_COMMAND_WORD;
    protected static final String DELETE_COMMAND_WORD = StringConstants.DELETE_COMMAND_WORD;
    protected static final String EDIT_COMMAND_WORD = StringConstants.EDIT_COMMAND_WORD;
    protected static final String GRADE_COMMAND_WORD = StringConstants.GRADE_COMMAND_WORD;
    protected static final String LIST_COMMAND_WORD = StringConstants.LIST_COMMAND_WORD;
    protected static final String MARK_COMMAND_WORD = StringConstants.MARK_COMMAND_WORD;
    protected static final String RESET_COMMAND_WORD = StringConstants.RESET_COMMAND_WORD;
    protected static final String SAVE_COMMAND_WORD = StringConstants.SAVE_COMMAND_WORD;
    protected static final String TAG_COMMAND_WORD = StringConstants.TAG_COMMAND_WORD;

    protected static final String HELP_NOTE = StringConstants.HELP_NOTE;
    protected static final String EXIT_HELP = StringConstants.EXIT_HELP;
    protected static final String ADD_HELP = StringConstants.ADD_HELP;
    protected static final String DELETE_HELP = StringConstants.DELETE_HELP;
    protected static final String EDIT_HELP = StringConstants.EDIT_HELP;
    protected static final String GRADE_HELP = StringConstants.GRADE_HELP;
    protected static final String LIST_HELP = StringConstants.LIST_HELP;
    protected static final String MARK_HELP = StringConstants.MARK_HELP;
    protected static final String RESET_HELP = StringConstants.RESET_HELP;
    protected static final String SAVE_HELP = StringConstants.SAVE_HELP;
    protected static final String TAG_HELP = StringConstants.TAG_HELP;
    protected static final String HELP = StringConstants.HELP;
    protected static final String HELP_EXCEPTION = StringConstants.HELP_EXCEPTION;

    private final String command;

    public HelpCommand(String command) {
        this.command = command;
    }

    @Override
    public CommandResult execute(ModuleList moduleList, Configuration configuration) throws ModHappyException {
        if (command.isBlank()) {
            return new CommandResult(HELP + "\n\n" + HELP_NOTE);
        }
        switch (command) {
        case EXIT_COMMAND_WORD:
            return new CommandResult(EXIT_HELP);
        case ADD_COMMAND_WORD:
            return new CommandResult(ADD_HELP);
        case DELETE_COMMAND_WORD:
            return new CommandResult(DELETE_HELP);
        case EDIT_COMMAND_WORD:
            return new CommandResult(EDIT_HELP);
        case GRADE_COMMAND_WORD:
            return new CommandResult(GRADE_HELP);
        case LIST_COMMAND_WORD:
            return new CommandResult(LIST_HELP);
        case MARK_COMMAND_WORD:
            return new CommandResult(MARK_HELP);
        case RESET_COMMAND_WORD:
            return new CommandResult(RESET_HELP);
        case SAVE_COMMAND_WORD:
            return new CommandResult(SAVE_HELP);
        case TAG_COMMAND_WORD:
            return new CommandResult(TAG_HELP);
        case OPTION_COMMAND_WORD:
            return new CommandResult(OPTION_HELP);
        default:
            throw new ModHappyException(HELP_EXCEPTION);
        }
    }
}
