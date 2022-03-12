package seedu.sherpass.command;

import seedu.sherpass.util.Storage;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Ui;

import static seedu.sherpass.constant.Message.HELP_MESSAGE_QUICK_START_COMMAND;
import static seedu.sherpass.constant.Message.HELP_MESSAGE_SPECIFIC_COMMAND;

public class HelpCommand extends Command {
    private String helpInput;
    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_WORD_VARIANT = "quick start";

    /**
     * Creates a constructor for the HelpCommand. Accepts the help input.
     * Help input contains a keyword to obtain detailed information on
     * available commands to use.
     *
     * @param filteredHelpInput Help keyword.
     */
    public HelpCommand(String filteredHelpInput) {
        helpInput = filteredHelpInput.trim().toLowerCase();
    }

    private void showHelpList(Ui ui) {
        System.out.println("Here is a list of commands available:");
        // To confirm indentation spaces when prof reply;
        // CheckStyle wants 4 spaces for continuation indent, but 8 spaces for other
        String[] helpCommands = {ListCommand.COMMAND_WORD, MarkCommand.COMMAND_WORD,
            UnmarkCommand.COMMAND_WORD, AddCommand.COMMAND_WORD, DeadlineCommand.COMMAND_WORD,
            EventCommand.COMMAND_WORD, DeleteCommand.COMMAND_WORD, FindCommand.COMMAND_WORD,
            ClearCommand.COMMAND_WORD, ExitCommand.COMMAND_WORD,};
        for (String helpCommand : helpCommands) {
            ui.showLine();
            showSpecificHelpCommand(ui, helpCommand);
        }
        ui.showLine();
        System.out.println("For a quick summary of what commands to execute, \n"
                + "enter 'help quick start'.");
    }


    private void showSpecificHelpCommand(Ui ui, String helpCommand) {
        switch (helpCommand) {
        case ListCommand.COMMAND_WORD:
            ui.showToUser(ListCommand.MESSAGE_USAGE);
            break;
        case MarkCommand.COMMAND_WORD:
            ui.showToUser(MarkCommand.MESSAGE_USAGE);
            break;
        case UnmarkCommand.COMMAND_WORD:
            ui.showToUser(UnmarkCommand.MESSAGE_USAGE);
            break;
        case AddCommand.COMMAND_WORD:
            ui.showToUser(AddCommand.MESSAGE_USAGE);
            break;
        case DeadlineCommand.COMMAND_WORD:
            ui.showToUser(DeadlineCommand.MESSAGE_USAGE);
            break;
        case EventCommand.COMMAND_WORD:
            ui.showToUser(EventCommand.MESSAGE_USAGE);
            break;
        case DeleteCommand.COMMAND_WORD:
            ui.showToUser(DeleteCommand.MESSAGE_USAGE);
            break;
        case FindCommand.COMMAND_WORD:
            ui.showToUser(FindCommand.MESSAGE_USAGE);
            break;
        case ClearCommand.COMMAND_WORD:
            ui.showToUser(ClearCommand.MESSAGE_USAGE);
            break;
        case ExitCommand.COMMAND_WORD:
            ui.showToUser(ExitCommand.MESSAGE_USAGE);
            break;
        case COMMAND_WORD_VARIANT:
            ui.showToUser(HELP_MESSAGE_QUICK_START_COMMAND + HELP_MESSAGE_SPECIFIC_COMMAND);
            break;
        default:
            showHelpList(ui);
        }
    }

    /**
     * Executes the help command. Checks for the search keyword
     * in the help command to list detailed information of available
     * commands according to what the keyword is looking for.
     *
     * @param taskList Task array.
     * @param ui       Ui for printing.
     * @param storage  Storage for overwriting/appending save data. Not used in this method.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        showSpecificHelpCommand(ui, helpInput);
    }
}


